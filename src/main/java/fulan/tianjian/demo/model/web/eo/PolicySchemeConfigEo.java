package fulan.tianjian.demo.model.web.eo;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;

/**
 * Created by tianjian on 2021/6/20.
 */
@Entity
@Table(name = "policy_scheme_config")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PolicySchemeConfigEo {

	@Id
	@GeneratedValue(generator = "jpa-uuid")
    private String id;

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
     * 保险类型
     */
    private String policyType;

    /**
     * 地区编码
     */
    private String regionCode;
    
    /**
     * 是否默认选中
     */
    private String isSelected;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	

	public BigDecimal getSumInsured() {
		return sumInsured;
	}

	public void setSumInsured(BigDecimal sumInsured) {
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
    
	public PolicySchemeConfigVo convertToVo() {
		PolicySchemeConfigVo policySchemeConfigVo = new PolicySchemeConfigVo();
		BeanUtils.copyProperties(this, policySchemeConfigVo);
		return policySchemeConfigVo;
	}
}
