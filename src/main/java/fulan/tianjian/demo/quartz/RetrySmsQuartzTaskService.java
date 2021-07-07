package fulan.tianjian.demo.quartz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fulan.tianjian.demo.client.HttpClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.model.client.RemoteRecordCurd;
import fulan.tianjian.demo.model.client.RemoteRecordEo;
import fulan.tianjian.demo.model.client.sms.SmsModel;

import static fulan.tianjian.demo.constant.ConstantCls.SMS_URL;

import java.util.List;

@Service
public class RetrySmsQuartzTaskService implements TaskExecute{
	
	@Autowired
	private RemoteRecordCurd remoteRecordCurd;
	
	@Autowired
	private HttpClient httpClient;
	

	@Override
	public String getQuartzTaskKey() {
		// TODO Auto-generated method stub
		return "test_name_cron:test_group_cron";
	}

	@Override
	public Boolean quartzTask(QuartzClientRequest quartzClientRequest) {
		
		List<RemoteRecordEo> remoteRecords = remoteRecordCurd.findByUrlAndIsSuccess(SMS_URL, "N");
		
		for(RemoteRecordEo remoteRecord : remoteRecords) {
			httpClient.postRestResult(ConstantCls.ORDER_CENTER_URL, 
					remoteRecord.getParams(), SmsModel.class);
		}
		
		try {
			Thread.sleep(60000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}


}
