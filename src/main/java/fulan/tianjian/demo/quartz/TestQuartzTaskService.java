package fulan.tianjian.demo.quartz;

import org.springframework.stereotype.Service;

@Service
public class TestQuartzTaskService implements TaskExecute{

	@Override
	public String getQuartzTaskKey() {
		// TODO Auto-generated method stub
		return "test_name_cron:test_group_cron";
	}

	@Override
	public Boolean quartzTask(QuartzClientRequest quartzClientRequest) {
		System.out.println("我又回来了啊哈哈");
		try {
			Thread.sleep(60000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}


}
