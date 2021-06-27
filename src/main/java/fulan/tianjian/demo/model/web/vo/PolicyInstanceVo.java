package fulan.tianjian.demo.model.web.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.client.insure.dto.PolicySchemeDTO;
import fulan.tianjian.demo.model.client.insure.remote.PolicySchemeRemote;
import fulan.tianjian.demo.model.web.eo.PolicyInstanceEo;
import fulan.tianjian.demo.util.CommonUtil;

public class PolicyInstanceVo {
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
	 * 保险类型
	 */
	private String policyType;

	/**
	 * 订单id
	 */
	private String orderNumber;


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

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
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
	
	public PolicyInstanceEo convertToEo() {
		PolicyInstanceEo policyInstanceEo = new PolicyInstanceEo();
		BeanUtils.copyProperties(this, policyInstanceEo);
		return policyInstanceEo;
	}

	public static List<PolicyInstanceVo> mockPolicyInstance(String orderNumber) {

		List<PolicyInstanceVo> policyInstancVos = new ArrayList<PolicyInstanceVo>();

		Date endDate = CommonUtil.strToDate("20210625000000");

		Date startDate = CommonUtil.strToDate("20200625000000");

		PolicyInstanceVo trafficPolicy = new PolicyInstanceVo();
		trafficPolicy.setEndDate(endDate);
		trafficPolicy.setStartDate(startDate);
		trafficPolicy.setPolicyCode("TRAFFIC2020");
		trafficPolicy.setPolicyName("车辆交强险");
		trafficPolicy.setPremium(new BigDecimal("1110"));
		trafficPolicy.setSumInsured(null);
		trafficPolicy.setOrderNumber(orderNumber);
		policyInstancVos.add(trafficPolicy);

		PolicyInstanceVo businessPolicy = new PolicyInstanceVo();
		businessPolicy.setEndDate(endDate);
		businessPolicy.setStartDate(startDate);
		businessPolicy.setPolicyCode("BUSINESS2020");
		businessPolicy.setPolicyName("车辆商业险");
		businessPolicy.setPremium(new BigDecimal("3000"));
		businessPolicy.setSumInsured(new BigDecimal("150000"));
		businessPolicy.setOrderNumber(orderNumber);
		policyInstancVos.add(businessPolicy);

		PolicyInstanceVo vehicleDamageInsurance = new PolicyInstanceVo();
		vehicleDamageInsurance.setPolicyCode("VEHICLEDAMAGEINSURANCE");
		vehicleDamageInsurance.setPolicyName("车损险");
		vehicleDamageInsurance.setPremium(new BigDecimal("1000"));
		vehicleDamageInsurance.setSumInsured(new BigDecimal("100000"));
		vehicleDamageInsurance.setOrderNumber(orderNumber);
		policyInstancVos.add(vehicleDamageInsurance);

		PolicyInstanceVo motorVehicleThirdPartyInsurance = new PolicyInstanceVo();
		motorVehicleThirdPartyInsurance.setPolicyCode("MOTORVEHICLETHIRDPARTYINSURANCE");
		motorVehicleThirdPartyInsurance.setPolicyName("车辆三责任险");
		motorVehicleThirdPartyInsurance.setPremium(new BigDecimal("2000"));
		motorVehicleThirdPartyInsurance.setSumInsured(new BigDecimal("50000"));
		motorVehicleThirdPartyInsurance.setOrderNumber(orderNumber);
		policyInstancVos.add(motorVehicleThirdPartyInsurance);

		return policyInstancVos;

	}
	
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(mockPolicyInstance(UUID.randomUUID().toString())));
	}

}
