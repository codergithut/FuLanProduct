package fulan.tianjian.demo.web.service.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.model.client.insure.dto.InsureConfigDTO;
import fulan.tianjian.demo.model.web.eo.InsureConfigCurd;
import fulan.tianjian.demo.model.web.eo.InsureConfigEo;

@Service("insureConfig")
public class InssureConfigService extends GuavaCahceService<String, InsureConfigDTO>{
	
	@Autowired
	private InsureConfigCurd insureConfigCurd;
	
    

	@Override
	public void saveCacheData(String key, InsureConfigDTO insureConfig) {
		
		insureConfigCurd.deleteByProvinceCode(key);
		
		InsureConfigEo insureConfigEo = insureConfig.convertToEo();
		
		insureConfigCurd.save(insureConfigEo);
		
	}

	@Override
	public void deleteCacheData(String key) {
		insureConfigCurd.deleteByProvinceCode(key);
	}

	@Override
	public InsureConfigDTO findCacheData(String key) {
		InsureConfigEo insureConfigEo = insureConfigCurd.findByProvinceCode(key);
		if(insureConfigEo != null) {
			return insureConfigEo.convertToDTO();
		}
		return null;
	}

}
