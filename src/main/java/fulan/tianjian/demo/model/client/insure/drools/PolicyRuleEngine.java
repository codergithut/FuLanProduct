package fulan.tianjian.demo.model.client.insure.drools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

public class PolicyRuleEngine {
	
	private int carAge;
	
	private boolean isNewEnergy;
	
	private int seat;
	
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
	
	public boolean initPolicyRuleData(VehicleRemote vehicleRemote, List<String> productCodes) {
		
		if(vehicleRemote == null || CollectionUtils.isEmpty(productCodes)) {
			return false;
		}
		
		this.setVehicleRemote(vehicleRemote);
		this.setProductCodes(productCodes);
		
		carAge = 6;
		String register = vehicleRemote.getRegisterDate();
		if(StringUtils.isNoneBlank(register)) {
			carAge = 3;
		}
		
		isNewEnergy = false;
		if("1".equals(vehicleRemote.getFuelType())) {
			isNewEnergy = true;
		}
		
		seat = 6;
		if(StringUtils.isNoneBlank(vehicleRemote.getSeat())) {
			seat = 3;
		}
		
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
	
	
	
	
	
	
	
}
