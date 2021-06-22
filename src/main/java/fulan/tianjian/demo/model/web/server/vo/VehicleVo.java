package fulan.tianjian.demo.model.web.server.vo;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.VehicleDTO;

/**
 * 车辆视图模型
 * Created by tianjian on 2021/6/20.
 */
public class VehicleVo {

    /**
     * 车架号
     */
    private String vinCode;

    /**
     * 车牌号
     */
    private String plateNo;

    /**
     * 车牌颜色
     */
    private String plateColor;

    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * 三方车型编码
     */
    private String vehicleCode;

    /**
     * 注册日期
     */
    private String registerDate;

    /**
     * 发证日期
     */
    private String certificateDate;

    /**
     * 是否新能源车
     */
    private String isNewEnergyResourcesVehicle;

    /**
     * 车辆转移日期
     */
    private String transferDate;
    
    /**
     * 订单编号
     */
    private String orderNumber;
    
    /**
     * 地区编码
     */
    private String regionCode;
    
    
    
    public String getVinCode() {
		return vinCode;
	}



	public void setVinCode(String vinCode) {
		this.vinCode = vinCode;
	}



	public String getPlateNo() {
		return plateNo;
	}



	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}



	public String getPlateColor() {
		return plateColor;
	}



	public void setPlateColor(String plateColor) {
		this.plateColor = plateColor;
	}



	public String getEngineNo() {
		return engineNo;
	}



	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}



	public String getVehicleCode() {
		return vehicleCode;
	}



	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}



	public String getRegisterDate() {
		return registerDate;
	}



	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}



	public String getCertificateDate() {
		return certificateDate;
	}



	public void setCertificateDate(String certificateDate) {
		this.certificateDate = certificateDate;
	}



	public String getIsNewEnergyResourcesVehicle() {
		return isNewEnergyResourcesVehicle;
	}



	public void setIsNewEnergyResourcesVehicle(String isNewEnergyResourcesVehicle) {
		this.isNewEnergyResourcesVehicle = isNewEnergyResourcesVehicle;
	}



	public String getTransferDate() {
		return transferDate;
	}



	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}



	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}



	public String getRegionCode() {
		return regionCode;
	}



	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}



	public VehicleDTO convertToDTO() {
    	VehicleDTO vehicleDTO = new VehicleDTO();
    	BeanUtils.copyProperties(this, vehicleDTO);
    	return vehicleDTO;
    }
}
