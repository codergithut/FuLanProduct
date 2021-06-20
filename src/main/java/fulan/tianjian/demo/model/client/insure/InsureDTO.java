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
}
