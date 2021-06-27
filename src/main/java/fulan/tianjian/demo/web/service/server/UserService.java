package fulan.tianjian.demo.web.service.server;

import fulan.tianjian.demo.model.web.eo.UserEo;
import fulan.tianjian.demo.model.web.vo.UserVo;

import java.util.UUID;

/**
 * Created by tianjian on 2021/6/21.
 */
public class UserService {
    public UserEo findUserInfo(String identityCardNumber) {
        return null;
    }

    public String updateUserInfo(UserVo userVo) {
        String userCredential = UUID.randomUUID().toString();
        //更新用户验证凭证和过期时间
        return userCredential;
    }
}
