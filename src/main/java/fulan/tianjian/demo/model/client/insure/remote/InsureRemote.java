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
    private List<InsurePersonRemote> insurePersonRemotes;

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
     * 地区信息
     */
    private String regionCode;

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

   
    public List<InsurePersonRemote> getInsurePersonRemotes() {
		return insurePersonRemotes;
	}

	public void setInsurePersonRemotes(List<InsurePersonRemote> insurePersonRemotes) {
		this.insurePersonRemotes = insurePersonRemotes;
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
    
    
    
    public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public static InsureRemote mockTrafficInsureRemote() {
    	InsureRemote insureRemote = new InsureRemote();
    	insureRemote.setVehicleRemote(VehicleRemote.mockTrafficVehicle());
    	insureRemote.setResultCode("0000");
    	return insureRemote;
    }

	public static InsureRemote mockReNewPolicy() {
		InsureRemote insureRemote = new InsureRemote();
		insureRemote.setVehicleRemote(VehicleRemote.mockReNewVehicle());
		insureRemote.setPolicySchemeRemotes(PolicySchemeRemote.mockPolicy());
		insureRemote.setResultCode("0000");
		return insureRemote;
	}

	public static InsureRemote mockPureRiskInfo() {
		InsureRemote insureRemote = new InsureRemote();
		PureRiskInfoRemote pureRiskInfo = new PureRiskInfoRemote();
		pureRiskInfo.setPureRiskInfo("pureRiskInfo");
		insureRemote.setPureRiskInfoRemote(pureRiskInfo);
		insureRemote.setResultCode("0000");
		return insureRemote;
	}
}
