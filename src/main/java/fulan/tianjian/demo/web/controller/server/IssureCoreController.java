package fulan.tianjian.demo.web.controller.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.vo.PayInfoVo;
import fulan.tianjian.demo.model.web.server.vo.PolicyInstanceVo;
import fulan.tianjian.demo.web.service.server.InssureCoreService;

/**
 * Created by tianjian on 2021/6/20.
 */
public class IssureCoreController {
	
	@Autowired
	private InssureCoreService inssureCoreService;
	
	
	/**
	 * 核保
	 * @param branchCode
	 * @param regionCode
	 * @return
	 * @throws PureRiskLossException 
	 */
	public ResponseValue<List<PolicyInstanceVo>> underwriting(String branchCode, String regionCode) throws PureRiskLossException {
		
		List<PolicyInstanceVo> policyInstanceVos = inssureCoreService.underwriting(branchCode, regionCode);
		
		return ResponseValue.successResponse(policyInstanceVos);
	}
	
	
	/**
	 * 报价
	 * @param branchCode
	 * @param regionCode
	 * @return
	 * @throws PureRiskLossException 
	 */
	public ResponseValue<List<PolicyInstanceVo>> quote(String branchCode, String regionCode) throws PureRiskLossException {
		
		List<PolicyInstanceVo> policyInstanceVos = inssureCoreService.quote(branchCode, regionCode);
		
		return ResponseValue.successResponse(policyInstanceVos);
		
	}
	
	/**
	 * 获取支付链接
	 * @param orderNumber
	 * @return
	 */
	public ResponseValue<PayInfoVo> getPayInfo(String orderNumber) {
		
		PayInfoVo payInfo = inssureCoreService.getPayInfo(orderNumber);
		
		return ResponseValue.successResponse(payInfo);
		
	}
	
}
