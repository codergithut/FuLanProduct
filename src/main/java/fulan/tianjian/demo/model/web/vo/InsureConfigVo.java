package fulan.tianjian.demo.model.web.vo;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.InsureConfigDTO;

public class InsureConfigVo {
	
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
     * 省份编码
     */
    private String provinceCode;

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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public InsureConfigDTO convertToDTO() {
		InsureConfigDTO insureConfigDTO = new InsureConfigDTO();
		BeanUtils.copyProperties(this, insureConfigDTO);
		return insureConfigDTO;
	}
	
	public static InsureConfigVo mockData() {
		InsureConfigVo insureConfigVo = new InsureConfigVo();
		insureConfigVo.setAgentPointCode("agentCode");
		insureConfigVo.setAgentPointName("testAgentName");
		insureConfigVo.setDistributionChannel("14");
		insureConfigVo.setIsElectronicApplicationForm("Y");
		insureConfigVo.setIsFaceRecognition("Y");
		insureConfigVo.setProvinceCode("32");
		insureConfigVo.setQuotationMode("1");
		return insureConfigVo;
	}
    
    

}
