package fulan.tianjian.demo.model.client.database;

import fulan.tianjian.demo.model.client.remote.InsuranceRiskInformationRemote;
import org.springframework.beans.BeanUtils;

/**
 * Created by tianjian on 2021/6/20.
 */
public class InsuranceRiskInformationEo {
    public InsuranceRiskInformationRemote convertEoToRemote() {
        InsuranceRiskInformationRemote insuranceRiskInformationRemote = new InsuranceRiskInformationRemote();
        BeanUtils.copyProperties(this, insuranceRiskInformationRemote);
        return insuranceRiskInformationRemote;
    }
}
