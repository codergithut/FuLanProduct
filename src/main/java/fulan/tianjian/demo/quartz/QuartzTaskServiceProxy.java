package fulan.tianjian.demo.quartz;

import java.util.concurrent.CompletableFuture;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuartzTaskServiceProxy{
	
	@Autowired
	private CronInstanceCurd cronInstanceCurd;
	
	/**
	 * 代理执行定时任务主要实现异步请求防止线程过多导致程序崩溃
	 * @param taskExecute 定时任务执行器
	 * @param quartzClientRequest 定时任务参数
	 * @return
	 */
	public QuartzServerResponse execute(TaskExecute taskExecute, QuartzClientRequest quartzClientRequest) {
		
		//插入数据表明定时任务已接受
		CronInstanceEo cronInstanceEo = new CronInstanceEo();
		cronInstanceEo.setCronMetadataId(quartzClientRequest.getCronMetadataId());
		cronInstanceEo.setStage("doing");
		cronInstanceCurd.save(cronInstanceEo);
		
		//异步调用请求任务 根据定时任务返回结果插入信息到定时任务执行表中
		CompletableFuture.supplyAsync(() -> {
			return taskExecute.quartzTask(quartzClientRequest);
		}).thenAccept((result) -> {
			CronInstanceEo v = new CronInstanceEo();
			v.setCronMetadataId(quartzClientRequest.getCronMetadataId());
			if(result) {
				v.setStage("finished");
			}else {
				v.setStage("unFinished");
			}
			cronInstanceCurd.save(v);
		});
		
		//通知客户端已经接受到请求消息
		return QuartzServerResponse.sucessResponse();
	}
	


}
