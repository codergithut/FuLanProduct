package fulan.tianjian.demo.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

@DisallowConcurrentExecution
public class RestClient implements Job{
	
	private static RestTemplate restTemplate;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String url = dataMap.getString("url");
		String params = dataMap.getString("params");
		String s = restTemplate.getForEntity(url,String.class).getBody();
		System.out.println(s);
		
	}

	public static void setRestTemplate(RestTemplate restTemplate) {
		RestClient.restTemplate = restTemplate;
	}
	
	




	
	

}
