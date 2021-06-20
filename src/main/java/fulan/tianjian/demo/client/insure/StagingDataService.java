package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.model.client.database.*;
import fulan.tianjian.demo.model.client.insure.VehicleDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 暂存数据
 * Created by tianjian on 2021/6/20.
 */
@Service
public class StagingDataService {
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
        return null;
    }

    public boolean saveVehicleDetail(VehicleDetailEo vehicleDetailEo) {
        return true;
    }

    public VehicleDetailEo getVehicleDetailByMd5ValueAndSource(String md5ValuePart, String renewVehicleDetail) {
        return null;
    }

    public List<VehicleDetailEo> findVehicleDetailByVehicleDTO(VehicleDTO vehicleDTO) {
        String vehicleCode = vehicleDTO.getVehicleCode();
        String md5Value = vehicleDTO.getMd5Value();
        return null;
    }
}
