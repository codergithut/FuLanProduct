package fulan.tianjian.demo.web.controller.server;

import com.google.common.cache.Cache;
import fulan.tianjian.demo.client.sms.SmsService;
import fulan.tianjian.demo.interceptor.LoginCacheDataService;
import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.eo.UserEo;
import fulan.tianjian.demo.model.web.server.vo.UserVo;
import fulan.tianjian.demo.web.service.server.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by tianjian on 2021/6/20.
 */
public class UserManagerController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private LoginCacheDataService loginCacheDataService;

 


    /**
     * 获取验证码
     * @param userVo
     * @return
     */
    public ResponseValue<Boolean> getCheckCode(UserVo userVo) {
    	
        String identityCardNumber = userVo.getIdentityCardNumber();
        String checkCode = RandomStringUtils.random(6, true, true);
        //根据用户id查询系统是否已有用户
        UserEo userEo =  userService.findUserInfo(userVo.getIdentityCardNumber());
        if(userEo != null) {
        	//发送验证码给用户
            boolean sendResult = smsService.sendCheckCode(userEo.getPhoneNumber(), checkCode);
            if(sendResult) {
            	//缓存记录
            	Cache<String, String> checkCodeCache = loginCacheDataService.getCheckCodeCache();
                checkCodeCache.put(identityCardNumber, checkCode);
            }
        }

        return ResponseValue.successResponse(true);
    }


    /**
     * 用户登录
     * @param userVo
     * @return
     */
    public ResponseValue<UserVo> userLogin(UserVo userVo, HttpServletResponse response) {
    	
    	Cache<String, String> checkCodeCache = loginCacheDataService.getCheckCodeCache();
    	Cache<String, String> loginCache = loginCacheDataService.getLoginCache();

        //获取缓存身份证编码
        String checkCode = checkCodeCache.getIfPresent(userVo.getIdentityCardNumber());

        //判断对应的校验码是否一致
        if(StringUtils.isNoneBlank(checkCode) && checkCode.equals(userVo.getCheckCode())) {
            //为当前用户更新通行字符串
            String userCredential = userService.updateUserInfo(userVo);
            //将用户凭证写入缓存
            loginCache.put(userCredential, userVo.getIdentityCardNumber());
            userVo.setUserCredential(userCredential);
            
            //为前端设置用户凭证并设置只读属性
            Cookie cookie = new Cookie("userCredential",userCredential);  //对比入参数据
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            
            return ResponseValue.successResponse(userVo);
        } else {
            return ResponseValue.failResponse();
        }
    }
}
