package fulan.tianjian.demo.web.service.server;

import java.util.List;

import org.springframework.stereotype.Service;

import fulan.tianjian.demo.model.web.server.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.server.vo.PolicyInstanceVo;
import fulan.tianjian.demo.model.web.server.vo.PolicySchemeVo;
import fulan.tianjian.demo.model.web.server.vo.PolicyValueViewVo;

@Service
public class PolicyService {

	public List<PolicyDescribeVo> findPolicyDescribe() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicyValueViewVo> findPolicyValueView() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicySchemeVo> findPolicySchemeByRegionCode(String regionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean savePolicyInstance(List<PolicyInstanceVo> policyInstances) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicyInstanceVo> findPolicyInstanceByOrderNumber(String orderNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
