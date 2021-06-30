package fulan.tianjian.demo.model.client.insure.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.InsuranceRiskInformationRemote;

/**
 * Created by tianjian on 2021/6/20.
 */
@Entity
@Table(name = "insurance_risk_information")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class InsuranceRiskInformationEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	private String simpleInsuranceInfo;
	
	private String orderNumber;
	
    public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getSimpleInsuranceInfo() {
		return simpleInsuranceInfo;
	}




	public void setSimpleInsuranceInfo(String simpleInsuranceInfo) {
		this.simpleInsuranceInfo = simpleInsuranceInfo;
	}




	public String getOrderNumber() {
		return orderNumber;
	}




	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}




	public InsuranceRiskInformationRemote convertEoToRemote() {
        InsuranceRiskInformationRemote insuranceRiskInformationRemote = new InsuranceRiskInformationRemote();
        BeanUtils.copyProperties(this, insuranceRiskInformationRemote);
        return insuranceRiskInformationRemote;
    }
}
