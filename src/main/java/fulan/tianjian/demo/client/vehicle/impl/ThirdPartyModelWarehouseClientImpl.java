package fulan.tianjian.demo.client.vehicle.impl;

import org.springframework.stereotype.Service;

import fulan.tianjian.demo.client.vehicle.ThirdPartyModelWarehouseClient;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;

@Service
public class ThirdPartyModelWarehouseClientImpl implements ThirdPartyModelWarehouseClient{
	

	@Override
	public ThirdPartyVehicle getVehicleTemplateDTO(String vehicleCode) {
		return ThirdPartyVehicle.mockThirdPartyVehicle(vehicleCode);
	}
	
}
