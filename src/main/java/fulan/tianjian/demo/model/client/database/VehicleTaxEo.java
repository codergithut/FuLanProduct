package fulan.tianjian.demo.model.client.database;

import fulan.tianjian.demo.model.client.remote.VehicleRemote;
import org.springframework.beans.BeanUtils;

/**
 * Created by tianjian on 2021/6/20.
 */
public class VehicleTaxEo {
    /**
     * 车船税开始时间
     */
    private String vehicleTaxStartTime;

    /**
     * 车船税结速时间
     */
    private String vehicleTaxEndTime;

    /**
     * 车船税纳税类型
     */
    private String vehicleTaxType;

    /**
     * 历年拒缴金额
     */
    private String vehicleTaxRefuseAmount;

    /**
     * 改单车船税数据情况
     */
    private String orderNumber;

    public VehicleRemote coverVehicleRemote(VehicleRemote vehicleRemote) {
        if(vehicleRemote == null) {
            return null;
        }
        BeanUtils.copyProperties(this, vehicleRemote);
        return vehicleRemote;
    }
}
