package fulan.tianjian.demo.web.controller.server;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.vo.VehicleVo;
import fulan.tianjian.demo.web.service.server.VehicleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by tianjian on 2021/6/20.
 */
public class VehicleController {
	
	@Autowired
	private VehicleService vehicleService;

	/**
	 * 按照某个用户获取去车辆列表数据
	 * @param identityCardNumber
	 * @return
	 */
    public ResponseValue<List<VehicleVo>> getAllVehicle(String identityCardNumber) {
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
    public ResponseValue<Boolean> deleteVehicle(String orderNumber) {
    	Boolean result = vehicleService.deleteVehicleByOrderNumber(orderNumber);
    	return null;
    }
    
    /**
     * 获取某个订单车数据
     * @param orderNumber 订单编号
     * @return
     */
    public ResponseValue<VehicleVo> getVehicleByOrderNumber(String orderNumber) {
    	VehicleVo vehicleVo = vehicleService.findVehicleByOrderNumber(orderNumber);
    	return ResponseValue.successResponse(vehicleVo);
    }
    

}
 