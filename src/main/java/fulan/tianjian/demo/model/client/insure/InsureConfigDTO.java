package fulan.tianjian.demo.model.client.insure;

import java.util.List;

/**
 * 保单的基础配置信息
 */
public class InsureConfigDTO {
    /**
     * 报价模式
     */
    private String quotationMode;

    /**
     * 是否电子投保单
     */
    private String isElectronicApplicationForm;


    /**
     * 是否需要人脸识别
     */
    private String isFaceRecognition;


    /**
     * 销售渠道
     */
    private String distributionChannel;


    /**
     * 代理点名称
     */
    private String agentPointName;


    /**
     * 代理点编码
     */
    private String agentPointCode;

    /**
     * 保险操作人信息
     */
    private List<InsureHandlePersonDTO> insureHandlePersonDTOS;

	public String getQuotationMode() {
		return quotationMode;
	}

	public void setQuotationMode(String quotationMode) {
		this.quotationMode = quotationMode;
	}

	public String getIsElectronicApplicationForm() {
		return isElectronicApplicationForm;
	}

	public void setIsElectronicApplicationForm(String isElectronicApplicationForm) {
		this.isElectronicApplicationForm = isElectronicApplicationForm;
	}

	public String getIsFaceRecognition() {
		return isFaceRecognition;
	}

	public void setIsFaceRecognition(String isFaceRecognition) {
		this.isFaceRecognition = isFaceRecognition;
	}

	public String getDistributionChannel() {
		return distributionChannel;
	}

	public void setDistributionChannel(String distributionChannel) {
		this.distributionChannel = distributionChannel;
	}

	public String getAgentPointName() {
		return agentPointName;
	}

	public void setAgentPointName(String agentPointName) {
		this.agentPointName = agentPointName;
	}

	public String getAgentPointCode() {
		return agentPointCode;
	}

	public void setAgentPointCode(String agentPointCode) {
		this.agentPointCode = agentPointCode;
	}

	public List<InsureHandlePersonDTO> getInsureHandlePersonDTOS() {
		return insureHandlePersonDTOS;
	}

	public void setInsureHandlePersonDTOS(List<InsureHandlePersonDTO> insureHandlePersonDTOS) {
		this.insureHandlePersonDTOS = insureHandlePersonDTOS;
	}
    

}
