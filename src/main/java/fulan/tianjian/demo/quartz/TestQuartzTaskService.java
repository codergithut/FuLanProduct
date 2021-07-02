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
	public Boolean quartzTask(String params) {
		System.out.println("我又回来了啊哈哈");
		// TODO Auto-generated method stub
		return true;
	}


}
