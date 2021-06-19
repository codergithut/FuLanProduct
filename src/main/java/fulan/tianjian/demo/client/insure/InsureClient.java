package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.model.client.insure.AllVehicleDTO;
import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.insure.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.VehicleTemplateDTO;

public interface InsureClient {

    /**
     * 报价接口
     * @param insureDTO
     * @return
     */
    InsureResultDTO quotedPrice(InsureDTO insureDTO);

    /**
     * 核保接口
     * @param insureDTO
     * @return
     */
    InsureResultDTO underwrite(InsureDTO insureDTO);

    /**
     * 获取续保数据
     * @param platNo
     * @param vin
     * @param engineNo
     * @return
     */
    InsureResultDTO getRenewPolicy(String platNo, String vin, String engineNo);

    /**
     *
     * @param vehicleCode 车型编码
     * @return 车辆模型数据
     */
    VehicleTemplateDTO getVehicleTemplateDTO(String vehicleCode);

    /**
     * 获取交管车辆数据
     * @param plateNo
     * @param vin
     * @param engineNo
     * @return
     */
    AllVehicleDTO getTrafficMangement(String plateNo, String vin, String engineNo);


}
