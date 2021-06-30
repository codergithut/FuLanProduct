package fulan.tianjian.demo.model.client.insure.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.InsureConfigRemote;
import fulan.tianjian.demo.model.client.insure.remote.InsureHandlePersonRemote;
import fulan.tianjian.demo.model.web.eo.InsureConfigEo;
import fulan.tianjian.demo.model.web.vo.InsureConfigVo;

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
     * 省份编码
     */
    private String provinceCode;

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
	
	
	
	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public InsureConfigEo convertToEo() {
		InsureConfigEo insureConfigEo = new InsureConfigEo();
		BeanUtils.copyProperties(this, insureConfigEo);
		return insureConfigEo;
		
	}
	
	
	public InsureConfigVo convertToVo() {
		InsureConfigVo insureConfigVo = new InsureConfigVo();
		BeanUtils.copyProperties(this, insureConfigVo);
		return insureConfigVo;
	}
	
	public InsureConfigRemote convertToRemote() {
		InsureConfigRemote insureConfigRemote = new InsureConfigRemote();
		BeanUtils.copyProperties(this, insureConfigRemote);
		List<InsureHandlePersonRemote> persons = insureHandlePersonDTOS.stream().map(e -> {
			return e.convertToRemote();
		}).collect(Collectors.toList());
		insureConfigRemote.setInsureHandlePersonRemotes(persons);
		return insureConfigRemote;
	}
    

}
