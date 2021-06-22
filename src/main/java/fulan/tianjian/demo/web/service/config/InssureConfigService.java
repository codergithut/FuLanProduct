package fulan.tianjian.demo.web.service.config;

import org.springframework.stereotype.Service;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.model.client.insure.InsureConfigDTO;

@Service("insureConfig")
public class InssureConfigService extends GuavaCahceService<String, InsureConfigDTO>{

	@Override
	public void saveCacheData(String key, InsureConfigDTO value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCacheData(String key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InsureConfigDTO findCacheData(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
