package fulan.tianjian.demo.model.client.vehicle;



/**
 * 第三方车型库
 */
public class ThirdPartyVehicle {

    /**
     * 车辆品牌名称
     */
    private String brandName;

    /**
     * 车辆排量
     */
    private String displacement;

    /**
     * 新车购置价格
     */
    private String acquisitionPrice;

    /**
     * 生产厂商
     */
    private String manufacturer;

    /**
     * 车辆型号
     */
    private String vehicleModel;

    /**
     * 车辆座位数目
     */
    private String seat;

    /**
     * 核定载客吨数
     */
    private String tonnagePassengers;

    /**
     * 燃料类型
     */
    private String fuelType;
    
    /**
     * 车辆编码
     */
    private String vehicleCode;

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getDisplacement() {
		return displacement;
	}

	public void setDisplacement(String displacement) {
		this.displacement = displacement;
	}

	public String getAcquisitionPrice() {
		return acquisitionPrice;
	}

	public void setAcquisitionPrice(String acquisitionPrice) {
		this.acquisitionPrice = acquisitionPrice;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getTonnagePassengers() {
		return tonnagePassengers;
	}

	public void setTonnagePassengers(String tonnagePassengers) {
		this.tonnagePassengers = tonnagePassengers;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	
	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public static ThirdPartyVehicle mockThirdPartyVehicle(String vehicleCode) {
		ThirdPartyVehicle vehicle = new ThirdPartyVehicle();
		vehicle.setVehicleCode(vehicleCode);
		vehicle.setFuelType("A");
		vehicle.setBrandName("上海大众");
		vehicle.setAcquisitionPrice("150000");
		vehicle.setFuelType("A");
		vehicle.setDisplacement("1.6");
		vehicle.setManufacturer("DKF");
		vehicle.setSeat("5");
		vehicle.setTonnagePassengers("3");
		vehicle.setVehicleModel("上海大众桑塔纳");
		return vehicle;
		
	}
    

}
