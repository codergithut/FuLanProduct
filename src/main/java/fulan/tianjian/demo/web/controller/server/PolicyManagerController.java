package fulan.tianjian.demo.web.controller.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.server.vo.PolicySchemeVo;
import fulan.tianjian.demo.model.web.server.vo.PolicyValueViewVo;
import fulan.tianjian.demo.model.web.server.vo.UserVo;
import fulan.tianjian.demo.web.service.server.PersonService;
import fulan.tianjian.demo.web.service.server.PolicyService;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PolicyManagerController {
	
	@Autowired
	private PolicyService policyService;
	
	/**
	 * 获取保险描述详情
	 * @return
	 */
	public ResponseValue<List<PolicyDescribeVo>> getPolicyDescribe() {
		
		List<PolicyDescribeVo> policyDescribes = policyService.findPolicyDescribe();

		return ResponseValue.successResponse(policyDescribes);
	}
	
	/**
	 * 获取保险视图主要是提供保险保额选线等数据
	 * @return
	 */
	public ResponseValue<List<PolicyValueViewVo>> getPolicyView() {
		
		List<PolicyValueViewVo> policyValueViews = policyService.findPolicyValueView();
		
		return ResponseValue.successResponse(policyValueViews);
	}
	
	
	/**
	 * 获取保险具体方案
	 * @param regionCode 地区编码
	 * @return
	 */
	public ResponseValue<List<PolicySchemeVo>> getPolicyScheme(String regionCode) {
		
		List<PolicySchemeVo> policySchemes = policyService.findPolicySchemeByRegionCode(regionCode);
		
		return ResponseValue.successResponse(policySchemes);
		
	}
	
	
	
	
}
