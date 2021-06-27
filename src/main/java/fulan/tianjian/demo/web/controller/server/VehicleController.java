package fulan.tianjian.demo.web.controller.server;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.VehicleVo;
import fulan.tianjian.demo.web.service.server.VehicleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianjian on 2021/6/20.
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;

	/**
	 * 按照某个用户获取去车辆列表数据
	 * @param identityCardNumber
	 * @return
	 */
	@RequestMapping("/getVehicleByUserId")
    public ResponseValue<List<VehicleVo>> getAllVehicle(@RequestParam String identityCardNumber) {
    	List<VehicleVo> result = vehicleService.findVehicleByIdCarNum(identityCardNumber);
    	if(result == null) {
    		return ResponseValue.failResponse();
    	}
        return ResponseValue.successResponse(result);
    }
    
    /**
     * 根据订单删除某辆车的数据
     * @param orderNumber
     * @return
     */
    @GetMapping("deleteVehicleByOrderNumber")
    public ResponseValue<Void> deleteVehicle(String orderNumber) {
    	vehicleService.deleteVehicleByOrderNumber(orderNumber);
    	return ResponseValue.successResponse();
    }
    
    /**
     * 获取某个订单车数据
     * @param orderNumber 订单编号
     * @return
     */
    @GetMapping("getVehicleByOrderNumber")
    public ResponseValue<VehicleVo> getVehicleByOrderNumber(@RequestParam String orderNumber) {
    	VehicleVo vehicleVo = vehicleService.findVehicleByOrderNumber(orderNumber);
    	return ResponseValue.successResponse(vehicleVo);
    }
    
    /**
     * 保存车辆数据
     * @param vehicleVo 前端车辆数据模型
     * @return 是否保存成功
     */
    @PostMapping("saveVehicle")
    public ResponseValue<Boolean> saveVehicle(@RequestBody VehicleVo vehicleVo) {
    	Boolean result = vehicleService.saveOrUpdateVehicle(vehicleVo);
    	return ResponseValue.successResponse(result);
    }

}
 