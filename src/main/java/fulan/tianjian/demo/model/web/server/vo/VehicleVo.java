package fulan.tianjian.demo.model.web.server.vo;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;
import fulan.tianjian.demo.util.CommonUtil;

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
    private Date registerDate;

    /**
     * 发证日期
     */
    private Date certificateDate;

    /**
     * 是否新能源车
     */
    private String isNewEnergyResourcesVehicle;

    /**
     * 车辆转移日期
     */
    private Date transferDate;
    
    /**
     * 订单编号
     */
    private String orderNumber;
    
    /**
     * 地区编码
     */
    private String regionCode;
    
    /**
     * url携带参数编码
     */
    private String configId;
    
    /**
     * 用户id
     */
    private String identityCardNumber;
    
    /**
     * 车辆实际价值
     */
    private String currentPrice; 
    
    
    
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


	public String getIsNewEnergyResourcesVehicle() {
		return isNewEnergyResourcesVehicle;
	}



	public void setIsNewEnergyResourcesVehicle(String isNewEnergyResourcesVehicle) {
		this.isNewEnergyResourcesVehicle = isNewEnergyResourcesVehicle;
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
	

	public String getConfigId() {
		return configId;
	}
	
	
	public Date getRegisterDate() {
		return registerDate;
	}



	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}



	public Date getCertificateDate() {
		return certificateDate;
	}



	public void setCertificateDate(Date certificateDate) {
		this.certificateDate = certificateDate;
	}



	public Date getTransferDate() {
		return transferDate;
	}



	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}



	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}



	public String getCurrentPrice() {
		return currentPrice;
	}



	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}



	public void setConfigId(String configId) {
		this.configId = configId;
	}
	
	public static VehicleVo mockVehicleVo(String orderNumber) {
		VehicleVo vehicleVo = new VehicleVo();
		vehicleVo.setCertificateDate(CommonUtil.strToDate("20180912100000"));
		vehicleVo.setEngineNo("9403958YKG86HC76");
		vehicleVo.setIsNewEnergyResourcesVehicle("Y");
		vehicleVo.setOrderNumber(orderNumber);
		vehicleVo.setPlateColor("02");
		vehicleVo.setPlateNo("苏A9876O");
		vehicleVo.setRegionCode("215000");
		vehicleVo.setRegisterDate(CommonUtil.strToDate("20190912100000"));
		vehicleVo.setTransferDate(CommonUtil.strToDate("20191012100000"));
		vehicleVo.setVehicleCode("dIhuv9");
		vehicleVo.setVinCode("8493082909837653");
		vehicleVo.setIdentityCardNumber("320987198006073429");
		return vehicleVo;
	}


	public VehicleDTO convertToDTO() {
    	VehicleDTO vehicleDTO = new VehicleDTO();
    	BeanUtils.copyProperties(this, vehicleDTO);
    	return vehicleDTO;
    }
	
	public static void main(String[] args) {
		VehicleVo vehicleVo = mockVehicleVo(UUID.randomUUID().toString());
		System.out.println(JSON.toJSONString(vehicleVo));
		
	}

}
 