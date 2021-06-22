package fulan.tianjian.demo.client.sms;

import fulan.tianjian.demo.client.AbstractHttpClient;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.sms.SmsModel;

import static fulan.tianjian.demo.constant.ConstantCls.SMS_URL;

/**
 * Created by tianjian on 2021/6/20.
 */
public class SmsService extends AbstractHttpClient<SmsModel> {



    public boolean sendCheckCode(String phoneNumber, String checkCode) {
        MyRestValueModel<SmsModel> data = postRestResult(SMS_URL, checkCode, SmsModel.class);
        return "0000".equals(data.getStatus());
    }

    @Override
    public boolean analyseResult(SmsModel smsModel) {
        if("0000".equals(smsModel.getCode())) {
            return true;
        }
        return false;
    }

}
