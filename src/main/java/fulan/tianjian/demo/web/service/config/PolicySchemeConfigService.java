package fulan.tianjian.demo.web.service.config;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.web.eo.PolicySchemeConfigCurd;
import fulan.tianjian.demo.model.web.eo.PolicySchemeConfigEo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;

//todo 添加中间层做缓存 VO -> DTO -> EO
@Service
public class PolicySchemeConfigService {
	
	@Autowired
	private PolicySchemeConfigCurd policySchemeConfigCurd;
	
	public void savePolicyConfig(List<PolicySchemeConfigVo> policySchemeConfigVos) {
		
		
		if(CollectionUtils.isEmpty(policySchemeConfigVos)) {
			return ;
		}
		
		String regionCode = policySchemeConfigVos.get(0).getRegionCode();
		
		policySchemeConfigCurd.deleteByRegionCode(regionCode);
		List<PolicySchemeConfigEo> policySchemeConfigEos = policySchemeConfigVos.stream().map(e -> {
			return e.convertToEo();
		}).collect(Collectors.toList());
		policySchemeConfigCurd.saveAll(policySchemeConfigEos);
		
	}
	
	public List<PolicySchemeConfigVo> getPolicyConfig(String regionCode) {
		List<PolicySchemeConfigEo> policySchemeConfigs = policySchemeConfigCurd.findByRegionCode(regionCode);
		if(CollectionUtils.isEmpty(policySchemeConfigs)) {
			return new ArrayList<PolicySchemeConfigVo>();
		}
		return policySchemeConfigs.stream().map(e -> {
			return e.convertToVo();
		}).collect(Collectors.toList());
	}

}
