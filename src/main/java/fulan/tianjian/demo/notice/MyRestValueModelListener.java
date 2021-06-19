package fulan.tianjian.demo.notice;

import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class MyRestValueModelListener implements ApplicationListener<MyRestValueModel> {
    @Override
    public void onApplicationEvent(MyRestValueModel myRestValueModel) {
        System.out.println(myRestValueModel.toString());
    }
}
