package fulan.tianjian.demo.model.client.insure.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.PolicySchemeRemote;
import fulan.tianjian.demo.model.web.server.vo.PolicyInstanceVo;

/**
 * 订单保险方案数据
 */
public class PolicySchemeDTO {

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
     * 子险
     */
    private List<PolicySchemeDTO> subInsurancePolicySchemes;
    
    
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


	public List<PolicySchemeDTO> getSubInsurancePolicySchemes() {
		return subInsurancePolicySchemes;
	}




	public void setSubInsurancePolicySchemes(List<PolicySchemeDTO> subInsurancePolicySchemes) {
		this.subInsurancePolicySchemes = subInsurancePolicySchemes;
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


	public PolicyInstanceVo convertToVo() {
    	PolicyInstanceVo policyInstanceVo = new PolicyInstanceVo();
    	BeanUtils.copyProperties(this, policyInstanceVo);
    	return policyInstanceVo;
    }
	
	public PolicySchemeRemote convertToRemote() {
		PolicySchemeRemote policySchemeRemote = new PolicySchemeRemote();
		BeanUtils.copyProperties(this, policySchemeRemote);
		return policySchemeRemote;
		
	}


}
