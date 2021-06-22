package fulan.tianjian.demo.web.service.server;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.insure.InsureConfigDTO;
import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.insure.InsurePersonDTO;
import fulan.tianjian.demo.model.client.insure.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.PolicySchemeDTO;
import fulan.tianjian.demo.model.client.insure.VehicleDTO;
import fulan.tianjian.demo.model.web.server.vo.PayInfoVo;
import fulan.tianjian.demo.model.web.server.vo.PersonVo;
import fulan.tianjian.demo.model.web.server.vo.PolicyInstanceVo;
import fulan.tianjian.demo.model.web.server.vo.VehicleVo;
import fulan.tianjian.demo.web.service.config.InssureConfigService;

@Service
public class InssureCoreService {
	
	@Autowired
    @Qualifier("insureConfig") 
	private GuavaCahceService<String, InsureConfigDTO> insureConfigService;

	/**
	 * 核保请求
	 * @param orderNumber 订单id
	 * @param regionCode 地区编码
	 * @return
	 * @throws PureRiskLossException 纯风险异常
	 */
	public List<PolicyInstanceVo> underwriting(String orderNumber, String regionCode) throws PureRiskLossException {
		return generalBehavior(orderNumber, regionCode, ConstantCls.UNDERWRITING);
	}

	
	/**
	 * 报价请求
	 * @param orderNumber 订单id
	 * @param regionCode 地区编码
	 * @return
	 * @throws PureRiskLossException 纯风险异常
	 */
	public List<PolicyInstanceVo> quote(String orderNumber, String regionCode) throws PureRiskLossException {
		return generalBehavior(orderNumber, regionCode, ConstantCls.QUOTE);
	}

	/**
	 * 获取支付信息
	 * @param orderNumber
	 * @return
	 */
	public PayInfoVo getPayInfo(String orderNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Autowired
	private PolicyService policyService;
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private VehicleService vehicleService;
	
	@Autowired
	private InsureClient insureClient;
	
	private List<PolicyInstanceVo> generalBehavior(String orderNumber, String regionCode, String type) throws PureRiskLossException {
		
		//获取人员信息并转换
        List<PersonVo> persons = personService.findPersonByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(persons)) {
			return null;
		}
		List<InsurePersonDTO> issurePersons = persons.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		//获取配置信息
		InsureConfigDTO insureConfigDTO = insureConfigService.getValueByKey(regionCode);
		
		//获取保单信息
		List<PolicyInstanceVo> policyInstances = policyService.findPolicyInstanceByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(policyInstances)) {
			return null;
		}
		List<PolicySchemeDTO> policySchemes = policyInstances.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		//获取车辆数据
		VehicleVo vehicleVo = vehicleService.findVehicleByOrderNumber(orderNumber);
		VehicleDTO vehicleDTO = insureClient.getVehicle(vehicleVo.convertToDTO());
		
		InsureDTO insureDTO = new InsureDTO();
		insureDTO.setOrderNumber(orderNumber);
		insureDTO.setPolicySchemes(policySchemes);
		insureDTO.setInsureConfigDTO(insureConfigDTO);
		insureDTO.setInsurePersons(issurePersons);
		insureDTO.setInsureConfigDTO(insureConfigDTO);
		insureDTO.setInsureType(type);
		insureDTO.setVehicleDTO(vehicleDTO);
		
		//核保或报价
		InsureResultDTO insureReult = insureClient.quotedPrice(insureDTO);
		
		//获取返回结果
		List<PolicySchemeDTO> policySchemeResults = insureReult.getInsureDTO().getPolicySchemes();
		if(CollectionUtils.isEmpty(policySchemeResults)) {
			return null;
		}
		List<PolicyInstanceVo> policyInstanceVos = policySchemeResults.stream().map(e -> {
			return e.convertToVo();
		}).collect(Collectors.toList());
		
		//保存报价后的方案并返回给调用方
		policyService.savePolicyInstance(policyInstances);
		return policyInstanceVos;
		
	}
	
	

}
