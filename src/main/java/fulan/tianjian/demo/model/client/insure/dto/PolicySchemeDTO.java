package fulan.tianjian.demo.model.client.insure.dto;

import java.util.List;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.web.server.vo.PolicyInstanceVo;

/**
 * 订单保险方案数据
 */
public class PolicySchemeDTO {

    /**
     * 保额
     */
    private String premium;

    /**
     * 保费
     */
    private String sumInsured;

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
    private String startDate;

    /**
     * 结束时间
     */
    private String endData;

    /**
     * 子险
     */
    private List<PolicySchemeDTO> subInsurancePolicySchemes;
    
    
    
    
    public String getPremium() {
		return premium;
	}




	public void setPremium(String premium) {
		this.premium = premium;
	}




	public String getSumInsured() {
		return sumInsured;
	}




	public void setSumInsured(String sumInsured) {
		this.sumInsured = sumInsured;
	}




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




	public String getStartDate() {
		return startDate;
	}




	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}




	public String getEndData() {
		return endData;
	}




	public void setEndData(String endData) {
		this.endData = endData;
	}




	public List<PolicySchemeDTO> getSubInsurancePolicySchemes() {
		return subInsurancePolicySchemes;
	}




	public void setSubInsurancePolicySchemes(List<PolicySchemeDTO> subInsurancePolicySchemes) {
		this.subInsurancePolicySchemes = subInsurancePolicySchemes;
	}




	public PolicyInstanceVo convertToVo() {
    	PolicyInstanceVo policyInstanceVo = new PolicyInstanceVo();
    	BeanUtils.copyProperties(this, policyInstanceVo);
    	return policyInstanceVo;
    }


}
