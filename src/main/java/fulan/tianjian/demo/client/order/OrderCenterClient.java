package fulan.tianjian.demo.client.order;

import org.springframework.stereotype.Service;

import fulan.tianjian.demo.client.AbstractHttpClient;
import fulan.tianjian.demo.model.client.order.OrderCenterVo;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;

@Service
public class OrderCenterClient extends AbstractHttpClient<OrderCenterVo>{
	
	
	public OrderCenterVo getOrderCenterByOrderCenterId(String orderCenterId) {
		
		MyRestValueModel<OrderCenterVo> result = postRestResult("url", orderCenterId, OrderCenterVo.class);
		
		if("0000".equals(result.getStatus())) {
			return result.getData();
		}
		
		return null;
		
	}

	@Override
	public boolean analyseResult(OrderCenterVo t) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
