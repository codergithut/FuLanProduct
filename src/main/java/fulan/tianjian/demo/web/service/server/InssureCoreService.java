package fulan.tianjian.demo.web.service.server;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.insure.dto.InsureConfigDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureHandlePersonDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsurePersonDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.dto.PolicySchemeDTO;
import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;
import fulan.tianjian.demo.model.web.vo.PayInfoVo;
import fulan.tianjian.demo.model.web.vo.PersonVo;
import fulan.tianjian.demo.model.web.vo.PolicyInstanceVo;
import fulan.tianjian.demo.model.web.vo.UrlParamConfigVo;
import fulan.tianjian.demo.model.web.vo.VehicleVo;

@Service
public class InssureCoreService {
	
	@Autowired
    @Qualifier("insureConfig") 
	private GuavaCahceService<String, InsureConfigDTO> insureConfigService;
	
	@Autowired
	@Qualifier("inusreConfigPerson")
	private GuavaCahceService<String, List<InsureHandlePersonDTO>> inssureHandlePersonService;

	/**
	 * 核保请求
	 * @param orderNumber 订单id
	 * @param regionCode 地区编码
	 * @return
	 * @throws PureRiskLossException 纯风险异常
	 */
	public List<PolicyInstanceVo> underwriting(String orderNumber, String regionCode, String provinceCode) throws PureRiskLossException {
		return generalBehavior(orderNumber, regionCode, provinceCode, ConstantCls.UNDERWRITING);
	}

	
	/**
	 * 报价请求
	 * @param orderNumber 订单id
	 * @param regionCode 地区编码
	 * @return
	 * @throws PureRiskLossException 纯风险异常
	 */
	public List<PolicyInstanceVo> quote(String orderNumber, String regionCode, String provinceCode) throws PureRiskLossException {
		return generalBehavior(orderNumber, regionCode, provinceCode, ConstantCls.QUOTE);
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
	
	@Autowired
	private UrlParamConfigService urlParamConfigService;
	
	

	private List<PolicyInstanceVo> generalBehavior(String orderNumber, String regionCode, String provinceCode, String type) throws PureRiskLossException {
		
		//获取人员信息并转换
        List<PersonVo> persons = personService.findPersonByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(persons)) {
			return null;
		}
		List<InsurePersonDTO> issurePersons = persons.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		//获取车辆数据
		VehicleVo vehicleVo = vehicleService.findVehicleByOrderNumber(orderNumber);
		VehicleDTO vehicleDTO = insureClient.getVehicle(vehicleVo.convertToDTO());
		
		
		//获取配置信息
		InsureConfigDTO insureConfigDTO = insureConfigService.getValueByKey(provinceCode);
		if(StringUtils.isNoneBlank(vehicleVo.getConfigId())) {
			UrlParamConfigVo urlParamConfigVo = urlParamConfigService.getUrlParamConfigVo(vehicleVo.getConfigId());
			insureConfigDTO = overrideInsureConfig(insureConfigDTO, urlParamConfigVo);
		}
		
		List<InsureHandlePersonDTO> insureHandlePersonDTOs = inssureHandlePersonService.getValueByKey(regionCode);
		insureConfigDTO.setInsureHandlePersonDTOS(insureHandlePersonDTOs);
		
		if(CollectionUtils.isEmpty(insureHandlePersonDTOs)) {
			return null;
		}
		
		//获取保单信息
		List<PolicyInstanceVo> policyInstances = policyService.findPolicyInstanceByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(policyInstances)) {
			return null;
		}
		List<PolicySchemeDTO> policySchemes = policyInstances.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
	
		InsureDTO insureDTO = new InsureDTO();
		insureDTO.setOrderNumber(orderNumber);
		insureDTO.setPolicySchemes(policySchemes);
		insureDTO.setInsureConfigDTO(insureConfigDTO);
		insureDTO.setInsurePersons(issurePersons);
		insureDTO.setInsureType(type);
		insureDTO.setRegionCode(regionCode);
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
	
	private InsureConfigDTO overrideInsureConfig(InsureConfigDTO insureConfigDTO, UrlParamConfigVo urlParamConfigVo) {
		if(urlParamConfigVo == null) {
			return insureConfigDTO;
		}
		BeanUtils.copyProperties(urlParamConfigVo, insureConfigDTO);
		return insureConfigDTO;
		
	}
	
	

}
