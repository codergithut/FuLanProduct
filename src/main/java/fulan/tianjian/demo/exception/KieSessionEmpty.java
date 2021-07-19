package fulan.tianjian.demo.exception;

import fulan.tianjian.demo.constant.ConstantCls;

public class KieSessionEmpty extends CustomException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public KieSessionEmpty(String message) {
		super(message, ConstantCls.SESSION_FAIL);
	}

}
