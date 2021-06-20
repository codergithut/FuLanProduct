package fulan.tianjian.demo.web.controller.server;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import fulan.tianjian.demo.client.sms.SmsService;
import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.eo.UserEo;
import fulan.tianjian.demo.model.web.server.vo.UserVo;
import fulan.tianjian.demo.web.service.server.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * Created by tianjian on 2021/6/20.
 */
public class UserManagerController {

    @Autowired
    private SmsService smsService;

    @Autowired
    private UserService userService;

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


    /**
     * 获取验证码
     * @param userVo
     * @return
     */
    public ResponseValue<Boolean> getCheckCode(UserVo userVo) {
        String identityCardNumber = userVo.getIdentityCardNumber();
        String checkCode = RandomStringUtils.random(6, true, true);
        UserEo userEo =  userService.findUserInfo(userVo.getIdentityCardNumber());
        if(userEo != null) {
            boolean sendResult = smsService.sendCheckCode(userEo.getPhoneNumber(), checkCode);
            if(sendResult) {
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
    public ResponseValue<UserVo> userLogin(UserVo userVo) {

        //获取缓存身份证编码
        String checkCode = checkCodeCache.getIfPresent(userVo.getIdentityCardNumber());

        //判断对应的校验码是否一致
        if(StringUtils.isNoneBlank(checkCode) && checkCode.equals(userVo.getCheckCode())) {
            //为当前用户更新通行字符串
            String userCredential = userService.updateUserInfo(userVo);
            //将用户凭证写入缓存
            loginCache.put(userCredential, userVo.getIdentityCardNumber());
            userVo.setUserCredential(userCredential);
            return ResponseValue.successResponse(userVo);
        } else {
            return ResponseValue.failResponse();
        }
    }
}
