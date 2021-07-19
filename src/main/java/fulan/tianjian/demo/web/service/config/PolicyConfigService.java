package fulan.tianjian.demo.web.service.config;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.model.web.eo.PolicySchemeConfigCurd;
import fulan.tianjian.demo.model.web.eo.PolicySchemeConfigEo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;

@Service("policyConfig")
public class PolicyConfigService extends GuavaCahceService<String, List<PolicySchemeConfigVo>>{
	

	@Autowired
	private PolicySchemeConfigCurd policySchemeConfigCurd;

	@Override
	public void saveCacheData(String key, List<PolicySchemeConfigVo> values) {
		List<PolicySchemeConfigEo> policySchemeConfieEos =  values.stream().map(e -> {
			return e.convertToEo();
		}).collect(Collectors.toList());
		policySchemeConfigCurd.saveAll(policySchemeConfieEos);
	}

	@Override
	public void deleteCacheData(String key) {
		policySchemeConfigCurd.deleteByRegionCode(key);
		
	}

	@Override
	public List<PolicySchemeConfigVo> findCacheData(String key) {
		List<PolicySchemeConfigEo> policySchemeConfigEos = policySchemeConfigCurd.findByRegionCode(key);
		if(CollectionUtils.isEmpty(policySchemeConfigEos)) {
			return null;
		}
		
		return policySchemeConfigEos.stream().map(e -> {
			return e.convertToVo();
		}).collect(Collectors.toList());
	}

}
