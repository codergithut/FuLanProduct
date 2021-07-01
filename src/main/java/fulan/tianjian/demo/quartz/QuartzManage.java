package fulan.tianjian.demo.quartz;

import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Service
public class QuartzManage {
	
	@Autowired
	private CronMetadataCurd cronMetadataCurd;
	
	Scheduler scheduler = null;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostConstruct
	public void init() {
		RestClient.setRestTemplate(restTemplate);
	}

	
	public void initQuatzJob() throws SchedulerException {
		
		if(scheduler != null) {
			scheduler.clear();
		} else {
			 //创建一个scheduler
	        scheduler = StdSchedulerFactory.getDefaultScheduler();
		}
        
        List<CronMetadataEo> cronMetadataEos = cronMetadataCurd.findAll();
        
        if(CollectionUtils.isEmpty(cronMetadataEos)) {
        	return ;
        }
        
        for(CronMetadataEo cronMetadata : cronMetadataEos) {
        	addQuatzJob(cronMetadata);
        }
        scheduler.start();
	}
	
	public void refreshQuatzJob(CronMetadataEo cronMetadataEo) throws SchedulerException {
		JobKey jobKey = new JobKey(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup());
		scheduler.deleteJob(jobKey);
		addQuatzJob(cronMetadataEo);
	}
	
	private void addQuatzJob(CronMetadataEo cronMetadata) throws SchedulerException {
		Trigger trigger = createTriggerByCoronMetadata(cronMetadata);
    	JobDetail job = createJobDetailByCoronMetadata(cronMetadata);
    	scheduler.scheduleJob(job,trigger);
	}
	
	private Trigger createTriggerByCoronMetadata(CronMetadataEo cronMetadataEo) {
		
		TriggerBuilder<CronTrigger> trb = TriggerBuilder.newTrigger()
				.forJob(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup())
				.withSchedule(CronScheduleBuilder.cronSchedule(cronMetadataEo.getCron()));
		
		if(cronMetadataEo.getStartTime() != null && cronMetadataEo.getEndTime() != null) {
			trb.endAt(cronMetadataEo.getEndTime());
			trb.startAt(cronMetadataEo.getStartTime());
		}
		
		CronTrigger cronTrigger = trb.build();
		return cronTrigger;
		
	}
	
	private JobDetail createJobDetailByCoronMetadata(CronMetadataEo cronMetadataEo) {
		//创建一个job
        JobDetail job = JobBuilder.newJob(RestClient.class)
                    .usingJobData("url", cronMetadataEo.getUrl())
                    .usingJobData("parmas", cronMetadataEo.getParams())
                    .withIdentity(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup())
                    .build();
        return job;
		
	}

}
