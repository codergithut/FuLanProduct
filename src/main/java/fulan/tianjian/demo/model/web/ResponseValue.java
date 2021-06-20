package fulan.tianjian.demo.model.web;

import static fulan.tianjian.demo.constant.ConstantCls.REST_COMMON_FAIL_CODE;
import static fulan.tianjian.demo.constant.ConstantCls.REST_COMMON_SUCCESS_CODE;

/**
 * Created by tianjian on 2021/6/20.
 */
public class ResponseValue<T> {

    private String message;

    private Integer code;

    private T data;

    public ResponseValue(String message, Integer code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public ResponseValue(String message, Integer code) {
        this.message = message;
        this.code = code;
    }

    public static final ResponseValue failResponse() {
        return new ResponseValue("返回结果错误", REST_COMMON_FAIL_CODE);
    }

    public static<T> ResponseValue<T> successResponse(T data) {
        ResponseValue responseValue = new ResponseValue("成功", REST_COMMON_SUCCESS_CODE);
        return responseValue;
    }
}
