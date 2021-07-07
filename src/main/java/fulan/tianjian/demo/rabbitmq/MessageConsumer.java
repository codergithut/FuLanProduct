package fulan.tianjian.demo.rabbitmq;

import java.util.ArrayList;
import java.util.List;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;

import fulan.tianjian.demo.client.HttpClient;
import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.model.client.RemoteRecordCurd;
import fulan.tianjian.demo.model.client.RemoteRecordEo;
import fulan.tianjian.demo.model.client.insure.notice.NoticeEsMessage;
import fulan.tianjian.demo.model.client.insure.notice.NoticeEsMessageCurd;
import fulan.tianjian.demo.model.client.insure.notice.NoticeMessage;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.rest.RestValueLogCurd;
import fulan.tianjian.demo.model.client.synchro.SynchroModel;

@Component
public class MessageConsumer {
	
	@Autowired
	private RestValueLogCurd restValueLogCurd;
	
	@Autowired
	private RemoteRecordCurd remoteRecordCurd;
	
	
	private static List<String> retryUrls;
	
	
	static {
		retryUrls = new ArrayList<String>();
		retryUrls.add(ConstantCls.SYSNCHRO_URL);
	}
	

	@RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME_REMOTE)
	public <T> void remoteMessage(Channel channel, Message message, @Payload MyRestValueModel<T> myRestValueModel) {
		
		//三方请求录入mongo数据库
    	restValueLogCurd.save(myRestValueModel.convertToLog());
    	
    	
    	//需要重试请求的url并且请求未能成功入重试请求库以便重试请求
    	if(retryUrls.contains(myRestValueModel.getUrl())) {
    		
    		
    		List<RemoteRecordEo> remoteRecordEos = remoteRecordCurd.findByMd5Value(myRestValueModel.getMd5Value());
    		
    		if("0000".equals(myRestValueModel.getStatus())) {
    			remoteRecordEos.forEach(e -> e.setIsSuccess("Y"));
    			remoteRecordCurd.saveAll(remoteRecordEos);
    		}
    		
    		
    		if(!"0000".equals(myRestValueModel.getStatus())) {
    			if(CollectionUtils.isEmpty(remoteRecordEos)) {
    				remoteRecordCurd.save(myRestValueModel.convertToRemoteRecordEo());
    			} else {
    				for(RemoteRecordEo remoteRecordEo : remoteRecordEos) {
    					remoteRecordEo.setRetryCount(remoteRecordEo.getRetryCount());
    				}
    				remoteRecordCurd.saveAll(remoteRecordEos);
    			}
    		}
    	} 
    	
    	
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {

		}
	}

	@Autowired
	private NoticeEsMessageCurd noticeEsMessageCurd;

	@Autowired
	private HttpClient httpClient;

	@RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME_RECORD)
	public void recordMessage(Channel channel, Message message, @Payload NoticeMessage result) {
		// 记录订单信息到搜索引擎便于分析订单行为
		NoticeEsMessage noticeEsMessage = result.convertToEs();

		noticeEsMessageCurd.save(noticeEsMessage);
		// 同步给第三方需要知道的系统
		
		httpClient.postRestResult(ConstantCls.SYSNCHRO_URL, JSON.toJSONString(result.mockToSynchroRequest()), 
				SynchroModel.class);
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {

		}
	}
	
	@RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME_DL)
	public void dlMessage(Channel channel, Message message) {
		System.out.println("哈哈 我收到消息拉");
		try {
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {

		}
	}
	
//	@RabbitListener(queues = QueueConstants.MESSAGE_QUEUE_NAME_DELAYED)
//	public void delayedMessage(Channel channel, Message message) {
//		System.out.println("哈哈 我收到消息拉 1");
//		try {
//			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//		} catch (Exception e) {
//
//		}
//	}

}