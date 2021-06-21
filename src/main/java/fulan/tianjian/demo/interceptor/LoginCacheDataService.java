package fulan.tianjian.demo.interceptor;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@Service
public class LoginCacheDataService {
	
	Cache<String, String> checkCodeCache = CacheBuilder.newBuilder()
            .maximumSize(100) // 设置缓存的最大容量
            .expireAfterWrite(5, TimeUnit.MINUTES) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();

    Cache<String, String> loginCache = CacheBuilder.newBuilder()
            .maximumSize(100) // 设置缓存的最大容量
            .expireAfterWrite(5, TimeUnit.DAYS) // 设置缓存在写入一分钟后失效
            .concurrencyLevel(10) // 设置并发级别为10
            .recordStats() // 开启缓存统计
            .build();

	public Cache<String, String> getCheckCodeCache() {
		return checkCodeCache;
	}

	public void setCheckCodeCache(Cache<String, String> checkCodeCache) {
		this.checkCodeCache = checkCodeCache;
	}

	public Cache<String, String> getLoginCache() {
		return loginCache;
	}

	public void setLoginCache(Cache<String, String> loginCache) {
		this.loginCache = loginCache;
	}
    
    

}
