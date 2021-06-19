package fulan.tianjian.demo.model.client.convert;

import fulan.tianjian.demo.model.client.insure.AllVehicleDTO;
import fulan.tianjian.demo.model.client.insure.VehicleDTO;
import fulan.tianjian.demo.model.client.insure.VehicleTemplateDTO;
import fulan.tianjian.demo.model.client.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;
import org.springframework.beans.BeanUtils;

/**
 * 车辆模型转换
 */
public class VehicleConvertUtil {
    public static VehicleTemplateDTO convertThirdPartyVehicle(ThirdPartyVehicle thirdPartyVehicle) {
        VehicleTemplateDTO vehicleTemplateDTO = new VehicleTemplateDTO();
        BeanUtils.copyProperties(thirdPartyVehicle, vehicleTemplateDTO);
        return vehicleTemplateDTO;
    }

    public static VehicleRemote convertVehicleRemoteByDTO(AllVehicleDTO allVehicleDTO){
        VehicleRemote vehicleRemote = new VehicleRemote();
        BeanUtils.copyProperties(vehicleRemote, allVehicleDTO.getVehicleDTO());
        BeanUtils.copyProperties(vehicleRemote, allVehicleDTO.getVehicleTemplateDTO());
        return vehicleRemote;
    }
}
