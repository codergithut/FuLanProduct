package fulan.tianjian.demo.model.client.insure.remote;

import java.util.List;

/**
 * 保险基础数据封装
 */
public class InsureRemote {

    /**
     * 当前保单配置信息
     */
    private InsureConfigRemote insureConfigRemote;

    /**
     * 当前投保人受益人车主信息
     */
    private InsurePersonRemote insurePersonRemote;

    /**
     * 当前订单保险方案
     */
    private List<PolicySchemeRemote> policySchemeRemotes;

    /**
     * 存风险保费
     */
    private PureRiskInfoRemote pureRiskInfoRemote;

    /**
     * 当前保单车辆数据
     */
    private VehicleRemote vehicleRemote;

    /**
     * 核心返回编码
     */
    private String resultCode;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 支付信息
     */
    private PayInfoRemote payInfoRemote;

    private PremiumFloatingItemsRemote premiumFloatingItemsRemote;

    private InsuranceRiskInformationRemote insuranceRiskInformationRemote;

    public InsureConfigRemote getInsureConfigRemote() {
        return insureConfigRemote;
    }

    public void setInsureConfigRemote(InsureConfigRemote insureConfigRemote) {
        this.insureConfigRemote = insureConfigRemote;
    }

    public InsurePersonRemote getInsurePersonRemote() {
        return insurePersonRemote;
    }

    public void setInsurePersonRemote(InsurePersonRemote insurePersonRemote) {
        this.insurePersonRemote = insurePersonRemote;
    }

    
    public List<PolicySchemeRemote> getPolicySchemeRemotes() {
		return policySchemeRemotes;
	}

	public void setPolicySchemeRemotes(List<PolicySchemeRemote> policySchemeRemotes) {
		this.policySchemeRemotes = policySchemeRemotes;
	}

	public VehicleRemote getVehicleRemote() {
        return vehicleRemote;
    }

    public void setVehicleRemote(VehicleRemote vehicleRemote) {
        this.vehicleRemote = vehicleRemote;
    }

    public PureRiskInfoRemote getPureRiskInfoRemote() {
        return pureRiskInfoRemote;
    }

    public void setPureRiskInfoRemote(PureRiskInfoRemote pureRiskInfoRemote) {
        this.pureRiskInfoRemote = pureRiskInfoRemote;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PayInfoRemote getPayInfoRemote() {
        return payInfoRemote;
    }

    public void setPayInfoRemote(PayInfoRemote payInfoRemote) {
        this.payInfoRemote = payInfoRemote;
    }

    public PremiumFloatingItemsRemote getPremiumFloatingItemsRemote() {
        return premiumFloatingItemsRemote;
    }

    public void setPremiumFloatingItemsRemote(PremiumFloatingItemsRemote premiumFloatingItemsRemote) {
        this.premiumFloatingItemsRemote = premiumFloatingItemsRemote;
    }

    public InsuranceRiskInformationRemote getInsuranceRiskInformationRemote() {
        return insuranceRiskInformationRemote;
    }

    public void setInsuranceRiskInformationRemote(InsuranceRiskInformationRemote insuranceRiskInformationRemote) {
        this.insuranceRiskInformationRemote = insuranceRiskInformationRemote;
    }
}
