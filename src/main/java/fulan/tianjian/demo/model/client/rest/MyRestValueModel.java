package fulan.tianjian.demo.model.client.rest;

import com.alibaba.fastjson.JSON;
import org.springframework.context.ApplicationEvent;

public class MyRestValueModel<T> extends ApplicationEvent {

    private String url;

    private String params;


    private String message;

    private T t;

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

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(t);

    }
}
