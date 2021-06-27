package fulan.tianjian.demo.model.web.vo;

public class VehicleBaseVo {
	
	 /**
     * 车架号
     */
    private String vinCode;

    /**
     * 车牌号
     */
    private String plateNo;
    
    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * 三方车型编码
     */
    private String vehicleCode;

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
	
	
    
   
}
