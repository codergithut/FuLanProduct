package fulan.tianjian.demo.model.client.insure.remote;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.PolicySchemeDTO;
import fulan.tianjian.demo.util.CommonUtil;

/**
 * 订单保险方案数据
 */
public class PolicySchemeRemote {

    /**
     * 保额
     */
    private BigDecimal premium;

    /**
     * 保费
     */
    private BigDecimal sumInsured;

    /**
     * 保险代码
     */
    private String policyCode;

    /**
     * 保险名称
     */
    private String policyName;

    /**
     * 起始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;
    
    /**
     * 送的次数
     */
    private String policyNumber;
    
    /**
     * 挡位
     */
    private String policyGears;


	public String getPolicyCode() {
		return policyCode;
	}

	public void setPolicyCode(String policyCode) {
		this.policyCode = policyCode;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}


	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyGears() {
		return policyGears;
	}

	public void setPolicyGears(String policyGears) {
		this.policyGears = policyGears;
	}
	
	
	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(BigDecimal sumInsured) {
		this.sumInsured = sumInsured;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public PolicySchemeDTO convertToDTO() {
		PolicySchemeDTO policySchemeDTO = new PolicySchemeDTO();
		BeanUtils.copyProperties(this, policySchemeDTO);
		return policySchemeDTO;
	}

	public static List<PolicySchemeRemote> mockPolicy() {
		List<PolicySchemeRemote> policySchemeRemotes = new ArrayList<PolicySchemeRemote>();
		
		Date endDate = CommonUtil.strToDate("20210625000000");
		
		Date startDate = CommonUtil.strToDate("20200625000000");
		
		PolicySchemeRemote trafficPolicy = new PolicySchemeRemote();
		trafficPolicy.setEndDate(endDate);
		trafficPolicy.setStartDate(startDate);
		trafficPolicy.setPolicyCode("TRAFFIC2020");
		trafficPolicy.setPolicyName("车辆交强险");
		trafficPolicy.setPremium(new BigDecimal("1110"));
		trafficPolicy.setSumInsured(null);
		policySchemeRemotes.add(trafficPolicy);
		
		PolicySchemeRemote businessPolicy = new PolicySchemeRemote();
		businessPolicy.setEndDate(endDate);
		businessPolicy.setStartDate(startDate);
		businessPolicy.setPolicyCode("BUSINESS2020");
		businessPolicy.setPolicyName("车辆商业险");
		businessPolicy.setPremium(new BigDecimal("3000"));
		businessPolicy.setSumInsured(new BigDecimal("150000"));
		policySchemeRemotes.add(businessPolicy);
		
		PolicySchemeRemote vehicleDamageInsurance = new PolicySchemeRemote();
		vehicleDamageInsurance.setPolicyCode("VEHICLEDAMAGEINSURANCE");
		vehicleDamageInsurance.setPolicyName("车损险");
		vehicleDamageInsurance.setPremium(new BigDecimal("1000"));
		vehicleDamageInsurance.setSumInsured(new BigDecimal("100000"));
		policySchemeRemotes.add(businessPolicy);
		
		PolicySchemeRemote motorVehicleThirdPartyInsurance = new PolicySchemeRemote();
		motorVehicleThirdPartyInsurance.setPolicyCode("MOTORVEHICLETHIRDPARTYINSURANCE");
		motorVehicleThirdPartyInsurance.setPolicyName("车辆三责任险");
		motorVehicleThirdPartyInsurance.setPremium(new BigDecimal("2000"));
		motorVehicleThirdPartyInsurance.setSumInsured(new BigDecimal("50000"));
		policySchemeRemotes.add(motorVehicleThirdPartyInsurance);
		
		return policySchemeRemotes;
	}
	
	
    
    


}
