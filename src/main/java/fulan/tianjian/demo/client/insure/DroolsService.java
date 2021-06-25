package fulan.tianjian.demo.client.insure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.exception.DrlResourceEmptyException;
import fulan.tianjian.demo.model.client.insure.database.DrlResourceCurd;
import fulan.tianjian.demo.model.client.insure.database.DrlResourceEo;
import fulan.tianjian.demo.model.client.insure.drools.DrlResource;
import fulan.tianjian.demo.model.client.insure.drools.GivingPolicy;
import fulan.tianjian.demo.model.client.insure.drools.PolicyRuleEngine;
import fulan.tianjian.demo.model.client.insure.remote.PolicySchemeRemote;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

@Service
public class DroolsService {
	
	private KieSession kieSession;
	
	@Autowired
	private DrlResourceCurd drlResourceCurd;
	
	String s = "package fulan.tianjian.demo.drools\r\n"
			+ "rule \"PolicyRuleEngine Rule\"\r\n"
			+ "    when\r\n"
			+ "        $p : PolicyRuleEngine(carAge == 3)\r\n"
			+ "    then\r\n"
			+ "        $p.saveGivingPolicy(\"1\", \"2\", \"3\", \"4\");\r\n"
			+ "        System.out.println(\"PolicyRuleEngine Rule\");\r\n"
			+ "end\r\n"
			+ "\r\n"
			+ "\r\n"
			+ "rule \"PolicyRuleEngine isNewEnergy\"\r\n"
			+ "	when\r\n"
			+ "	    $p : PolicyRuleEngine(seat == 3)\r\n"
			+ "    then\r\n"
			+ "        $p.saveGivingPolicy(\"1\", \"2\", \"3\", \"4\");\r\n"
			+ "        System.out.println(\"PolicyRuleEngine Rule\");\r\n"
			+ "end";
	
	
	
	/**
	 * 初始化模板引擎会话信息
	 * @param drlResources 模板信息资源
	 * @throws DrlResourceEmptyException 模板数据获取失败
	 */
	public void initKieSession() throws DrlResourceEmptyException {
		
		closeKieSession();
		
		DrlResourceEo drlResourceEo = drlResourceCurd.findResourceByDrlName("POLICYDRL");
		
		if(drlResourceEo == null) {
			return ;
		}
		
		List<DrlResource> drlResources = new ArrayList<DrlResource>();
		
		DrlResource drlResource = new DrlResource();
		
		BeanUtils.copyProperties(drlResourceEo, drlResource);
		drlResources.add(drlResource);
		
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
	public List<GivingPolicy> getGivingPolicyByVehicle(VehicleRemote vehicleRemote, List<PolicySchemeRemote> policySchemeRemotes) {
		
		if(CollectionUtils.isEmpty(policySchemeRemotes) || kieSession == null) {
			return null;
		}
		
		List<String> policyStrings = policySchemeRemotes.stream().map(e -> e.getPolicyCode()).toList();
		PolicyRuleEngine policyRuleEngine = new PolicyRuleEngine();
	    policyRuleEngine.initPolicyRuleData(vehicleRemote,policyStrings);
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
	
	public static DrlResource mockDrlString(String fileName) throws IOException {
		DrlResource drlResource = new DrlResource();
		
		@SuppressWarnings("deprecation")
		List<String> fileValues = FileUtils.readLines(new File(fileName));
		StringBuffer drlValue = new StringBuffer();
		for(String s : fileValues) {
			drlValue.append(s + " ");
		}
		drlResource.setDrlFile(drlValue.toString());
		drlResource.setDrlName("POLICYDRL");
		return drlResource;
		
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println(JSON.toJSONString(mockDrlString("C:\\drl\\PolicyRuleEngine.drl")));
	}

}