package fulan.tianjian.demo.model.web.eo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.web.vo.VehicleVo;


/**
 * 车辆视图模型
 * Created by tianjian on 2021/6/20.
 */
@Entity
@Table(name = "vehicle_base")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class VehicleEo {

    /**
     * id
     */
	@Id
	@GeneratedValue(generator = "jpa-uuid")
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
     * 订单编码
     */
    private String orderNumber;

    private String identityCardNumber;
    
    /**
     * 地区编码
     */
    private String regionCode;

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

	

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public VehicleVo convertToVo() {
		VehicleVo vehicle = new VehicleVo();
		BeanUtils.copyProperties(this, vehicle);
		return vehicle;
	}
    
    
    
}
