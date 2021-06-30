package fulan.tianjian.demo.web.service.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.model.client.insure.dto.InsureConfigDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureHandlePersonDTO;
import fulan.tianjian.demo.model.web.vo.InsureConfigVo;
import fulan.tianjian.demo.model.web.vo.InsureHandlePersonVo;
import fulan.tianjian.demo.model.web.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;
import fulan.tianjian.demo.model.web.vo.PolicyValueViewVo;

//todo 添加中间层做缓存 VO -> DTO -> EO
@Service
public class ServiceBaseConfigService {

	@Autowired
	@Qualifier("policyConfig")
	private GuavaCahceService<String, List<PolicySchemeConfigVo>> policyConfigCacheService;

	@Autowired
	@Qualifier("inusreConfigPerson")
	private GuavaCahceService<String, List<InsureHandlePersonDTO>> inssureHandlePersonService;

	@Autowired
	@Qualifier("insureConfig")
	private GuavaCahceService<String, List<InsureConfigDTO>> isureConfigCache;
	
	public void saveInsureConfig(List<InsureConfigVo> issureConfigVos) {
		if(CollectionUtils.isEmpty(issureConfigVos)) {
			return ;
		}
		
		List<InsureConfigDTO> insureConfigDTOs = issureConfigVos.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		isureConfigCache.saveCacheData(issureConfigVos.get(0).getProvinceCode(), insureConfigDTOs);
	}
	
	public List<InsureConfigVo> getInsureConfig(String provinceCode) {
		
		List<InsureConfigDTO> insureConfigDTOs = isureConfigCache.findCacheData(provinceCode);
		if(CollectionUtils.isEmpty(insureConfigDTOs)) {
			return null;
		}
		
		List<InsureConfigVo> insureConfigVos = insureConfigDTOs.stream().map(e -> {
			return e.convertToVo();
		}).collect(Collectors.toList());
		
		return insureConfigVos;
	}
	
	
	public void saveInsurePerson(List<InsureHandlePersonVo> insureHandlePersonVos) {
		
		if(CollectionUtils.isEmpty(insureHandlePersonVos)) {
			return ;
		}
		
		List<InsureHandlePersonDTO> insureHandlePersonDTOs = insureHandlePersonVos.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		inssureHandlePersonService.saveCacheData(insureHandlePersonVos.get(0).getRegionCode(), insureHandlePersonDTOs);
		
	}
	
	public List<InsureHandlePersonVo> getInsureHandlePerson(String regionCode) {
		
		List<InsureHandlePersonDTO> insureHandlePersons = inssureHandlePersonService.findCacheData(regionCode);
		
		if(CollectionUtils.isEmpty(insureHandlePersons)) {
			return null;
		}
		
		List<InsureHandlePersonVo> result = insureHandlePersons.stream().map(e -> {
			return e.convertToVo();
		}).collect(Collectors.toList());
		
		return result;
		
	}
	
	

	public void savePolicyConfig(List<PolicySchemeConfigVo> policySchemeConfigVos) {

		if (CollectionUtils.isEmpty(policySchemeConfigVos)) {
			return;
		}

		policyConfigCacheService.saveCacheData(policySchemeConfigVos.get(0).getRegionCode(), policySchemeConfigVos);

	}

	public List<PolicySchemeConfigVo> getPolicyConfig(String regionCode) {
		return policyConfigCacheService.getValueByKey(regionCode);
	}

	public List<PolicyDescribeVo> findPolicyDescribe() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicyValueViewVo> findPolicyValueView() {
		// TODO Auto-generated method stub
		return null;
	}

}
