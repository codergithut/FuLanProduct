package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;

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
    
    

    public String getVehicleTaxStartTime() {
		return vehicleTaxStartTime;
	}



	public void setVehicleTaxStartTime(String vehicleTaxStartTime) {
		this.vehicleTaxStartTime = vehicleTaxStartTime;
	}



	public String getVehicleTaxEndTime() {
		return vehicleTaxEndTime;
	}



	public void setVehicleTaxEndTime(String vehicleTaxEndTime) {
		this.vehicleTaxEndTime = vehicleTaxEndTime;
	}



	public String getVehicleTaxType() {
		return vehicleTaxType;
	}



	public void setVehicleTaxType(String vehicleTaxType) {
		this.vehicleTaxType = vehicleTaxType;
	}



	public String getVehicleTaxRefuseAmount() {
		return vehicleTaxRefuseAmount;
	}



	public void setVehicleTaxRefuseAmount(String vehicleTaxRefuseAmount) {
		this.vehicleTaxRefuseAmount = vehicleTaxRefuseAmount;
	}



	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}



	public VehicleRemote coverVehicleRemote(VehicleRemote vehicleRemote) {
        if(vehicleRemote == null) {
            return null;
        }
        BeanUtils.copyProperties(this, vehicleRemote);
        return vehicleRemote;
    }
}
