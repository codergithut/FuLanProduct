package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.PureRiskInfoRemote;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PureRiskEo {
    public PureRiskInfoRemote createPureRiskInfoRemoteByEo() {
        PureRiskInfoRemote pureRiskInfoRemote = new PureRiskInfoRemote();
        BeanUtils.copyProperties(this, pureRiskInfoRemote);
        return pureRiskInfoRemote;
    }
}
