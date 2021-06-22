package fulan.tianjian.demo.model.client.remote;

/**
 * 核保报价返回数据
 */
public class InsureResultRemote {

    /**
     * 请求返回的保险对象
     */
    private InsureRemote insureRemote;

    /**
     * 返回编码
     */
    private String resultCode;

    /**
     * 消息封装
     */
    private String resultMsg;

    /**
     * 订单编码
     */
    private String businessCode;

	public InsureRemote getInsureRemote() {
		return insureRemote;
	}

	public void setInsureRemote(InsureRemote insureRemote) {
		this.insureRemote = insureRemote;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getBusinessCode() {
		return businessCode;
	}

	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}


}
