package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.constant.ConstantCls;
import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationCurd;
import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsCurd;
import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.insure.database.PureRiskCurd;
import fulan.tianjian.demo.model.client.insure.database.PureRiskEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailCurd;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleTaxCurd;
import fulan.tianjian.demo.model.client.insure.database.VehicleTaxEo;
import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;
import fulan.tianjian.demo.util.CommonUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

/**
 * 暂存数据
 * Created by tianjian on 2021/6/20.
 */
@Service
public class StagingDataService {
	
	@Autowired
	private VehicleDetailCurd vehicleDetailCurd;
	
	@Autowired
	private PureRiskCurd pureRiskCurd;
	
	@Autowired
	private InsuranceRiskInformationCurd insuranceRiskInformationCurd;
	
	@Autowired
	private PremiumFloatingItemsCurd premiumFloatingItemsCurd;
	
	@Autowired
	private VehicleTaxCurd vehicleTaxCurd;
	
    /**
     * 根据车辆实际情况获取存风险保费
     * @param md5Value
     * @return
     */
    public PureRiskEo getPureRiskEoByMd5(String md5Value) {
    	PureRiskEo pureRisk = pureRiskCurd.findByMd5Value(md5Value);
        return pureRisk;
    }

    public boolean savePureRiskEo(PureRiskEo pureRiskEo) {
    	
    	String md5Value = pureRiskEo.getMd5Value();
    	
    	if(getPureRiskEoByMd5(md5Value) != null) {
    		return true;
    	}
    	pureRiskCurd.save(pureRiskEo);
    	
        return true;
    }

    public InsuranceRiskInformationEo getInsuranceRiskInformationEoByOrderNumber(String orderNumber){
    	InsuranceRiskInformationEo insuranceRiskInformationEo = insuranceRiskInformationCurd.findByOrderNumber(orderNumber);
        return insuranceRiskInformationEo;
    }

    @Transactional
    public boolean saveInsureRiskInformationEo(InsuranceRiskInformationEo insuranceRiskInformationEo){
    	insuranceRiskInformationCurd.deleteByOrderNumber(insuranceRiskInformationEo.getOrderNumber());
    	insuranceRiskInformationCurd.save(insuranceRiskInformationEo);
        return true;
    }

    public PremiumFloatingItemsEo getPremiumFloatingItemsEoByOrderNumber(String orderNumber) {
    	PremiumFloatingItemsEo premiumFloatingItemsEo = premiumFloatingItemsCurd.findByOrderNumber(orderNumber);
        return premiumFloatingItemsEo;
    }

    @Transactional
    public boolean savePremiumFloatingItemsEo(PremiumFloatingItemsEo premiumFloatingItemsEo) {
    	premiumFloatingItemsCurd.deleteByOrderNumber(premiumFloatingItemsEo.getOrderNumber());
    	premiumFloatingItemsCurd.save(premiumFloatingItemsEo);
        return true;
    }

    public VehicleTaxEo getVehicleTaxEoByOrderNumber(String orderNumber){
    	VehicleTaxEo vehicleTaxEo = vehicleTaxCurd.findByOrderNumber(orderNumber);
        return vehicleTaxEo;
    }

    @Transactional
    public boolean saveVehicleTaxEo(VehicleTaxEo vehicleTaxEo) {
    	vehicleTaxCurd.deleteByOrderNumber(vehicleTaxEo.getOrderNumber());
    	vehicleTaxCurd.save(vehicleTaxEo);
        return true;
    }

    public VehicleDetailEo getVehicleDetailByVehicleCode(String vehicleCode) {
    	VehicleDetailEo vehicleDetailEo = vehicleDetailCurd.findByVehicleCodeAndDataSource(vehicleCode, ConstantCls.THIRD_VEHICLE_DETAIL);
    	if(vehicleDetailEo != null) {
    		return vehicleDetailEo;
    	}
        return null;
    }

    public boolean saveVehicleDetail(VehicleDetailEo vehicleDetailEo) {
    	
        return vehicleDetailCurd.save(vehicleDetailEo) != null;
    }

    public VehicleDetailEo getVehicleDetailByMd5ValueAndSource(String md5ValuePart, String vehicleSource) {
    	VehicleDetailEo vehicleDetailEo = vehicleDetailCurd.findByMd5ValueAndDataSource(md5ValuePart, vehicleSource);
        return vehicleDetailEo;
    }

    public List<VehicleDetailEo> findVehicleDetailByVehicleDTO(VehicleDTO vehicleDTO) {
    	String vehicleCode = vehicleDTO.getVehicleCode();
    	String plateNo = vehicleDTO.getPlateNo();
    	String vinCode = vehicleDTO.getVinCode();
    	String engineNo = vehicleDTO.getEngineNo();
    	String md5Value = CommonUtil.getMd5Value(plateNo, vinCode, engineNo);
    	
    	
    	List<VehicleDetailEo> allVehicleDetail = new ArrayList<VehicleDetailEo>();
    	if(StringUtils.isNoneBlank(vehicleCode)) {
    		VehicleDetailEo thirdVehicle =  getVehicleDetailByVehicleCode(vehicleCode);
    		allVehicleDetail.add(thirdVehicle);
    	}
    	
    	VehicleDetailEo renewVehicle = getVehicleDetailByMd5ValueAndSource(md5Value, ConstantCls.RENEW_VEHICLE_DETAIL);
    	
    	if(renewVehicle != null) {
    		allVehicleDetail.add(renewVehicle);
    	}
    	
    	VehicleDetailEo trafficVehicle = getVehicleDetailByMd5ValueAndSource(md5Value, ConstantCls.TRAFFIC_MANGEMENT_VEHICLE_DETAIL);
    	
    	if(trafficVehicle != null) {
    		allVehicleDetail.add(trafficVehicle);
    	}
    	
        return allVehicleDetail;
    }
    
   
}
