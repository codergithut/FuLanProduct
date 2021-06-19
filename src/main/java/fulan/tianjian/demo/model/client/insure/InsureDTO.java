package fulan.tianjian.demo.model.client.insure;

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
    private InsurePersonDTO insurePersonDTO;

    /**
     * 当前订单保险方案
     */
    private PolicySchemeDTO policySchemeDTO;

    /**
     * 当前保单车辆数据
     */
    private AllVehicleDTO allVehicleDTO;


    public InsureConfigDTO getInsureConfigDTO() {
        return insureConfigDTO;
    }

    public void setInsureConfigDTO(InsureConfigDTO insureConfigDTO) {
        this.insureConfigDTO = insureConfigDTO;
    }

    public InsurePersonDTO getInsurePersonDTO() {
        return insurePersonDTO;
    }

    public void setInsurePersonDTO(InsurePersonDTO insurePersonDTO) {
        this.insurePersonDTO = insurePersonDTO;
    }

    public PolicySchemeDTO getPolicySchemeDTO() {
        return policySchemeDTO;
    }

    public void setPolicySchemeDTO(PolicySchemeDTO policySchemeDTO) {
        this.policySchemeDTO = policySchemeDTO;
    }

    public AllVehicleDTO getAllVehicleDTO() {
        return allVehicleDTO;
    }

    public void setAllVehicleDTO(AllVehicleDTO allVehicleDTO) {
        this.allVehicleDTO = allVehicleDTO;
    }
}
