package fulan.tianjian.demo.web.controller.server;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.client.insure.dto.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;
import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.vo.VehicleBaseVo;

@RestController
@RequestMapping("/third")
public class VehicleModelController {
	
	
	@Autowired
	private InsureClient insureClient;
	
	
	@PostMapping("saveThirdVehicle")
	public ResponseValue<VehicleDetailEo> saveThirdParyModel(@RequestBody VehicleBaseVo vehicleBase) {
		return ResponseValue.successResponse(insureClient.saveVehicleTemplate(vehicleBase.getVehicleCode()));
	}
	
	@GetMapping("findVehicleCode")
	public ResponseValue<String> getThirdPartVehicleCode() {
		String vehicleCode = RandomStringUtils.random(6, true, true);
		return ResponseValue.successResponse(vehicleCode);
	}
	
	@PostMapping("saveTrafficVehicle")
	public ResponseValue<VehicleRemote> saveTrafficVehicle(@RequestBody VehicleBaseVo vehicleBase) {
		VehicleRemote vehicleRemote = insureClient.saveVehicleTrafficMangement(vehicleBase.getPlateNo(), vehicleBase.getVinCode(), vehicleBase.getEngineNo());
		return ResponseValue.successResponse(vehicleRemote);
	}
	
	@PostMapping("getReNewPolicy")
	public ResponseValue<InsureResultDTO> getReNewPolicy(@RequestBody VehicleBaseVo vehicleBase) {
		return ResponseValue.successResponse(insureClient.getRenewPolicy(vehicleBase.getPlateNo(), 
				vehicleBase.getVinCode(), vehicleBase.getEngineNo()));
		
	}
	
	

}
