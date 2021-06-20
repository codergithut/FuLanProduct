package fulan.tianjian.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianjian on 2021/6/21.
 */
public class LoginInterceptor implements HandlerInterceptor {
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
        Cookie[] cookies = request.getCookies();
        cookies[0].getName();
        System.out.println("我是在DispatcherServlet之前执行的方法");
        return true;
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
