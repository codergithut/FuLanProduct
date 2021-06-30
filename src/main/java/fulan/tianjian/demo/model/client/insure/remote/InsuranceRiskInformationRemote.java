package fulan.tianjian.demo.model.client.insure.remote;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationEo;

/**
 * Created by tianjian on 2021/6/20.
 */
public class InsuranceRiskInformationRemote {
	
	private String simpleInsuranceInfo;

	public String getSimpleInsuranceInfo() {
		return simpleInsuranceInfo;
	}

	public void setSimpleInsuranceInfo(String simpleInsuranceInfo) {
		this.simpleInsuranceInfo = simpleInsuranceInfo;
	}
	
	public InsuranceRiskInformationEo convertToEo(String orderNumber) {
		InsuranceRiskInformationEo insuranceRiskInformationEo = new InsuranceRiskInformationEo();
		BeanUtils.copyProperties(this, insuranceRiskInformationEo);
		insuranceRiskInformationEo.setOrderNumber(orderNumber);
		return insuranceRiskInformationEo;
		
	}
	
	
}
