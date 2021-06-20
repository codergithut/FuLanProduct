package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.model.client.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.database.PureRiskEo;
import fulan.tianjian.demo.model.client.database.VehicleTaxEo;

/**
 * Created by tianjian on 2021/6/20.
 */
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
}
