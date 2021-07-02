package fulan.tianjian.demo.quartz;

import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.exception.DrlResourceEmptyException;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.web.ResponseValue;

@RestController
@RequestMapping("/quartz")
public class QuartzController {
	
	@Autowired
	private QuartzDataService quartzDataService;
	
	@Autowired 
	private QuartzManage quartzManage;
	
	@Autowired
	private QuartzTaskServiceProxy quartzTaskServiceProxy;
	
	@Autowired
	private List<TaskExecute> taskExecutes;
	
	
	
	
	
	@PostMapping("addQuartz")
	public ResponseValue<Boolean> saveDrlResource(@RequestBody CronMetadataEo cronMetadata) throws PureRiskLossException, DrlResourceEmptyException {
		cronMetadata.setCronMetadataId(null);
		quartzDataService.saveQuartzData(cronMetadata);
		return ResponseValue.successResponse(true);
	}
	
	@GetMapping("findQuartz")
	public ResponseValue<List<CronMetadataEo>> findAllQuartz() {
		List<CronMetadataEo> quartzDatas = quartzDataService.findAllQuartzData();
		return ResponseValue.successResponse(quartzDatas);
	}
	
	@GetMapping("startQuartz")
	public ResponseValue<Boolean> startAllQuartz() throws SchedulerException {
		quartzManage.initQuatzJob();
		return ResponseValue.successResponse(true);
	}
	
	@PostMapping("task")
	public ResponseValue<QuartzServerResponse> startTask(@RequestBody QuartzClientRequest quartzClientRequest) throws SchedulerException {
		//实际任务处理
		System.out.println("我已经在执行任务拉"); 
		
		TaskExecute taskExecute = getTaskExecuteByCronNameAndCronGroup(quartzClientRequest.getCronName(), 
				quartzClientRequest.getCronGroup());
		QuartzServerResponse quartzServerResponse = quartzTaskServiceProxy.execute(quartzClientRequest.getParams(), taskExecute);
		
		return ResponseValue.successResponse(quartzServerResponse);
	}
	
	public static void main(String[] args) {
		CronMetadataEo cronMetadataEo = new CronMetadataEo();
		cronMetadataEo.setCron("*/5 * * * * ?");
		cronMetadataEo.setCronGroup("test_group_cron");
		cronMetadataEo.setCronName("test_name_cron");
		cronMetadataEo.setParams("ss");
		cronMetadataEo.setUrl("www.baidu.com");
		System.out.println(JSON.toJSONString(cronMetadataEo));
	}
	
	private TaskExecute getTaskExecuteByCronNameAndCronGroup(String cronName, String cronGroup) {
		if(CollectionUtils.isEmpty(taskExecutes)) {
			return null;
		}
		
		String key = cronName + ":" + cronGroup;
		
		for(TaskExecute detail : taskExecutes) {
			if(key.equals(detail.getQuartzTaskKey())) {
				return detail;
			}
		}
		return null;
	}

}
