package fulan.tianjian.demo.model.client.insure.remote;


import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.database.PureRiskEo;

public class PureRiskInfoRemote {
    public PureRiskEo createPureRiskEoByPureRiskInfoRemote(){
        PureRiskEo pureRiskEo = new PureRiskEo();
        BeanUtils.copyProperties(this, pureRiskEo);
        return pureRiskEo;
    }
}
