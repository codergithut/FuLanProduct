package fulan.tianjian.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import fulan.tianjian.demo.model.web.ResponseValue;

@ControllerAdvice
public class GlobalExceptionHandler {
	/**
	 * 处理自定义的业务异常
	 * @param req
	 * @param e
	 * @return
	 */
    @ExceptionHandler(value = CustomException.class)  
    @ResponseBody  
	public  ResponseValue<String> customExceptionHandler(HttpServletRequest req, CustomException e){
    	return ResponseValue.failResponse(e.getMessage());
    }

	/**
	 * 处理空指针的异常
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value =NullPointerException.class)
	@ResponseBody
	public ResponseValue<String> exceptionHandler(HttpServletRequest req, NullPointerException e){
		return ResponseValue.failResponse("空指针异常");
	}


    /**
        * 处理其他异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value =Exception.class)
	@ResponseBody
	public ResponseValue<String> exceptionHandler(HttpServletRequest req, Exception e){
       	return ResponseValue.failResponse(e.getMessage());
    }

}
