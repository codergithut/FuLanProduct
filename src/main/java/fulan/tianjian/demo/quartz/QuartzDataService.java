package fulan.tianjian.demo.quartz;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzDataService {
	
	@Autowired
	private CronMetadataCurd cronMetadataCurd;
	
	@Transactional
	public void saveQuartzData(CronMetadataEo cronMetadataEo) {
		String cronName = cronMetadataEo.getCronName();
		String cronGroup = cronMetadataEo.getCronGroup();
		cronMetadataCurd.deleteByCronNameAndCronGroup(cronName, cronGroup);
		cronMetadataCurd.save(cronMetadataEo);
		
	}

	public List<CronMetadataEo> findAllQuartzData() {
		// TODO Auto-generated method stub
		return cronMetadataCurd.findAll();
	}

}
