package fulan.tianjian.demo.quartz;

public class QuartzServerResponse {
	
	private String resultCode;
	
	private boolean isSuccess;
	
	private String message;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static QuartzServerResponse sucessResponse() {
		QuartzServerResponse quartzServerResponse = new QuartzServerResponse();
		quartzServerResponse.setMessage("OK");
		quartzServerResponse.setResultCode("OK");
		quartzServerResponse.setSuccess(true);
		return quartzServerResponse;
	}
	
	

}
