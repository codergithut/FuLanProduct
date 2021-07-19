package fulan.tianjian.demo.pool;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.kie.api.runtime.KieSession;

public class KieSessionPool extends GenericObjectPool<KieSession>{
	
	public KieSessionPool(KieSessionPoolFactory factory, GenericObjectPool.Config poolConfig) {
		super(factory, poolConfig);
	}
	
	public KieSessionPool(KieSessionPoolFactory factory) {
		super(factory, new GenericObjectPool.Config());
	}

}
