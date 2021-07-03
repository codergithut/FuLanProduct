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
	private CronInstanceCurd cronInstanceCurd;
	
	@Autowired
	private RestTemplate restTemplate;
	
	/**
	 * 注入spring对象到静态属性中
	 */
	@PostConstruct
	public void init() {
		RestClient.setRestTemplate(restTemplate);
		RestClient.setCronInstanceCurd(cronInstanceCurd);
	}
	
	

	
	/**
	 * 初始化当前定时任务
	 * @throws SchedulerException
	 */
	public void initQuatzJob() throws SchedulerException {
		
		if(scheduler != null) {
			scheduler.clear();
		} else {
			 //创建一个scheduler
	        scheduler = StdSchedulerFactory.getDefaultScheduler();
		}
        
		//获取所有可以执行的任务
        List<CronMetadataEo> cronMetadataEos = cronMetadataCurd.findAll();
        
        if(CollectionUtils.isEmpty(cronMetadataEos)) {
        	return ;
        }
        
        for(CronMetadataEo cronMetadata : cronMetadataEos) {
        	addQuatzJob(cronMetadata);
        }
        scheduler.start();
	}
	
	
	/**
	 * 根据元数据刷新已有的定时任务
	 * @param cronMetadataEo 元数据对象
	 * @throws SchedulerException
	 */
	public void refreshQuatzJob(CronMetadataEo cronMetadataEo) throws SchedulerException {
		JobKey jobKey = new JobKey(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup());
		scheduler.deleteJob(jobKey);
		addQuatzJob(cronMetadataEo);
	}
	
	/**
	 * 添加定时任务
	 * @param cronMetadata 元数据对象
	 * @throws SchedulerException
	 */
	private void addQuatzJob(CronMetadataEo cronMetadata) throws SchedulerException {
		Trigger trigger = createTriggerByCoronMetadata(cronMetadata);
    	JobDetail job = createJobDetailByCoronMetadata(cronMetadata);
    	scheduler.scheduleJob(job,trigger);
	}
	
	
	/**
	 * 添加时间任务对象
	 * @param cronMetadataEo 元数据
	 * @return
	 */
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
	
	
	/**
	 * 添加任务对象
	 * @param cronMetadataEo 元数据
	 * @return
	 */
	private JobDetail createJobDetailByCoronMetadata(CronMetadataEo cronMetadataEo) {
		//创建一个job
        JobDetail job = JobBuilder.newJob(RestClient.class)
                    .usingJobData("url", cronMetadataEo.getUrl())
                    .usingJobData("parmas", cronMetadataEo.getParams())
                    .usingJobData("jobId", cronMetadataEo.getCronMetadataId())
                    .usingJobData("cronName", cronMetadataEo.getCronName())
                    .usingJobData("cronGroup", cronMetadataEo.getCronGroup())
                    .usingJobData("cronMetadataId", cronMetadataEo.getCronMetadataId())
                    .withIdentity(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup())
                    .build();
        return job;
		
	}
	

}
