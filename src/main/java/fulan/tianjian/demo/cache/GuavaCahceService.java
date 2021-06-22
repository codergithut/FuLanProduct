package fulan.tianjian.demo.cache;

import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 通过guava实现本地缓存测试
 * @author 14681
 *
 * @param <KEY>
 * @param <VALUE>
 */
public abstract class GuavaCahceService<KEY, VALUE> implements CacheDataService<KEY, VALUE>, CacheService<KEY, VALUE>{
	
	private Cache<KEY, VALUE> cache = CacheBuilder.newBuilder()
            .maximumSize(100) // 设置缓存的最大容量
            .expireAfterWrite(5, TimeUnit.MINUTES) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();
	
	@Override
    public VALUE getValueByKey(KEY key) {
    	VALUE value = null;
    	
    	if(cache.getIfPresent(key) != null) {
    		value = cache.getIfPresent(key);
    		return value;
    	}
    	
    	value = findCacheData(key);
    	
    	if(value != null) {
    		cache.put(key, value);
    	}
   
    	return value;
    
    }
	
	@Override
	public void saveKeyAndValue(KEY key, VALUE value) {
		
		deleteKey(key);
		cache.invalidate(key);
		
		saveCacheData(key, value);
		cache.put(key, value);

	}
	
	@Override
	public void deleteKey(KEY key) {
		deleteCacheData(key);
		cache.invalidate(key);
	}
	

}
