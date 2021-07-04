package fulan.tianjian.demo.client.insure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.client.synchro.SysnchroService;
import fulan.tianjian.demo.model.client.insure.notice.NoticeEsMessage;
import fulan.tianjian.demo.model.client.insure.notice.NoticeEsMessageCurd;
import fulan.tianjian.demo.model.client.insure.notice.NoticeMessage;

@Component
public class NoticeListener implements ApplicationListener<NoticeMessage>{
	
	@Autowired
	private NoticeEsMessageCurd noticeEsMessageCurd;
	
	@Autowired
	private SysnchroService sysnchroService;

	@Override
	public void onApplicationEvent(NoticeMessage event) {
		
		//记录订单信息到搜索引擎便于分析订单行为
		NoticeEsMessage noticeEsMessage = event.convertToEs();
		
		noticeEsMessageCurd.save(noticeEsMessage);
		//同步给第三方需要知道的系统
		sysnchroService.sysnchroData(JSON.toJSONString(event.mockToSynchroRequest()));
	}

}
