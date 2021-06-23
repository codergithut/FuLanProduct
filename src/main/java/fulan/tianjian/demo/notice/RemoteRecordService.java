package fulan.tianjian.demo.notice;

import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.notice.database.RemoteRecordDataService;

public class RemoteRecordService {
	
	@Autowired
	private RemoteRecordDataService remoteRecordDataService;
	
	public <T> void saveFailRemoteRecord(MyRestValueModel<T> failRestValue) {
		
		remoteRecordDataService.saveRemoteRecordData(failRestValue.convertToRemoteRecordEo());
	
	}

}
