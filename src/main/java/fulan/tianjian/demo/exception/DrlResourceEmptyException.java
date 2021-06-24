package fulan.tianjian.demo.exception;

import static fulan.tianjian.demo.constant.ConstantCls.HTTP_DRL_EMPTY;

public class DrlResourceEmptyException extends CustomException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DrlResourceEmptyException(String message) {
		super(message, HTTP_DRL_EMPTY);
		// TODO Auto-generated constructor stub
	}

}
