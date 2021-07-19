package fulan.tianjian.demo.convert;

import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

import fulan.tianjian.demo.exception.KieSessionEmpty;
import fulan.tianjian.demo.pool.KieSessionPool;

public class KieSessionProxy {
	
	private KieSession kieSession;
	
	private KieSessionPool kieSessionPool;
	
	public KieSessionProxy(KieSession kieSession) {
		this.kieSession = kieSession;
		
	}
	
	public KieSessionProxy(KieSessionPool kieSessionPool) throws Exception {
		this.kieSessionPool = kieSessionPool;
		this.kieSession = kieSessionPool.borrowObject();
		
	}
	
	
	public KieSession getKieSession() throws Exception {
		if(kieSession != null) {
			return kieSession;
		}
		throw new KieSessionEmpty("get kieSession fail");
	}
	
	
	
	public FactHandle insert(Object object) throws KieSessionEmpty {
		if(kieSession == null) {
			throw new KieSessionEmpty("get kieSession fail");
		}
		return kieSession.insert(object);
	}
	
	public int fireAllRules(int max) throws KieSessionEmpty {
		if(kieSession == null) {
			throw new KieSessionEmpty("get kieSession fail");
		}
		return kieSession.fireAllRules(max);
	}
	
	public void dispose() throws Exception {
		if(kieSessionPool != null) {
			kieSessionPool.returnObject(kieSession);
			return ;
		}
		if(kieSession != null) {
			kieSession.dispose();
			return ;
		}
	}
	
	

}
