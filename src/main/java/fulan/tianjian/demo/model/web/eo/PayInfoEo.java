package fulan.tianjian.demo.model.web.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "pay_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PayInfoEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	/**
	 * 订单id
	 */
	private String orderNumber;
	
	/**
     * 支付号
     */
    private String payNo;

    /**
     * 订单号
     */
    private String businessId;

    /**
     * 投保单号
     */
    private String insurancePolicyNumber;

    /**
     * 支付方式 0 支付宝 1 微信 2 银行支付
     */
    private String payWay;

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getInsurancePolicyNumber() {
		return insurancePolicyNumber;
	}

	public void setInsurancePolicyNumber(String insurancePolicyNumber) {
		this.insurancePolicyNumber = insurancePolicyNumber;
	}

	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}

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
	
	
    
    

}
