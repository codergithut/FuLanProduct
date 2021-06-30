package fulan.tianjian.demo.model.client.convert;

import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;
import org.springframework.beans.BeanUtils;

import static fulan.tianjian.demo.constant.ConstantCls.THIRD_VEHICLE_DETAIL;

/**
 * 车辆模型转换
 */
public class VehicleConvertUtil {
    public static VehicleDetailEo convertThirdPartyVehicle(ThirdPartyVehicle thirdPartyVehicle) {
        VehicleDetailEo vehicleDetailEo = new VehicleDetailEo();
        BeanUtils.copyProperties(thirdPartyVehicle, vehicleDetailEo);
        vehicleDetailEo.setDataSource(THIRD_VEHICLE_DETAIL);
        return vehicleDetailEo;
    }

    public static VehicleRemote convertVehicleRemoteByDTO(VehicleDTO vehicleDTO){
        VehicleRemote vehicleRemote = new VehicleRemote();
        BeanUtils.copyProperties(vehicleDTO, vehicleRemote);
        return vehicleRemote;
    }

    public static VehicleDetailEo convertRemoteToEo(VehicleRemote vehicleDetail, String tag) {
        VehicleDetailEo vehicleDetailEo = new VehicleDetailEo();
        BeanUtils.copyProperties(vehicleDetail, vehicleDetailEo);
        vehicleDetailEo.setDataSource(tag);
        return vehicleDetailEo;
    }
}
