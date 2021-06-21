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
    
    public VehicleDTO convertToDTO() {
    	VehicleDTO vehicleDTO = new VehicleDTO();
    	BeanUtils.copyProperties(this, vehicleDTO);
    	return vehicleDTO;
    }
}
