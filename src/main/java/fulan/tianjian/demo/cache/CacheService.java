package fulan.tianjian.demo.cache;

/**
 * 缓存操作
 * @author 14681
 *
 * @param <KEY>
 * @param <VALUE>
 */
public interface CacheService<KEY,VALUE> {
	
	/**
	 * 根据key获取缓存数据
	 * @param key
	 * @return
	 */
	VALUE getValueByKey(KEY key);
	
	/**
	 * 保存缓存数据
	 * @param key
	 * @param value
	 */
	void saveKeyAndValue(KEY key, VALUE value);
	
	/**
	 * 删除缓存数据
	 * @param key
	 */
	void deleteKey(KEY key);
	

}
