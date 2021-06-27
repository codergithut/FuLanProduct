package fulan.tianjian.demo.web.service.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.web.eo.PolicyInstanceCurd;
import fulan.tianjian.demo.model.web.eo.PolicyInstanceEo;
import fulan.tianjian.demo.model.web.vo.PolicyDescribeVo;
import fulan.tianjian.demo.model.web.vo.PolicyInstanceVo;
import fulan.tianjian.demo.model.web.vo.PolicySchemeConfigVo;
import fulan.tianjian.demo.model.web.vo.PolicyValueViewVo;

@Service
public class PolicyService {
	
	@Autowired
	private PolicyInstanceCurd policyInstanceCurd;

	public List<PolicyDescribeVo> findPolicyDescribe() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicyValueViewVo> findPolicyValueView() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<PolicySchemeConfigVo> findPolicySchemeByRegionCode(String regionCode) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean savePolicyInstance(List<PolicyInstanceVo> policyInstances) {
		if(CollectionUtils.isEmpty(policyInstances)) {
			return false;
		}
		
		String orderNumber = policyInstances.get(0).getOrderNumber();
		policyInstanceCurd.deleteByOrderNumber(orderNumber);
		
		List<PolicyInstanceEo> policyInstanceEos = policyInstances.stream().map(e -> {
			return e.convertToEo();
		}).collect(Collectors.toList());
		policyInstanceCurd.saveAll(policyInstanceEos);
		
		return true;
	}

	public List<PolicyInstanceVo> findPolicyInstanceByOrderNumber(String orderNumber) {
		List<PolicyInstanceEo> policyInstanceEos = policyInstanceCurd.findByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(policyInstanceEos)) {
			return new ArrayList<PolicyInstanceVo>();
		}
	    return policyInstanceEos.stream().map(e -> {
	    	return e.convertToVo();
	    }).collect(Collectors.toList());
	}
	

}
