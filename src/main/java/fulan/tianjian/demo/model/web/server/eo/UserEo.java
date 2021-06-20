package fulan.tianjian.demo.model.web.server.eo;

/**
 * Created by tianjian on 2021/6/20.
 */
public class UserEo {
    /**
     * 用户姓名
     */
    private String name;

    /**
     * 手机号码
     */
    private String phoneNumber;

    /**
     * 身份证号码
     */
    private String identityCardNumber;

    /**
     * 用户凭证
     */
    private String userCredential;

    /**
     * 过期时间
     */
    private Long overdue;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getUserCredential() {
        return userCredential;
    }

    public void setUserCredential(String userCredential) {
        this.userCredential = userCredential;
    }
}
