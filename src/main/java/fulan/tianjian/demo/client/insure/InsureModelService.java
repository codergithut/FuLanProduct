package fulan.tianjian.demo.client.insure;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.client.HttpClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.insure.database.PureRiskEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleTaxEo;
import fulan.tianjian.demo.model.client.insure.drools.GivingPolicy;
import fulan.tianjian.demo.model.client.insure.dto.InsureDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.dto.PolicySchemeDTO;
import fulan.tianjian.demo.model.client.insure.remote.InsurePersonRemote;
import fulan.tianjian.demo.model.client.insure.remote.InsureRemote;
import fulan.tianjian.demo.model.client.insure.remote.PolicySchemeRemote;
import fulan.tianjian.demo.model.client.insure.remote.PureRiskInfoRemote;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.order.OrderCenterVo;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static fulan.tianjian.demo.constant.ConstantCls.VEHICLE_LOSS_INSUREANCE;
import static fulan.tianjian.demo.model.client.convert.VehicleConvertUtil.convertVehicleRemoteByDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsureModelService {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private StagingDataService stagingDataService;
    
    @Autowired
    private DroolsService droolsService;
    

    /**
     * 创建送核心的保文
     * @param insureDTO 前端收集保险数据
     * @param type 请求核心类型
     * @return
     * @throws PureRiskLossException
     */
    public InsureRemote createInsureRemoteByInsureDTO(InsureDTO insureDTO, String type) throws PureRiskLossException {
        InsureRemote insureRemote = new InsureRemote();

        //将用户车辆数据和模版数据合并
        VehicleRemote vehicleRemote = convertVehicleRemoteByDTO(insureDTO.getVehicleDTO());

        //将改单车船税节点数据合并
        VehicleTaxEo vehicleTaxEo = stagingDataService.getVehicleTaxEoByOrderNumber(insureDTO.getOrderNumber());
        if(vehicleTaxEo == null) {
        	vehicleTaxEo = initVehicleTaxRemote(insureDTO);
            stagingDataService.saveVehicleTaxEo(vehicleTaxEo);
        }
        vehicleRemote = vehicleTaxEo.coverVehicleRemote(vehicleRemote);
        insureRemote.setVehicleRemote(vehicleRemote);
        insureRemote.setRegionCode(insureDTO.getRegionCode());

        //获取纯风险保费
        PureRiskInfoRemote pureRiskInfoRemote = getPureRiskInfoByVehicle(vehicleRemote);
        if(pureRiskInfoRemote == null) {
            throw new PureRiskLossException("纯风险数据丢失");
        }
        insureRemote.setPureRiskInfoRemote(pureRiskInfoRemote);
        insureRemote.setVehicleRemote(vehicleRemote);

        //获取上次核心返回单保单风险信息
        InsuranceRiskInformationEo insuranceRiskInformationEo = stagingDataService
                .getInsuranceRiskInformationEoByOrderNumber(insureDTO.getOrderNumber());
        if(insuranceRiskInformationEo != null) {
            insureRemote.setInsuranceRiskInformationRemote(insuranceRiskInformationEo.convertEoToRemote());
        }

        //获取上次核心返回的浮动项信息
        PremiumFloatingItemsEo premiumFloatingItemsEo = stagingDataService
                .getPremiumFloatingItemsEoByOrderNumber(insureDTO.getOrderNumber());
        if(premiumFloatingItemsEo != null) {
            insureRemote.setPremiumFloatingItemsRemote(premiumFloatingItemsEo.convertEoToRemote());

        }
        
        insureRemote.setInsureConfigRemote(insureDTO.getInsureConfigDTO().convertToRemote());
        
        List<InsurePersonRemote> insurePresonRemotes = insureDTO.getInsurePersons().stream().map(e -> {
        	return e.convertToRemote();
        }).collect(Collectors.toList());
        
        insureRemote.setInsurePersonRemotes(insurePresonRemotes);
        
        
        //正常保费处理
        List<PolicySchemeRemote> policySchemeRemotes = insureDTO.getPolicySchemes().stream().map(e -> {
        	return e.convertToRemote();
        }).collect(Collectors.toList());
        
        
        //获取规则引擎获取的赠送的险种信息
        List<GivingPolicy> givingPolicys =  droolsService.getGivingPolicyByVehicle(vehicleRemote, policySchemeRemotes, insureDTO.getRegionCode());
        
        //重写部分险种信息比如车辆损失险要和车辆实际价值一致，添加赠送险种等
        overridePolicyRemote(policySchemeRemotes, givingPolicys, vehicleRemote);
        insureRemote.setPolicySchemeRemotes(policySchemeRemotes);
        
        return insureRemote;
    }

    
    /**
     * 重写保险方案数据
     * @param policySchemeRemotes 已有的保险方案数据
     * @param givingPolicys 赠送险种数据
     * @param vehicleRemote 车辆数据
     * @return 是否重写成功
     */
    private Boolean overridePolicyRemote(List<PolicySchemeRemote> policySchemeRemotes,
			List<GivingPolicy> givingPolicys, VehicleRemote vehicleRemote) {
    	
    	//如果是车损失险保额同车辆实际价值
    	for(PolicySchemeRemote policySchemeRemote : policySchemeRemotes) {
    		if(VEHICLE_LOSS_INSUREANCE.equals(policySchemeRemote.getPolicyCode())) {
    			policySchemeRemote.setSumInsured(vehicleRemote.getCurrentPrice());
    		}
    	}
    	
  
    	//判断赠送的险种是否为空
		if(CollectionUtils.isEmpty(givingPolicys)) {
			return false;
		}
		
		//插入按照规则引擎查询到的数据
		List<PolicySchemeRemote> givePolicySchemeReomte = givingPolicys.stream().map(e -> {
			PolicySchemeRemote policySchemeRemote = new PolicySchemeRemote();
			BeanUtils.copyProperties(e, policySchemeRemote);
			return policySchemeRemote;
			
		}).collect(Collectors.toList());
		policySchemeRemotes.addAll(givePolicySchemeReomte);
		
		return true;
	}

	private VehicleTaxEo initVehicleTaxRemote(InsureDTO insureDTO) {
		VehicleTaxEo vehicleTaxEo = new VehicleTaxEo();
		vehicleTaxEo.setVehicleTaxEndTime("20210630");
		vehicleTaxEo.setVehicleTaxEndTime("20220630");
		vehicleTaxEo.setOrderNumber(insureDTO.getOrderNumber());
        return vehicleTaxEo;
    }

    /**
     * 根据车辆数据获取纯风险保费
     * @param vehicleRemote 车辆数据
     * @return 纯风险保费
     */
    private PureRiskInfoRemote getPureRiskInfoByVehicle(VehicleRemote vehicleRemote) {

        //按照请求参数md5值获取本地纯风险保费，如存在返回
        PureRiskEo pureRiskEo = stagingDataService.getPureRiskEoByMd5(vehicleRemote.getMd5Value());

        if(pureRiskEo != null) {
            return pureRiskEo.createPureRiskInfoRemoteByEo();
        }

        //本地无该纯风险保费，请求三方根据返回入库，并返回给调用方
        MyRestValueModel<InsureRemote> result = httpClient.postRestResult(ConstantCls.PURE_RISK_INFO,
                JSON.toJSONString(vehicleRemote), InsureRemote.class);
        if("0000".equals(result.getStatus())) {
            PureRiskEo savePureRiskEo = result.getData()
                    .getPureRiskInfoRemote().createPureRiskEoByPureRiskInfoRemote();
            stagingDataService.savePureRiskEo(savePureRiskEo);
            return result.getData().getPureRiskInfoRemote();
        }

        return null;
    }

    public InsureResultDTO createInsureResultDTOByInsureRemote(InsureRemote insureRemote) {
    	
        InsureResultDTO insureResultDTO = new InsureResultDTO();
        
        InsureDTO insureDTO = new InsureDTO();
        
        if(!CollectionUtils.isEmpty(insureRemote.getPolicySchemeRemotes())) {
        	 List<PolicySchemeDTO> policySchemes = insureRemote.getPolicySchemeRemotes().stream().map(e -> {
             	return e.convertToDTO();
             }).toList();
             insureDTO.setPolicySchemes(policySchemes);
        }
        
        if(insureRemote.getVehicleRemote() != null) {
        	insureDTO.setVehicleDTO(insureRemote.getVehicleRemote().convertToDTO());
        }
       
        insureResultDTO.setInsureDTO(insureDTO);
        insureResultDTO.setResultCode("0000");
        
        BeanUtils.copyProperties(insureRemote, insureResultDTO);
        return insureResultDTO;

    }

	public InsureRemote createInsureRemoteByOrderCenterVo(OrderCenterVo orderCenterVo, String string) {
		// TODO Auto-generated method stub
		InsureRemote insureRemote = new InsureRemote();
		BeanUtils.copyProperties(orderCenterVo, insureRemote);
		return insureRemote;
	}
	
}
