package fulan.tianjian.demo.web.controller.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;
import fulan.tianjian.demo.model.web.vo.PolicyValueViewVo;
import fulan.tianjian.demo.web.service.config.ServiceBaseConfigService;

@RestController
@RequestMapping("/config/policy")
public class PolicyConfigController {

	@Autowired
	private ServiceBaseConfigService policySchemeConfigService;

	@PostMapping("add")
	public ResponseValue<Boolean> savePolicyConfig(@RequestBody List<PolicySchemeConfigVo> policySchemeVos) {

		policySchemeConfigService.savePolicyConfig(policySchemeVos);

		return ResponseValue.successResponse(true);
	}

	@GetMapping("find")
	public ResponseValue<List<PolicySchemeConfigVo>> getPolicyConfig(@RequestParam String regionCode) {
		return ResponseValue.successResponse(policySchemeConfigService.getPolicyConfig(regionCode));
	}
	
	/**
	 * 前端缓存
	 * 获取保险描述详情
	 * @return
	 */
	public ResponseValue<List<PolicyDescribeVo>> getPolicyDescribe() {
		
		List<PolicyDescribeVo> policyDescribes = policySchemeConfigService.findPolicyDescribe();

		return ResponseValue.successResponse(policyDescribes);
	}
	
	/**
	 * 前端缓存
	 * 获取保险视图主要是提供保险保额选线等数据
	 * @return
	 */
	public ResponseValue<List<PolicyValueViewVo>> getPolicyView() {
		
		List<PolicyValueViewVo> policyValueViews = policySchemeConfigService.findPolicyValueView();
		
		return ResponseValue.successResponse(policyValueViews);
	}

}
