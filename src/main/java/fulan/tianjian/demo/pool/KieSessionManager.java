package fulan.tianjian.demo.pool;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class KieSessionManager {
	private Map<String, KieSessionPool> registerKieSessionPools = new HashMap<String, KieSessionPool>();
	
	
	public KieSessionPool addKieSessionPool(String key) {
		
		String keyMd5 = DigestUtils.md5DigestAsHex(key.getBytes());
		
		KieSessionPoolFactory kieSessionPoolFactory = new KieSessionPoolFactory(key);
		
		KieSessionPool pool = new KieSessionPool(kieSessionPoolFactory);
		
		registerKieSessionPools.put(keyMd5, pool);
		
		return pool;
		
	}
	
	public KieSessionPool getKieSessionPool(String key) {
		
		String keyMd5 = DigestUtils.md5DigestAsHex(key.getBytes());
		
		if(registerKieSessionPools.containsKey(keyMd5)) {
			
			return registerKieSessionPools.get(keyMd5);
			
		} else {
		
			return addKieSessionPool(key);
		}
		
	}
	
	public boolean deleteSessionPool(String key) {
		registerKieSessionPools.remove(DigestUtils.md5DigestAsHex(key.getBytes()));
		return true;
	}

}
