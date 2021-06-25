package fulan.tianjian.demo.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.google.common.cache.Cache;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianjian on 2021/6/21.
 */
public class LoginInterceptor implements HandlerInterceptor {
	
	@Autowired
    private LoginCacheDataService loginCacheDataService;
	
    /**
     *在DispatcherServlet之前执行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	
    	Cache<String, String> loginCache = loginCacheDataService.getLoginCache();
    	
    	//cookie获取用户凭证
        Cookie[] cookies = request.getCookies();
        
        //测试环境放开登录
        if(cookies == null) {
        	return true;
        }
        
        String userCredential = getUserCredential(cookies);
        
        //如果用户凭证为null 或者缓存凭证为null都视为缓存失效需要重新登录
        if(StringUtils.isBlank(userCredential) || StringUtils.isBlank(loginCache.getIfPresent(userCredential))) {
        	return false;
        }
        
        return true;
    }
    
    private String getUserCredential(Cookie[] cookies) {
    	for(Cookie cookie : cookies) {
        	if("userCredential".equals(cookie.getName())) {
        		return cookie.getValue();
        	}
        }
    	return null;
    }


    /**
     * 在页面渲染完成返回给客户端之前执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("在页面渲染完成返回给客户端之前执行");
    }
}
