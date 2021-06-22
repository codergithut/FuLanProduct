package fulan.tianjian.demo.model.client.insure.database;

/**
 * Created by tianjian on 2021/6/20.
 */
public class VehicleDetailEo {

    private String id;

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
     * 车辆实际价值
     */
    private String currentPrice;


    /**
     * 车辆性质编码
     */
    private String vehicleStyle;


    /**
     * 交管车辆编码
     */
    private String pmVehicleStyle;

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
     * 0 三方车型库数据 1 续保查询数据 2 交管查询数据
     */
    private String dataSource;

    /**
     * md5
     */
    private String md5Value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(String currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getVehicleStyle() {
        return vehicleStyle;
    }

    public void setVehicleStyle(String vehicleStyle) {
        this.vehicleStyle = vehicleStyle;
    }

    public String getPmVehicleStyle() {
        return pmVehicleStyle;
    }

    public void setPmVehicleStyle(String pmVehicleStyle) {
        this.pmVehicleStyle = pmVehicleStyle;
    }

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

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}
    
    
}
