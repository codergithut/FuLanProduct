package fulan.tianjian.demo.drools;

import java.io.StringReader;

import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;

public class DroolsTest {
	public static void main(String[] args) {
		String rule = "package fulan.tianjian.demo.drools\r\n"
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
		KnowledgeBuilder  kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		Resource resource = ResourceFactory.newReaderResource(new StringReader(rule.replaceAll("\r", "")));
		
		kb.add(resource, ResourceType.DRL);
		
		KieBase kieBase = kb.newKieBase();
		
		KieSession kieSession = kieBase.newKieSession();
		
		PolicyRuleEngine policyEngine = mockPolicyRuleEngine();
		Person mark = new Person();
		mark.setName("Mark");
		mark.setAge(31);
		
		Person tj = new Person();
		tj.setName("tj");
		tj.setAge(80);
		
		kieSession.insert(tj);
		kieSession.insert(mark);
		
		kieSession.insert(policyEngine);
		
		kieSession.fireAllRules(1);
		
		kieSession.dispose();
		
		System.out.println(policyEngine.getSeat() + ":" + policyEngine.isNewEnergy() + policyEngine.getCarAge());
		
		
		

	}
	
	public static PolicyRuleEngine mockPolicyRuleEngine() {
		PolicyRuleEngine policyRuleEngine = new PolicyRuleEngine();
		VehicleDTO vehicle = new VehicleDTO();
		vehicle.setRegisterDate("20100304");
		vehicle.setDisplacement("1890");
		policyRuleEngine.setVehicleDTO(vehicle);
		return policyRuleEngine;
	}

}
