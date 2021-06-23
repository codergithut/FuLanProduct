package fulan.tianjian.demo.model.client.rest;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.client.RemoteRecordEo;

import org.springframework.context.ApplicationEvent;
import org.springframework.util.DigestUtils;

public class MyRestValueModel<T> extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

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
    private T data;

    /**
     * 响应代码
     */
    private String status;

    public MyRestValueModel(Object source, String url, String params) {
        super(source);
        this.url = url;
        this.params = params;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(data);

    }
    
    public String getMd5Value() {
    	return DigestUtils.md5DigestAsHex(params.getBytes());
    }
    
    public RemoteRecordEo convertToRemoteRecordEo() {
    	RemoteRecordEo remoteRecord = new RemoteRecordEo();
    	remoteRecord.setMd5Value(this.getMd5Value());
    	remoteRecord.setParams(params);
    	remoteRecord.setUrl(url);
    	remoteRecord.setIsDelete("N");
    	return remoteRecord;
    	
    	
    }
}
