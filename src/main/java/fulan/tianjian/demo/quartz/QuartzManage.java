package fulan.tianjian.demo.quartz;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

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
	
	public void pauseJob(String cronName, String cronGroup) throws SchedulerException {
		if(scheduler == null) {
			return ;
		}
		List<CronMetadataEo> crons = cronMetadataCurd.findByCronNameAndCronGroup(cronName, cronGroup);
		scheduler.pauseJob(new JobKey(cronName, cronGroup));
		crons.stream().forEach(e -> {
			e.setStatus("2");
		});
		cronMetadataCurd.saveAll(crons);
		
	}
	
	public void resumeJob(String cronName, String cronGroup) throws SchedulerException {
		if(scheduler == null) {
			return ;
		}
		
		scheduler.resumeJob(new JobKey(cronName, cronGroup));
		List<CronMetadataEo> crons = cronMetadataCurd.findByCronNameAndCronGroup(cronName, cronGroup);
		crons.stream().forEach(e -> {
			e.setStatus("1");
		});
		
	}
	
	@Transactional
	public void removeJob(String cronName, String cronGroup) throws SchedulerException {
		if(scheduler == null) {
			return ;
		}
		scheduler.deleteJob(new JobKey(cronName, cronGroup));
		cronMetadataCurd.deleteByCronNameAndCronGroup(cronName, cronGroup);
	}
	
	
	/**
	 * 根据元数据刷新已有的定时任务
	 * @param cronMetadataEo 元数据对象
	 * @throws SchedulerException
	 */
	@Transactional
	public void refreshQuatzJob(CronMetadataEo cronMetadataEo) throws SchedulerException {
		cronMetadataCurd.deleteByCronNameAndCronGroup(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup());
		cronMetadataEo.setCronMetadataId(null);
		cronMetadataCurd.save(cronMetadataEo);
		JobKey jobKey = new JobKey(cronMetadataEo.getCronName(), cronMetadataEo.getCronGroup());
		if(scheduler == null) {
			initQuatzJob();
		}
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
    	if("2".equals(cronMetadata.getStatus())) {
    		scheduler.pauseJob(new JobKey(cronMetadata.getCronName(), cronMetadata.getCronGroup()));
    	}
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
