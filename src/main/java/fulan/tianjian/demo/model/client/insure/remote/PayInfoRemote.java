package fulan.tianjian.demo.model.client.insure.remote;

/**
 * 支付信息
 */
public class PayInfoRemote {

    /**
     * 支付号
     */
    private String payNo;

    /**
     * 订单号
     */
    private String businessId;

    /**
     * 投保单号
     */
    private String insurancePolicyNumber;

    /**
     * 支付方式 0 支付宝 1 微信 2 银行支付
     */
    private String payWay;

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
    
    
}
