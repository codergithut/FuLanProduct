package fulan.tianjian.demo.exception;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PureRiskLossException extends CustomException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PureRiskLossException(String message) {
        super(message, 100002);
    }
}
