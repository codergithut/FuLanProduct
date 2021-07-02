package fulan.tianjian.demo.quartz;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestQuartzTaskService implements TaskExecute{

	@Override
	public String getQuartzTaskKey() {
		// TODO Auto-generated method stub
		return "test_name_cron:test_group_cron";
	}

	@Override
	@Async
	public Boolean quartzTask(String params) {
		System.out.println("我又回来了啊哈哈");
		try {
			Thread.sleep(1000000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return true;
	}


}
