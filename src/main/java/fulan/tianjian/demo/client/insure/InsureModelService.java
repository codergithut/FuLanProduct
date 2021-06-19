package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.remote.InsureRemote;
import fulan.tianjian.demo.model.client.remote.PureRiskInfoRemote;
import fulan.tianjian.demo.model.client.remote.VehicleRemote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static fulan.tianjian.demo.model.client.convert.VehicleConvertUtil.convertVehicleRemoteByDTO;

@Service
public class InsureModelService {

    @Autowired
    private InsureRemoteService insureRemoteService;


    public InsureRemote createInsureRemoteByInsureDTO(InsureDTO insureDTO, String type){
        InsureRemote insureRemote = new InsureRemote();
        VehicleRemote vehicleRemote = convertVehicleRemoteByDTO(insureDTO.getAllVehicleDTO());
        PureRiskInfoRemote pureRiskInfoRemote = getPureRiskInfoByVehicle(vehicleRemote);
        insureRemote.setPureRiskInfoRemote(pureRiskInfoRemote);
        return null;
    }

    private PureRiskInfoRemote getPureRiskInfoByVehicle(VehicleRemote vehicleRemote) {

        return null;
    }
}
