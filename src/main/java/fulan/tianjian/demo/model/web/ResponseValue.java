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

    public static<T> ResponseValue<T> failResponse() {
        return new ResponseValue<T>("返回结果错误", REST_COMMON_FAIL_CODE);
    }

    public static<T> ResponseValue<T> successResponse(T data) {
        ResponseValue<T> responseValue = new ResponseValue<T>("成功", REST_COMMON_SUCCESS_CODE, data);
        return responseValue;
    }

	public static ResponseValue<String> failResponse(String message) {
		return new ResponseValue<String>(message, REST_COMMON_FAIL_CODE);
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
