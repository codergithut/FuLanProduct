package fulan.tianjian.demo.model.client.remote;

/**
 * 支付信息
 */
public class PayInfoRemote {

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
}
