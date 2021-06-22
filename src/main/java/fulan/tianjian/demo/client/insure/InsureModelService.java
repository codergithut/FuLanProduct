package fulan.tianjian.demo.client.insure;

import com.alibaba.fastjson.JSON;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.database.PureRiskEo;
import fulan.tianjian.demo.model.client.database.VehicleTaxEo;
import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.insure.InsureResultDTO;
import fulan.tianjian.demo.model.client.remote.InsureRemote;
import fulan.tianjian.demo.model.client.remote.PureRiskInfoRemote;
import fulan.tianjian.demo.model.client.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static fulan.tianjian.demo.constant.ConstantCls.QUOTED_PRICE_URL;
import static fulan.tianjian.demo.model.client.convert.VehicleConvertUtil.convertVehicleRemoteByDTO;

@Service
public class InsureModelService {

    @Autowired
    private InsureRemoteService insureRemoteService;

    @Autowired
    private StagingDataService stagingDataService;


    /**
     * 创建送核心的保文
     * @param insureDTO 前端收集保险数据
     * @param type 请求核心类型
     * @return
     * @throws PureRiskLossException
     */
    public InsureRemote createInsureRemoteByInsureDTO(InsureDTO insureDTO, String type) throws PureRiskLossException {
        InsureRemote insureRemote = new InsureRemote();

        //将用户车辆数据和模版数据合并
        VehicleRemote vehicleRemote = convertVehicleRemoteByDTO(insureDTO.getVehicleDTO());

        //将改单车船税节点数据合并
        VehicleTaxEo vehicleTaxEo = stagingDataService.getVehicleTaxEoByOrderNumber(insureDTO.getOrderNumber());
        if(vehicleTaxEo == null) {
            VehicleTaxEo initVehicleTaxEo = initVehicleTaxRemote(insureDTO);
            stagingDataService.saveVehicleTaxEo(initVehicleTaxEo);
        }
        vehicleRemote = vehicleTaxEo.coverVehicleRemote(vehicleRemote);
        insureRemote.setVehicleRemote(vehicleRemote);

        //获取纯风险保费
        PureRiskInfoRemote pureRiskInfoRemote = getPureRiskInfoByVehicle(vehicleRemote);
        if(pureRiskInfoRemote == null) {
            throw new PureRiskLossException("纯风险数据丢失");
        }
        insureRemote.setPureRiskInfoRemote(pureRiskInfoRemote);
        insureRemote.setVehicleRemote(vehicleRemote);

        //获取上次核心返回单保单风险信息
        InsuranceRiskInformationEo insuranceRiskInformationEo = stagingDataService
                .getInsuranceRiskInformationEoByOrderNumber(insureDTO.getOrderNumber());
        if(insuranceRiskInformationEo != null) {
            insureRemote.setInsuranceRiskInformationRemote(insuranceRiskInformationEo.convertEoToRemote());
        }

        //获取上次核心返回的浮动项信息
        PremiumFloatingItemsEo premiumFloatingItemsEo = stagingDataService
                .getPremiumFloatingItemsEoByOrderNumber(insureDTO.getOrderNumber());
        if(premiumFloatingItemsEo != null) {
            insureRemote.setPremiumFloatingItemsRemote(premiumFloatingItemsEo.convertEoToRemote());

        }

        return insureRemote;
    }

    private VehicleTaxEo initVehicleTaxRemote(InsureDTO insureDTO) {
        return null;
    }

    /**
     * 根据车辆数据获取纯风险保费
     * @param vehicleRemote 车辆数据
     * @return 纯风险保费
     */
    private PureRiskInfoRemote getPureRiskInfoByVehicle(VehicleRemote vehicleRemote) {

        //按照请求参数md5值获取本地纯风险保费，如存在返回
        PureRiskEo pureRiskEo = stagingDataService.getPureRiskEoByMd5(vehicleRemote.getMd5Value());

        if(pureRiskEo != null) {
            return pureRiskEo.createPureRiskInfoRemoteByEo();
        }

        //本地无该纯风险保费，请求三方根据返回入库，并返回给调用方
        MyRestValueModel<InsureRemote> result = insureRemoteService.postRestResult(QUOTED_PRICE_URL,
                JSON.toJSONString(vehicleRemote), InsureRemote.class);
        if("0000".equals(result.getStatus())) {
            PureRiskEo savePureRiskEo = result.getData()
                    .getPureRiskInfoRemote().createPureRiskEoByPureRiskInfoRemote();
            stagingDataService.savePureRiskEo(savePureRiskEo);
            return result.getData().getPureRiskInfoRemote();
        }

        return null;
    }

    public InsureResultDTO createInsureResultDTOByInsureRemote(InsureRemote insureRemote) {
        InsureResultDTO insureResultDTO = new InsureResultDTO();
        BeanUtils.copyProperties(insureRemote, insureResultDTO);
        return insureResultDTO;

    }
}
