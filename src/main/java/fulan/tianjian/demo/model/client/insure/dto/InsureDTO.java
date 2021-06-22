package fulan.tianjian.demo.model.client.insure.dto;

import java.util.List;

/**
 * 保险基础数据封装
 */
public class InsureDTO {

    /**
     * 当前保单配置信息
     */
    private InsureConfigDTO insureConfigDTO;

    /**
     * 当前投保人受益人车主信息
     */
    private List<InsurePersonDTO> insurePersons;

    /**
     * 当前订单保险方案
     */
    private List<PolicySchemeDTO> policySchemes;

    /**
     * 当前保单车辆数据
     */
    private VehicleDTO vehicleDTO;

    /**
     * 报价核保方法区分
     */
    private String insureType;


    /**
     * 订单号
     */
    private String orderNumber;


    public InsureConfigDTO getInsureConfigDTO() {
        return insureConfigDTO;
    }

    public void setInsureConfigDTO(InsureConfigDTO insureConfigDTO) {
        this.insureConfigDTO = insureConfigDTO;
    }
    
    public List<InsurePersonDTO> getInsurePersons() {
		return insurePersons;
	}

	public void setInsurePersons(List<InsurePersonDTO> insurePersons) {
		this.insurePersons = insurePersons;
	}

	public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public String getInsureType() {
        return insureType;
    }

    public void setInsureType(String insureType) {
        this.insureType = insureType;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

	public List<PolicySchemeDTO> getPolicySchemes() {
		return policySchemes;
	}

	public void setPolicySchemes(List<PolicySchemeDTO> policySchemes) {
		this.policySchemes = policySchemes;
	}
    
    
}
