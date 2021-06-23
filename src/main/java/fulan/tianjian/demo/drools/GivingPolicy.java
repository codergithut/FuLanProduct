package fulan.tianjian.demo.drools;

/**
 * 赠送险种
 * @author 14681
 *
 */
public class GivingPolicy {
	
	/**
     * 保险代码
     */
    private String policyCode;

    /**
     * 保险名称
     */
    private String policyName;
    
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
    
   
}
