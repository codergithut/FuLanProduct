package fulan.tianjian.demo.quartz;

import org.springframework.scheduling.annotation.Async;

public interface TaskExecute {
	
    String getQuartzTaskKey();
	
    @Async
	Boolean quartzTask(String params);

}
