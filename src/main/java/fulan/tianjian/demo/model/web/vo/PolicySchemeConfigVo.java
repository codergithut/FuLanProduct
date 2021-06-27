package fulan.tianjian.demo.model.web.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.web.eo.PolicySchemeConfigEo;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PolicySchemeConfigVo {

    /**
     * 保额
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
     * 保险类型
     */
    private String policyType;

    /**
     * 地区编码
     */
    private String regionCode;
    
    /**
     * 是否某人选中
     */
    private String isSelected;

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

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(String isSelected) {
		this.isSelected = isSelected;
	}
	
	
	
	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(BigDecimal sumInsured) {
		this.sumInsured = sumInsured;
	}

	public PolicySchemeConfigEo convertToEo() {
		PolicySchemeConfigEo policySchemeConfigEo = new PolicySchemeConfigEo();
		BeanUtils.copyProperties(this, policySchemeConfigEo);
		return policySchemeConfigEo;
		
	}
	
	public static List<PolicySchemeConfigVo> mockPolicySchemeConfig() {
		
		List<PolicySchemeConfigVo> configPolicys = new ArrayList<PolicySchemeConfigVo>();
		
		
		PolicySchemeConfigVo trafficPolicy = new PolicySchemeConfigVo();
		trafficPolicy.setPolicyCode("TRAFFIC2020");
		trafficPolicy.setPolicyName("车辆交强险");
		trafficPolicy.setSumInsured(null);
		trafficPolicy.setIsSelected("Y");
		trafficPolicy.setRegionCode("215000");
		configPolicys.add(trafficPolicy);
		
		PolicySchemeConfigVo businessPolicy = new PolicySchemeConfigVo();
		businessPolicy.setPolicyCode("BUSINESS2020");
		businessPolicy.setPolicyName("车辆商业险");
		businessPolicy.setSumInsured(new BigDecimal("150000"));
		businessPolicy.setIsSelected("Y");
		businessPolicy.setRegionCode("215000");
		configPolicys.add(businessPolicy);
		
		PolicySchemeConfigVo vehicleDamageInsurance = new PolicySchemeConfigVo();
		vehicleDamageInsurance.setPolicyCode("VEHICLEDAMAGEINSURANCE");
		vehicleDamageInsurance.setPolicyName("车损险");
		vehicleDamageInsurance.setSumInsured(new BigDecimal("100000"));
		vehicleDamageInsurance.setIsSelected("Y");
		vehicleDamageInsurance.setRegionCode("215000");
		configPolicys.add(vehicleDamageInsurance);
		
		PolicySchemeConfigVo motorVehicleThirdPartyInsurance = new PolicySchemeConfigVo();
		motorVehicleThirdPartyInsurance.setPolicyCode("MOTORVEHICLETHIRDPARTYINSURANCE");
		motorVehicleThirdPartyInsurance.setPolicyName("车辆三责任险");
		motorVehicleThirdPartyInsurance.setSumInsured(new BigDecimal("50000"));
		motorVehicleThirdPartyInsurance.setIsSelected("N");
		motorVehicleThirdPartyInsurance.setRegionCode("215000");
		configPolicys.add(motorVehicleThirdPartyInsurance);
		
		return configPolicys;
		
	}
	
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(mockPolicySchemeConfig()));
	}
    
    
}
