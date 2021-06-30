package fulan.tianjian.demo.web.service.config;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.cache.GuavaCahceService;
import fulan.tianjian.demo.model.client.insure.dto.InsureHandlePersonDTO;
import fulan.tianjian.demo.model.web.eo.InsureHandlePersonCurd;
import fulan.tianjian.demo.model.web.eo.InsureHandlePersonEo;

@Service("inusreConfigPerson")
public class InssureConfigPersonService extends GuavaCahceService<String, List<InsureHandlePersonDTO>>{
	
	@Autowired
	private InsureHandlePersonCurd insureHandlePersonCurd;

	@Override
	public void saveCacheData(String key, List<InsureHandlePersonDTO> insureHandlePersonDTOs) {
		
		if(CollectionUtils.isEmpty(insureHandlePersonDTOs)) {
			return ;
		}
		
		List<InsureHandlePersonEo> insureHandlePersonEos = insureHandlePersonDTOs.stream().map(e -> {
			return e.convertToEo();
		}).collect(Collectors.toList());
		
		insureHandlePersonCurd.saveAll(insureHandlePersonEos);
		
	}

	@Override
	public void deleteCacheData(String key) {
		
		insureHandlePersonCurd.deleteByRegionCode(key);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<InsureHandlePersonDTO> findCacheData(String key) {
	
		List<InsureHandlePersonEo> insureHandlePersonEos = insureHandlePersonCurd.findByRegionCode(key);
		
		if(CollectionUtils.isEmpty(insureHandlePersonEos)) {
			return null;
		}
		
		List<InsureHandlePersonDTO> insureHandlePersonVos = insureHandlePersonEos.stream().map(e -> {
			return e.convertToDTO();
		}).collect(Collectors.toList());
		
		return insureHandlePersonVos;
	}

	
	
	
	
	
	

}
