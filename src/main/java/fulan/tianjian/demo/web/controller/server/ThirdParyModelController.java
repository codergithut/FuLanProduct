package fulan.tianjian.demo.web.controller.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.web.ResponseValue;

@RestController
@RequestMapping("/third")
public class ThirdParyModelController {
	
	
	@Autowired
	private InsureClient insureClient;
	
	
	@GetMapping("findThirdVehicle")
	public ResponseValue<VehicleDetailEo> getThirdParyModel(String vehicleCode) {
		return ResponseValue.successResponse(insureClient.saveVehicleTemplate(vehicleCode));
	}

}
