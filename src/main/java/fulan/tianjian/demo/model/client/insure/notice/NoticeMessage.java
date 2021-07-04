package fulan.tianjian.demo.model.client.insure.notice;

import org.springframework.context.ApplicationEvent;

public class NoticeMessage extends ApplicationEvent{
	
	
	public NoticeMessage(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单id
	 */
	private String orderNumber;
	
	/**
	 * 订单中心id
	 */
	private String orderCenterCode;
	
	/**
	 * 订单状态
	 */
	private String status;
	
	/**
	 * 报价核保返回结果
	 */
	private String remoteMessage;
	
	/**
     * 车牌号
     */
    private String plateNo;
    
    /**
     * 发动机号
     */
    private String engineNo;

    /**
	 * 车架号
	 */
	private String vinCode;
    
    /**
     * 用户姓名
     */
    private String insurantName;

    /**
     * 身份证号码
     */
    private String insurantIdentityCardNumber;
    
    /**
     * 手机号码
     */
    private String insurantMobileNumber;
    
   
	/**
	 * 支付链接
	 */
	private String payUrl;
	
	
	/**
	 * 是否成功
	 */
	private String isSuccess;
	

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderCenterCode() {
		return orderCenterCode;
	}

	public void setOrderCenterCode(String orderCenterCode) {
		this.orderCenterCode = orderCenterCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemoteMessage() {
		return remoteMessage;
	}

	public void setRemoteMessage(String remoteMessage) {
		this.remoteMessage = remoteMessage;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	
	public String getVinCode() {
		return vinCode;
	}

	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}
	
	

	public String getInsurantName() {
		return insurantName;
	}

	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}

	public String getInsurantIdentityCardNumber() {
		return insurantIdentityCardNumber;
	}

	public void setInsurantIdentityCardNumber(String insurantIdentityCardNumber) {
		this.insurantIdentityCardNumber = insurantIdentityCardNumber;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getInsurantMobileNumber() {
		return insurantMobileNumber;
	}

	public void setInsurantMobileNumber(String insurantMobileNumber) {
		this.insurantMobileNumber = insurantMobileNumber;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

}
