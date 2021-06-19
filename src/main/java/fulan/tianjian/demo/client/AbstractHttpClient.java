package fulan.tianjian.demo.client;

import fulan.tianjian.demo.exception.OperateNonSupportException;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.notice.SendNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


import static org.springframework.http.HttpStatus.OK;

/**
 * 抽象的HttpClient封装方法
 * @param <T>
 */
public abstract class AbstractHttpClient<T> implements AnalyseRestResult<T>{

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SendNoticeService sendNoticeService;

    /**
     * 通用远程调用请求
     * @param url 请求的url
     * @param params 请求的参数String
     * @param t 需要转化的类
     * @param type get or post
     * @return 返回的对象
     */
    private MyRestValueModel<T> customRestResult(String url, String params, Class<T> t, String type) {

        //请求结果返回封装
        MyRestValueModel<T> myRestValueModel = new MyRestValueModel<>(this, url, params);

        //restTemplate对象结果
        ResponseEntity<T> resut = null;


        //根据get post 分别调用不同的方法
        if("get".equals(type)){
            resut = restTemplate.getForEntity(url, t, params);
        }

        if("post".equals(type)) {
            resut = restTemplate.postForEntity(url, params, t);
        }


        //尝试获取数据值
        T data = null;

        //判断返回码是否正确错误返回异常无需解析
        if(resut.getStatusCode() != OK) {
            return null;
        }

        if(resut.getBody() != null) {
            data = resut.getBody();
            myRestValueModel.setT(data);
        }

        //根据返回对象判断该消息是否正确返回了
        if(data != null) {
            if(analyseResult(data)) {
                myRestValueModel.setStatus("0000");
            } else {
                myRestValueModel.setStatus("0001");
            }
        }

        //整个请求响应消息异步发送消息
        sendNoticeService.publish(myRestValueModel);

        return myRestValueModel;
    }

    /**
     * post方法调用
     * @param url 请求url
     * @param params 请求参数
     * @param t class
     * @return 请求返回值
     */
    public MyRestValueModel<T> postRestResult(String url, String params, Class<T> t){
        return customRestResult(url, params, t, "post");

    }

    /**
     * get方法调用
     * @param url 请求url
     * @param params 请求参数
     * @param t class
     * @return 请求返回值
     * @throws OperateNonSupportException
     */
    public MyRestValueModel<T> getRestResult(String url, String params, Class<T> t) {
        return customRestResult(url, params, t, "get");
    }
}
