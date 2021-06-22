package fulan.tianjian.demo.notice;

import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyRestValueModelListener<T> implements ApplicationListener<MyRestValueModel<T>> {
    @Override
    public void onApplicationEvent(MyRestValueModel<T> myRestValueModel) {
    	
    	if("0000".equals(myRestValueModel.getStatus())) {
    		System.out.println(myRestValueModel.toString());
    	}
    	
    }
}
