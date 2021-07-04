package fulan.tianjian.demo.client;

import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.model.client.RemoteRecordCurd;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.rest.RestValueLogCurd;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyRestValueModelListener<T> implements ApplicationListener<MyRestValueModel<T>> {
	
	@Autowired
	private RestValueLogCurd restValueLogCurd;
	
	@Autowired
	private RemoteRecordCurd remoteRecordCurd;
	
	
	private static List<String> retryUrls;
	
	
	static {
		retryUrls = new ArrayList<String>();
		retryUrls.add(ConstantCls.SYSNCHRO_URL);
	}
	

	
    @Override
    public void onApplicationEvent(MyRestValueModel<T> myRestValueModel) {
    	
    	//三方请求录入mongo数据库
    	restValueLogCurd.save(myRestValueModel.convertToLog());
    	
    	//需要重试请求的url并且请求未能成功入重试请求库以便重试请求
    	if(retryUrls.contains(myRestValueModel.getUrl())) {
    		if(!"0000".equals(myRestValueModel.getStatus())) {
    			remoteRecordCurd.save(myRestValueModel.convertToRemoteRecordEo());
    		}
    	} 
    	
    }
}
