package fulan.tianjian.demo.web.service.config;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

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
public class BaseConfigService {

	@Autowired
	@Qualifier("policyConfig")
	private GuavaCahceService<String, List<PolicySchemeConfigVo>> policyConfigCacheService;

	@Autowired
	@Qualifier("inusreConfigPerson")
	private GuavaCahceService<String, List<InsureHandlePersonDTO>> inssureHandlePersonService;

	@Autowired
	@Qualifier("insureConfig")
	private GuavaCahceService<String, InsureConfigDTO> isureConfigCache;
	
	@Transactional
	public void saveInsureConfig(InsureConfigVo issureConfigVo) {
		if(issureConfigVo == null) {
			return ;
		}
		
		InsureConfigDTO insureConfigDTO = issureConfigVo.convertToDTO();
		
		isureConfigCache.saveKeyAndValue(issureConfigVo.getProvinceCode(), insureConfigDTO);
	}
	
	public InsureConfigVo getInsureConfig(String provinceCode) {
		
		InsureConfigDTO insureConfigDTO = isureConfigCache.getValueByKey(provinceCode);
		if(insureConfigDTO == null ) {
			return null;
		}
		
		InsureConfigVo insureConfigVo = insureConfigDTO.convertToVo();
		
		return insureConfigVo;
	}
	
	
	@Transactional
	public void saveInsurePerson(List<InsureHandlePersonVo> insureHandlePersonVos) {
		
		if(CollectionUtils.isEmpty(insureHandlePersonVos)) {
			return ;
		}
		
		List<InsureHandlePersonDTO> insureHandlePersonDTOs = insureHandlePersonVos.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		inssureHandlePersonService.saveKeyAndValue(insureHandlePersonVos.get(0).getRegionCode(), insureHandlePersonDTOs);
		
	}
	
	public List<InsureHandlePersonVo> getInsureHandlePerson(String regionCode) {
		
		List<InsureHandlePersonDTO> insureHandlePersons = inssureHandlePersonService.getValueByKey(regionCode);
		
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

		policyConfigCacheService.saveKeyAndValue(policySchemeConfigVos.get(0).getRegionCode(), policySchemeConfigVos);

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
	
	public static void main(String[] args) {
		List<InsureHandlePersonVo> insureHanlePersonVos = InsureHandlePersonVo.mockData();
		System.out.println(JSON.toJSONString(insureHanlePersonVos));
	}

}
