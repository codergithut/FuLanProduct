package fulan.tianjian.demo.model.client.insure.notice;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "insure_record_log")
public class NoticeEsMessage {
	
	@Id
	private String id;
	
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
	 * 邮编代码
	 */
	private String regionCode;
	
	
	/**
	 * 是否成功
	 */
	private String isSuccess;
	
	/**
	 * 创建时间
	 */
	private Date createDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getInsurantMobileNumber() {
		return insurantMobileNumber;
	}

	public void setInsurantMobileNumber(String insurantMobileNumber) {
		this.insurantMobileNumber = insurantMobileNumber;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	

}
