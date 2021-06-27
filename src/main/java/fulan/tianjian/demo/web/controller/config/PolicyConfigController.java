package fulan.tianjian.demo.web.controller.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.PolicySchemeVo;
import fulan.tianjian.demo.web.service.config.PolicySchemeConfigService;

@RestController
@RequestMapping("/config/policy")
public class PolicyConfigController {
	
	@Autowired
	private PolicySchemeConfigService policySchemeConfigService;
	
	@PostMapping("add")
	public ResponseValue<Boolean> savePolicyConfig(@RequestBody List<PolicySchemeVo> policySchemeVos) {
		
		Boolean result = policySchemeConfigService.savePolicyConfig(policySchemeVos);
		
		return ResponseValue.successResponse(true);
	}

}
