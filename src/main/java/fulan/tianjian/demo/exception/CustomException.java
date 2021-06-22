package fulan.tianjian.demo.exception;

public class CustomException extends Exception{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Integer code;

    public CustomException(String message, Integer code) {
        super(message);

    }

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
    
    
}
