package fulan.tianjian.demo.quartz;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
	private TaskExecuteFactory taskExecuteFactory;
	
	
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
	public QuartzServerResponse startTask(@RequestBody QuartzClientRequest quartzClientRequest) throws SchedulerException, InterruptedException, ExecutionException {
		//实际任务处理
		System.out.println("我已经在执行任务拉"); 
		TaskExecute taskExecute = taskExecuteFactory.createTaskExecuteByKey(quartzClientRequest.getCronName() + ":" +quartzClientRequest.getCronGroup());
		Boolean result = taskExecute.quartzTask(quartzClientRequest);
		if(!result) {
			return QuartzServerResponse.failResponse();
		}
		
		return QuartzServerResponse.sucessResponse();
	}


}
