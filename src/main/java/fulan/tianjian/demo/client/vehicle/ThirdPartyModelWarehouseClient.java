package fulan.tianjian.demo.client.vehicle;

import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;

public interface ThirdPartyModelWarehouseClient {

    /**
     *
     * @param vehicleCode 车型编码
     * @return 车辆模型数据
     */
    ThirdPartyVehicle getVehicleTemplateDTO(String vehicleCode);
}
