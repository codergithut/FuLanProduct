package fulan.tianjian.demo.model.client.order;

import fulan.tianjian.demo.model.client.insure.remote.InsureConfigRemote;
import fulan.tianjian.demo.model.client.insure.remote.InsurePersonRemote;
import fulan.tianjian.demo.model.client.insure.remote.PolicySchemeRemote;
import fulan.tianjian.demo.model.client.insure.remote.PureRiskInfoRemote;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

public class OrderCenterVo {
	
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
    private PolicySchemeRemote policySchemeRemote;

    /**
     * 存风险保费
     */
    private PureRiskInfoRemote pureRiskInfoRemote;

    /**
     * 当前保单车辆数据
     */
    private VehicleRemote vehicleRemote;
    
    /**
     * 系统返回编码
     */
    private String code;
   
    /**
     * 订单中心id
     */
    private String orderCenterCode;
    
    /**
     * 地区编码
     */
    private String regionCode;

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

	public PolicySchemeRemote getPolicySchemeRemote() {
		return policySchemeRemote;
	}

	public void setPolicySchemeRemote(PolicySchemeRemote policySchemeRemote) {
		this.policySchemeRemote = policySchemeRemote;
	}

	public PureRiskInfoRemote getPureRiskInfoRemote() {
		return pureRiskInfoRemote;
	}

	public void setPureRiskInfoRemote(PureRiskInfoRemote pureRiskInfoRemote) {
		this.pureRiskInfoRemote = pureRiskInfoRemote;
	}

	public VehicleRemote getVehicleRemote() {
		return vehicleRemote;
	}

	public void setVehicleRemote(VehicleRemote vehicleRemote) {
		this.vehicleRemote = vehicleRemote;
	}

	public String getOrderCenterCode() {
		return orderCenterCode;
	}

	public void setOrderCenterCode(String orderCenterCode) {
		this.orderCenterCode = orderCenterCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	
    
	
    
    

}
