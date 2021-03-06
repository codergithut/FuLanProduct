package fulan.tianjian.demo.quartz;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@DisallowConcurrentExecution
public class RestClient implements Job{
	
	private static final String defaultUrl = "http://localhost:8080/quartz/task";
	
	private static RestTemplate restTemplate;
	

	private static CronInstanceCurd cronInstanceCurd;
	

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		String url = dataMap.getString("url");
		String params = dataMap.getString("params");
		String jobId = dataMap.getString("jobId");
		String jobInsId = UUID.randomUUID().toString();
		String cronName = dataMap.getString("cronName");
		String cronGroup = dataMap.getString("cronGroup");
		String cronMetadataId = dataMap.getString("cronMetadataId");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("jobId", jobId);
		headers.add("Content-Type", "application/json");
		headers.add("jobInsId", jobInsId);
		
		QuartzClientRequest quartzClientRequest = new QuartzClientRequest();
		
		quartzClientRequest.setJobId(jobId);
		quartzClientRequest.setJobInsId(jobInsId);
		quartzClientRequest.setParams(params);
		quartzClientRequest.setCronGroup(cronGroup);
		quartzClientRequest.setCronName(cronName);
		quartzClientRequest.setCronMetadataId(cronMetadataId);
		
		HttpEntity<QuartzClientRequest> httpEntity = new HttpEntity<>(quartzClientRequest, headers);
		
		if(StringUtils.isNoneBlank(url)) {
			url = defaultUrl;
		}

		ResponseEntity<QuartzServerResponse> s = restTemplate.postForEntity(url, httpEntity, QuartzServerResponse.class);
		
		CronInstanceEo cronInstanceEo = new CronInstanceEo();
		cronInstanceEo.setJobInsCode(jobInsId);
		cronInstanceEo.setCronMetadataId(cronMetadataId);
		cronInstanceEo.setCronMetadataId(cronMetadataId);
		if(s.getBody().isSuccess()) {
			cronInstanceEo.setStage("send");
			
		} else {
			cronInstanceEo.setStage("unSend");
		}
		cronInstanceCurd.save(cronInstanceEo);
		System.out.println(s);
		
	}

	public static void setRestTemplate(RestTemplate restTemplate) {
		RestClient.restTemplate = restTemplate;
	}

	public static void setCronInstanceCurd(CronInstanceCurd cronInstanceCurd) {
		RestClient.cronInstanceCurd = cronInstanceCurd;
	}
	
	
	
	




	
	

}
