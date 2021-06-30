package fulan.tianjian.demo.model.web.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.InsureConfigDTO;

@Entity
@Table(name = "insure_config")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class InsureConfigEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


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
	
    
}
