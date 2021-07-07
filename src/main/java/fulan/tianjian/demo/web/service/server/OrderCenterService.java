package fulan.tianjian.demo.web.service.server;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.client.HttpClient;
import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.model.client.insure.dto.InsureResultDTO;
import fulan.tianjian.demo.model.client.order.OrderCenterVo;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.web.vo.PersonVo;
import fulan.tianjian.demo.model.web.vo.PolicyInstanceVo;
import fulan.tianjian.demo.model.web.vo.VehicleVo;

public class OrderCenterService {
	
	@Autowired
	private HttpClient httpClient;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private InsureClient insureClient;

	public Boolean initOrderCenterData(String orderCenterId) {
		
		//订单中心获取订单数据
		MyRestValueModel<OrderCenterVo> orderCenterVo = httpClient.postRestResult(ConstantCls.ORDER_CENTER_URL, 
				orderCenterId, OrderCenterVo.class);
		if(orderCenterVo != null && orderCenterVo.getData() != null) {
			saveOrderCenterData(orderCenterVo.getData());
		}
		
		//删除车辆数据
		vehicleService.deleteVehicleByOrderNumber(orderCenterId);
		
		//转换订单中心数据为本地模型数据并入库
		Boolean result = converAndSaveOrderCenterVo(orderCenterVo.getData());
	
		return result;
	}
	
	private Boolean converAndSaveOrderCenterVo(OrderCenterVo orderCenterVo) {
		
		//获取车辆模型数据
		VehicleVo vehicleVo = getVehicleVoByOrderCenterVo(orderCenterVo);
		
		//获取保险方案数据
		List<PolicyInstanceVo> policyInstances = getPolicyInstacneVoByOrderCenterVo(orderCenterVo);
		
		//获取投保人被保人受益人信息
		List<PersonVo> persons = getPersonVoByOrderCenterVo(orderCenterVo);
	
		//保存信息入库为展示做准备
		personService.savePersonData(persons);
		vehicleService.saveOrUpdateVehicle(vehicleVo);
		policyService.savePolicyInstance(policyInstances);
		
		return null;
	}

	private List<PersonVo> getPersonVoByOrderCenterVo(OrderCenterVo orderCenterVo) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<PolicyInstanceVo> getPolicyInstacneVoByOrderCenterVo(OrderCenterVo orderCenterVo) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean saveOrderCenterData(OrderCenterVo orderCenterVo) {
		return true;
	}
	
	
	public OrderCenterVo getOrderCenterData(String orderCenterId) {
		return null;
	}
	
	
	private VehicleVo getVehicleVoByOrderCenterVo(OrderCenterVo orderCenterVo) {
		return null;
	}

	/**
	 * 半流程核保
	 * @param orderCenterId 订单id
	 * @return
	 */
	public List<PolicyInstanceVo> underwriting(String orderCenterId) {
		
		OrderCenterVo orderCenterVo = getOrderCenterData(orderCenterId);
		
		InsureResultDTO result = insureClient.underwritingByOrderCenter(orderCenterVo);
		
		if(result.getInsureDTO() != null && CollectionUtils.isEmpty(result.getInsureDTO().getPolicySchemes())) {
			return result.getInsureDTO().getPolicySchemes().stream().map(e -> {
				return e.convertToVo();
			}).collect(Collectors.toList());
		}
		
		return null;
	}
	

	

}
