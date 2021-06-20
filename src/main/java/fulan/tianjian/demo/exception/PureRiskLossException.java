package fulan.tianjian.demo.exception;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PureRiskLossException extends CustomException{
    public PureRiskLossException(String message) {
        super(message, 100002);
    }
}
