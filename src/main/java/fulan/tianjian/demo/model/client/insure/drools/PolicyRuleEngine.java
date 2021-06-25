package fulan.tianjian.demo.model.client.insure.drools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

public class PolicyRuleEngine {
	
	/**
	 * 车龄
	 */
	private int carAge;
	
	/**
	 * 是否新能源车
	 */
	private boolean isNewEnergy;
	
	/**
	 * 座位数
	 */
	private int seat;
	
	 /**
     * 地区编码
     */
    private String regionCode;
	
	private VehicleRemote vehicleRemote;
	
	private List<GivingPolicy> givingPolicys = new ArrayList<GivingPolicy>();
	
	private List<String> productCodes = new ArrayList<String>();
	
	public void saveGivingPolicy(String policyName, String policyCode, String policyNumber, String policyGears) {
		GivingPolicy givingPolicy = new GivingPolicy();
		givingPolicy.setPolicyCode(policyCode);
		givingPolicy.setPolicyName(policyName);
		givingPolicy.setPolicyGears(policyGears);
		givingPolicy.setPolicyNumber(policyNumber);
		givingPolicys.add(givingPolicy);
	}
	
	public boolean initPolicyRuleData(VehicleRemote vehicleRemote, List<String> productCodes, String regionCode) {
		
		if(vehicleRemote == null || CollectionUtils.isEmpty(productCodes)) {
			return false;
		}
		
		this.regionCode = regionCode;
		this.setVehicleRemote(vehicleRemote);
		this.setProductCodes(productCodes);
		
		carAge = 6;
		Date register = vehicleRemote.getRegisterDate();
		if(register != null) {
			carAge = 3;
		}
		
		isNewEnergy = false;
		if("1".equals(vehicleRemote.getFuelType())) {
			isNewEnergy = true;
		}
		
		seat = vehicleRemote.getSeat();
		
		return true;
		
	}
	
	public Boolean containProductCode(String productCode) {
		return productCodes.contains(productCode);
	}


	public void setCarAge(int carAge) {
		this.carAge = carAge;
	}


	public void setNewEnergy(boolean isNewEnergy) {
		this.isNewEnergy = isNewEnergy;
	}


	public void setSeat(int seat) {
		this.seat = seat;
	}


	public List<GivingPolicy> getGivingPolicys() {
		return givingPolicys;
	}


	public void setGivingPolicys(List<GivingPolicy> givingPolicys) {
		this.givingPolicys = givingPolicys;
	}

	public VehicleRemote getVehicleRemote() {
		return vehicleRemote;
	}

	public void setVehicleRemote(VehicleRemote vehicleRemote) {
		this.vehicleRemote = vehicleRemote;
	}

	public int getCarAge() {
		return carAge;
	}

	public boolean isNewEnergy() {
		return isNewEnergy;
	}

	public int getSeat() {
		return seat;
	}

	public List<String> getProductCodes() {
		return productCodes;
	}

	public void setProductCodes(List<String> productCodes) {
		this.productCodes = productCodes;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	
}
