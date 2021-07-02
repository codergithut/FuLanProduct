package fulan.tianjian.demo.quartz;

import org.springframework.stereotype.Service;

@Service
public class QuartzTaskServiceProxy{
	
	public QuartzServerResponse execute(String params, TaskExecute taskExecute) {
		if(taskExecute.quartzTask(params)) {
			return QuartzServerResponse.sucessResponse();
		}
		return null;
	}

}
