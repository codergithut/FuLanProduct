package fulan.tianjian.demo.drools;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;

public class PolicyRuleEngine {
	
	@SuppressWarnings("unused")
	private int carAge;
	
	@SuppressWarnings("unused")
	private boolean isNewEnergy;
	
	@SuppressWarnings("unused")
	private int seat;
	
	private VehicleDTO vehicleDTO;
	
	private List<GivingPolicy> givingPolicys = new ArrayList<GivingPolicy>();
	
	public void saveGivingPolicy(String policyName, String policyCode, String policyNumber, String policyGears) {
		GivingPolicy givingPolicy = new GivingPolicy();
		givingPolicy.setPolicyCode(policyCode);
		givingPolicy.setPolicyName(policyName);
		givingPolicy.setPolicyGears(policyGears);
		givingPolicy.setPolicyNumber(policyNumber);
		givingPolicys.add(givingPolicy);
	}


	public int getCarAge() {
		String register = vehicleDTO.getRegisterDate();
		if(StringUtils.isNoneBlank(register)) {
			return 3;
		}
		return 5;
	}
	
	public boolean isNewEnergy() {
		if("1".equals(vehicleDTO.getFuelType())) {
			return true;
		}
		return false;
	}
	
	public int getSeat() {
		if(StringUtils.isNoneBlank(vehicleDTO.getSeat())) {
			return 6;
		}
		return 3;
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


	public VehicleDTO getVehicleDTO() {
		return vehicleDTO;
	}


	public void setVehicleDTO(VehicleDTO vehicleDTO) {
		this.vehicleDTO = vehicleDTO;
	}


	public List<GivingPolicy> getGivingPolicys() {
		return givingPolicys;
	}


	public void setGivingPolicys(List<GivingPolicy> givingPolicys) {
		this.givingPolicys = givingPolicys;
	}
	
	
	
}
