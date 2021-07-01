package fulan.tianjian.demo.cache;

/**
 * 缓存数据库操作
 * @author 14681
 *
 * @param <KEY>
 * @param <VALUE>
 */
public abstract class CacheDataService<KEY, VALUE> {
	
	/**
	 * 保存缓存数据到数据库
	 * @param key
	 * @param value 
	 */
	protected abstract void saveCacheData(KEY key, VALUE value);
	
	/**
	 * 删除数据库中的缓存数据
	 * @param key
	 */
	protected abstract void deleteCacheData(KEY key);
	
	/**
	 * 根据key 获取数据库的缓存数据
	 * @param key
	 * @return
	 */
	protected abstract VALUE findCacheData(KEY key);

}
