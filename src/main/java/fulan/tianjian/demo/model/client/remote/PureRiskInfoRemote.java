package fulan.tianjian.demo.model.client.remote;


import fulan.tianjian.demo.model.client.database.PureRiskEo;
import org.springframework.beans.BeanUtils;

public class PureRiskInfoRemote {
    public PureRiskEo createPureRiskEoByPureRiskInfoRemote(){
        PureRiskEo pureRiskEo = new PureRiskEo();
        BeanUtils.copyProperties(this, pureRiskEo);
        return pureRiskEo;
    }
}
