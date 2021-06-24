package fulan.tianjian.demo.client.insure;

import java.io.StringReader;
import java.util.List;

import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.exception.DrlResourceEmptyException;
import fulan.tianjian.demo.model.client.insure.drools.DrlResource;
import fulan.tianjian.demo.model.client.insure.drools.GivingPolicy;
import fulan.tianjian.demo.model.client.insure.drools.PolicyRuleEngine;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

@Service
public class DroolsService {
	
	private KieSession kieSession;
	
	/**
	 * 初始化模板引擎会话信息
	 * @param drlResources 模板信息资源
	 * @throws DrlResourceEmptyException 模板数据获取失败
	 */
	public void initKieSession(List<DrlResource> drlResources) throws DrlResourceEmptyException {
		
		if(kieSession != null) {
			return ;
		}
		
		if(CollectionUtils.isEmpty(drlResources)) {
			throw new DrlResourceEmptyException("模板文件为空");
		}
		
		//初始化知识库获取会话信息
		KnowledgeBuilder  kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		drlResources.stream().forEach(e -> {
			Resource resource = ResourceFactory.newReaderResource(new StringReader(e.getDrlFile().replaceAll("\r", "")));
			kb.add(resource, ResourceType.DRL);
		});
		KieBase kieBase = kb.newKieBase();
		kieSession = kieBase.newKieSession();
		
	}
	
	/**
	 * 根据车辆数据和规则引擎
	 * @param vehicleRemote 车辆数据
	 * @return 可以送的赠送险
	 */
	public List<GivingPolicy> getGivingPolicyByVehicle(VehicleRemote vehicleRemote) {
		
		PolicyRuleEngine policyRuleEngine = new PolicyRuleEngine();
	    policyRuleEngine.setVehicleRemote(vehicleRemote);
	    policyRuleEngine.initPolicyRuleData();
		
		kieSession.insert(policyRuleEngine);
		kieSession.fireAllRules(1);
		
		return policyRuleEngine.getGivingPolicys();
		
	}
	
	public void closeKieSession() {
		if(kieSession == null) {
			return ;
		}
		kieSession.dispose();
	}

}