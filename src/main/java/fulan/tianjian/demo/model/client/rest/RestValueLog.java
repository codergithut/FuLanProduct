package fulan.tianjian.demo.model.client.rest;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "callLog")
public class RestValueLog {
	
	@Id
	private String id;
	
	/**
	 * 请求url
	 */
	private String url;

	/**
	 * 请求参数
	 */
    private String params;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 返回数据
     */
    private String responseData;
    
    /**
     * 响应代码
     */
    private String status;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public String getResponseData() {
		return responseData;
	}

	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    


}
