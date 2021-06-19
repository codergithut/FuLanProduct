package fulan.tianjian.demo.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class SendNoticeService<T extends ApplicationEvent> {
    @Autowired
    private ApplicationContext applicationContext;

    @Async
    public void publish(T t){
        applicationContext.publishEvent(t);//调用ApplicationContext的publishEvent方法来发布
    }
}
