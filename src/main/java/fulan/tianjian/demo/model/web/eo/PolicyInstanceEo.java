package fulan.tianjian.demo.model.web.eo;

public class PolicyInstanceEo {
	
	private String id;
	
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
    

}
