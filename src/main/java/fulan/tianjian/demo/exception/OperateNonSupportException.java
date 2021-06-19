package fulan.tianjian.demo.exception;

import static fulan.tianjian.demo.constant.ConstantCls.HTTP_OPERATE_NOT_SUPPORT;

public class OperateNonSupportException extends CustomException{

    public OperateNonSupportException(String message) {
        super(message, HTTP_OPERATE_NOT_SUPPORT);
    }
}
