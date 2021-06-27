package fulan.tianjian.demo.web.controller.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.PolicyInstanceVo;
import fulan.tianjian.demo.web.service.server.OrderCenterService;

public class OrderCenterController {
	
	@Autowired
	private OrderCenterService orderCenterService;
	
	/**
	 * 根据订单中心id初始化数据
	 * @param orderCenterId
	 * @return
	 */
	public ResponseValue<String> initOrderCenterData(String orderCenterId) {
		
		orderCenterService.initOrderCenterData(orderCenterId);
		
		return ResponseValue.successResponse(orderCenterId);
	
	}
	
	public ResponseValue<List<PolicyInstanceVo>> orderCenterUnderwriting(String orderCenterId) {
		List<PolicyInstanceVo> policyInstances = orderCenterService.underwriting(orderCenterId);
		return ResponseValue.successResponse(policyInstances);
		
	}
	
	
	

}
