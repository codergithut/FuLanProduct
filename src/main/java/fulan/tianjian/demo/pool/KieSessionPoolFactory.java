package fulan.tianjian.demo.pool;

import java.io.StringReader;

import org.apache.commons.pool.PoolableObjectFactory;
import org.kie.api.KieBase;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;

public class KieSessionPoolFactory implements PoolableObjectFactory<KieSession>{
	
	private String drlContent = null;
	
	public KieSessionPoolFactory(String drlContent) {
		this.drlContent = drlContent;
	}


	@Override
	public KieSession makeObject() throws Exception {
		KnowledgeBuilder kb = KnowledgeBuilderFactory.newKnowledgeBuilder();
		Resource resource = ResourceFactory.newReaderResource(new StringReader(drlContent.replaceAll("\r", "")));
		kb.add(resource, ResourceType.DRL);
		KieBase kieBase = kb.newKieBase();
		return kieBase.newKieSession();
	}

	@Override
	public void destroyObject(KieSession kieSession) throws Exception {
		if(kieSession != null) {
			kieSession.destroy();
		}
		
	}

	@Override
	public boolean validateObject(KieSession kieSession) {
		//验证当前字符串是否正确
		return true;
	}

	@Override
	public void activateObject(KieSession kieSession) throws Exception {
		
	}

	@Override
	public void passivateObject(KieSession kieSession) throws Exception {
		
		 
	}

}
