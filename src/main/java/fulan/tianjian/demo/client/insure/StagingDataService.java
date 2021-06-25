package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.insure.database.PureRiskEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailCurd;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleTaxEo;
import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 暂存数据
 * Created by tianjian on 2021/6/20.
 */
@Service
public class StagingDataService {
	
	@Autowired
	private VehicleDetailCurd vehicleDetailCurd;
	
    /**
     * 根据车辆实际情况获取存风险保费
     * @param md5Value
     * @return
     */
    public PureRiskEo getPureRiskEoByMd5(String md5Value) {
        return null;
    }

    public boolean savePureRiskEo(PureRiskEo pureRiskEo) {
        return true;
    }

    public InsuranceRiskInformationEo getInsuranceRiskInformationEoByOrderNumber(String orderNumber){
        return null;
    }

    public boolean saveInsureRiskInformationEo(InsuranceRiskInformationEo insuranceRiskInformationEo){
        return true;
    }

    public PremiumFloatingItemsEo getPremiumFloatingItemsEoByOrderNumber(String orderNumber) {
        return null;
    }

    public boolean savePremiumFloatingItemsEo() {
        return true;
    }

    public VehicleTaxEo getVehicleTaxEoByOrderNumber(String orderNumber){
        return null;
    }

    public boolean saveVehicleTaxEo(VehicleTaxEo vehicleTaxEo) {
        return true;
    }

    public VehicleDetailEo getVehicleDetailByVehicleCode(String vehicleCode) {
    	VehicleDetailEo vehicleDetailEo = vehicleDetailCurd.findByVehicleCode(vehicleCode);
    	if(vehicleDetailEo != null) {
    		return vehicleDetailEo;
    	}
        return null;
    }

    public boolean saveVehicleDetail(VehicleDetailEo vehicleDetailEo) {
    	
        return vehicleDetailCurd.save(vehicleDetailEo) != null;
    }

    public VehicleDetailEo getVehicleDetailByMd5ValueAndSource(String md5ValuePart, String renewVehicleDetail) {
        return null;
    }

    public List<VehicleDetailEo> findVehicleDetailByVehicleDTO(VehicleDTO vehicleDTO) {
    	//todo 处理
//        String vehicleCode = vehicleDTO.getVehicleCode();
//        String md5Value = vehicleDTO.getMd5Value();
        return null;
    }
}
