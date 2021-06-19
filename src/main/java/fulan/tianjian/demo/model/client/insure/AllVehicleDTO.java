package fulan.tianjian.demo.model.client.insure;

/**
 * 车辆完整数据
 */
public class AllVehicleDTO {

    /**
     * 车辆个性数据
     */
    private VehicleDTO vehicleDTO;

    /**
     * 车辆共性数据
     */
    private VehicleTemplateDTO vehicleTemplateDTO;

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    public VehicleTemplateDTO getVehicleTemplateDTO() {
        return vehicleTemplateDTO;
    }

    public void setVehicleTemplateDTO(VehicleTemplateDTO vehicleTemplateDTO) {
        this.vehicleTemplateDTO = vehicleTemplateDTO;
    }
}
