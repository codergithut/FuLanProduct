package fulan.tianjian.demo.model.client.database;

import fulan.tianjian.demo.model.client.remote.PureRiskInfoRemote;
import org.springframework.beans.BeanUtils;

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
