package fulan.tianjian.demo.model.web.eo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.web.vo.PolicyInstanceVo;

@Entity
@Table(name = "policy_instance")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PolicyInstanceEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	
	public PolicyInstanceVo convertToVo() {
		PolicyInstanceVo policyInstanceVo = new PolicyInstanceVo();
		BeanUtils.copyProperties(this, policyInstanceVo);
		return policyInstanceVo;
	}

}
