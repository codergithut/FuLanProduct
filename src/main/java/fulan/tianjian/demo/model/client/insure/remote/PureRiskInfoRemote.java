package fulan.tianjian.demo.model.client.insure.remote;


import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.database.PureRiskEo;

public class PureRiskInfoRemote {
	
private String pureRiskInfo;
	
	
    public String getPureRiskInfo() {
		return pureRiskInfo;
	}



	public void setPureRiskInfo(String pureRiskInfo) {
		this.pureRiskInfo = pureRiskInfo;
	}

    public PureRiskEo createPureRiskEoByPureRiskInfoRemote(){
        PureRiskEo pureRiskEo = new PureRiskEo();
        BeanUtils.copyProperties(this, pureRiskEo);
        return pureRiskEo;
    }
}
