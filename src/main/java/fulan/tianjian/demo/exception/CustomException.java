package fulan.tianjian.demo.exception;

public class CustomException extends Exception{
    private Integer code;

    public CustomException(String message, Integer code) {
        super(message);

    }
}
