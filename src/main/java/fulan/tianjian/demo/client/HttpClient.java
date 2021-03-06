package fulan.tianjian.demo.client;

import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.exception.OperateNonSupportException;
import fulan.tianjian.demo.model.client.insure.remote.InsuranceRiskInformationRemote;
import fulan.tianjian.demo.model.client.insure.remote.InsureRemote;
import fulan.tianjian.demo.model.client.insure.remote.PremiumFloatingItemsRemote;
import fulan.tianjian.demo.model.client.order.OrderCenterVo;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.sms.SmsModel;
import fulan.tianjian.demo.model.client.synchro.SynchroModel;
import fulan.tianjian.demo.rabbitmq.MessageProducer;
import fulan.tianjian.demo.rabbitmq.QueueConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import static org.springframework.http.HttpStatus.OK;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * 抽象的HttpClient封装方法
 * 
 * @param <T>
 */
@Service
public class HttpClient implements AnalyseRestResult {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MessageProducer messageProducer;

	@Value("${remote.mockTyp}")
	private Boolean isMock;

	/**
	 * 通用远程调用请求
	 * 
	 * @param url    请求的url
	 * @param params 请求的参数String
	 * @param t      需要转化的类
	 * @param type   get or post
	 * @return 返回的对象
	 */
	private <T> MyRestValueModel<T> customRestResult(String url, String params, Class<T> t, String type) {

		// 请求结果返回封装
		MyRestValueModel<T> myRestValueModel = new MyRestValueModel<>();
		
		myRestValueModel.setUrl(url);
		myRestValueModel.setParams(params);

		// restTemplate对象结果
		ResponseEntity<T> result = null;

		if (!isMock) {
			// 根据get post 分别调用不同的方法
			if ("get".equals(type) && !isMock) {
				result = restTemplate.getForEntity(url, t, params);
			}

			if ("post".equals(type) && !isMock) {
				result = restTemplate.postForEntity(url, params, t);
			}
		}

		// 尝试获取数据值
		T data = null;

		// 判断返回码是否正确错误返回异常无需解析
		if (!isMock && result.getStatusCode() != OK) {
			return null;
		}

		if (!isMock && result.getBody() != null) {
			data = result.getBody();
			myRestValueModel.setData(data);
		}

		if (isMock) {
			data = mockData(url, t, params);
			myRestValueModel.setData(data);

		}

		// 根据返回对象判断该消息是否正确返回了
		if (data != null) {
			if (analyseResult(data)) {
				myRestValueModel.setStatus("0000");
			} else {
				myRestValueModel.setStatus("0001");
			}
		}

		// 整个请求响应消息异步发送消息
//        sendNoticeService.publish(myRestValueModel);

		messageProducer.sendMessage(QueueConstants.MESSAGE_EXCHANGE_REMOTE, QueueConstants.MESSAGE_ROUTE_KEY_REMOTE,
				myRestValueModel);
		return myRestValueModel;
	}

	/**
	 * post方法调用
	 * 
	 * @param url    请求url
	 * @param params 请求参数
	 * @param t      class
	 * @return 请求返回值
	 */
	public <T> MyRestValueModel<T> postRestResult(String url, String params, Class<T> t) {
		return customRestResult(url, params, t, "post");

	}

	private static List<String> mockMessage;

	static {
		mockMessage = new ArrayList<>();
		mockMessage.add("报价规则拦截");
		mockMessage.add("车辆数据有误");
		mockMessage.add("人员信息校验失败");
		mockMessage.add("保险方案有误");
		mockMessage.add("不支持临牌网销投保");
	}

	/**
	 * get方法调用
	 * 
	 * @param url    请求url
	 * @param params 请求参数
	 * @param t      class
	 * @return 请求返回值
	 * @throws OperateNonSupportException
	 */
	protected <T> MyRestValueModel<T> getRestResult(String url, String params, Class<T> t) {
		return customRestResult(url, params, t, "get");
	}

	@SuppressWarnings("unchecked")
	private <T> T mockData(String url, Class<T> mockCls, String requestMsg) {

		if (mockCls == InsureRemote.class && ConstantCls.TRAFFIC_VEHICLE_URL.equals(url)) {
			return (T) InsureRemote.mockTrafficInsureRemote();
		}

		if (mockCls == InsureRemote.class && ConstantCls.RENEW_POLICY_URL.equals(url)) {
			return (T) InsureRemote.mockReNewPolicy();
		}

		if (mockCls == InsureRemote.class && ConstantCls.PURE_RISK_INFO.equals(url)) {
			return (T) InsureRemote.mockPureRiskInfo();
		}

		if (mockCls == InsureRemote.class && ConstantCls.QUOTED_PRICE_URL.equals(url)) {
			InsureRemote insureRemote = JSONObject.parseObject(requestMsg, InsureRemote.class);
			insureRemote = mockInsureRemote(insureRemote);
			Integer randomValue = new Random().nextInt(8);
			if (randomValue % 2 == 0) {
				insureRemote.setMessage(mockMessage.get(new Random().nextInt(5)));
				insureRemote.setResultCode("0001");
			} else {
				insureRemote.setResultCode("0000");
			}
			return (T) insureRemote;
		}

		if (mockCls == SynchroModel.class && ConstantCls.SYSNCHRO_URL.equals(url)) {
			SynchroModel synchroModel = new SynchroModel();
			synchroModel.setCode("0000");
			Integer randomValue = new Random().nextInt(8);
			if (randomValue % 2 == 0) {
				synchroModel.setCode("0001");
			}
			return (T) synchroModel;
		}

		return null;
	}

	public <T> boolean analyseResult(T t) {

		if (t instanceof SmsModel) {
			SmsModel result = (SmsModel) t;
			if ("0000".equals(result.getCode())) {
				return true;
			}
			return false;
		}
		
		
		if(t instanceof SynchroModel) {
			SynchroModel result = (SynchroModel) t;
			if ("0000".equals(result.getCode())) {
				return true;
			}
			return false;
		}
		
		if(t instanceof OrderCenterVo) {
			OrderCenterVo result = (OrderCenterVo) t;
			if ("0000".equals(result.getCode())) {
				return true;
			}
			return false;
		}
		
		if(t instanceof InsureRemote) {
			InsureRemote result = (InsureRemote) t;
			if ("0000".equals(result.getResultCode())) {
				return true;
			}
			return false;
		}

		return false;

	}

	public InsureRemote mockInsureRemote(InsureRemote insureRemote) {

		PremiumFloatingItemsRemote premiumFloatingItemsRemote = new PremiumFloatingItemsRemote();
		premiumFloatingItemsRemote.setPremiumFloatingItemInfo(UUID.randomUUID().toString());
		insureRemote.setPremiumFloatingItemsRemote(premiumFloatingItemsRemote);

		InsuranceRiskInformationRemote insuranceRiskInformationRemote = new InsuranceRiskInformationRemote();
		insuranceRiskInformationRemote.setSimpleInsuranceInfo(UUID.randomUUID().toString());
		insureRemote.setInsuranceRiskInformationRemote(insuranceRiskInformationRemote);

		return insureRemote;

	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setMessageProducer(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	}

	public void setIsMock(Boolean isMock) {
		this.isMock = isMock;
	}

	public static void setMockMessage(List<String> mockMessage) {
		HttpClient.mockMessage = mockMessage;
	}

}
