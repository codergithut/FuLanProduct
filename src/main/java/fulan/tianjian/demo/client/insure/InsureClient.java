package fulan.tianjian.demo.client.insure;

import com.alibaba.fastjson.JSON;
import fulan.tianjian.demo.client.vehicle.ThirdPartyModelWarehouseClient;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.convert.VehicleConvertUtil;
import fulan.tianjian.demo.model.client.insure.AllVehicleDTO;
import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.insure.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.VehicleTemplateDTO;
import fulan.tianjian.demo.model.client.remote.InsureRemote;
import fulan.tianjian.demo.model.client.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;
import org.springframework.beans.factory.annotation.Autowired;

import static fulan.tianjian.demo.constant.ConstantCls.QUOTED_PRICE_URL;
import static fulan.tianjian.demo.constant.ConstantCls.RENEW_POLICY_URL;

public class InsureClient {

    @Autowired
    private ThirdPartyModelWarehouseClient thirdPartyModelWarehouseClient;

    @Autowired
    private InsureRemoteService insureRemoteService;

    @Autowired
    private InsureModelService insureModelService;

    @Autowired
    private StagingDataService stagingDataService;


    /**
     * 报价接口
     * @param insureDTO
     * @return
     */
    InsureResultDTO quotedPrice(InsureDTO insureDTO) throws PureRiskLossException {
        InsureRemote insureRemote = insureModelService.createInsureRemoteByInsureDTO(insureDTO, "quotePrice");
        MyRestValueModel<InsureRemote> result = insureRemoteService.postRestResult(QUOTED_PRICE_URL,
                JSON.toJSONString(insureRemote), InsureRemote.class);

        InsureRemote backData = null;
        if(result.getData() != null) {
            backData = result.getData();
        }

        if("0000".equals(result.getStatus())) {
            return insureModelService.createInsureResultDTOByInsureRemote(backData);
        }

        //核保或报价失败，按照返回数据封装数据给前端使用
        if(backData != null) {

        }

        return null;
    }


    /**
     * 获取续保数据
     * @param platNo
     * @param vin
     * @param engineNo
     * @return
     */
    InsureResultDTO getRenewPolicy(String platNo, String vin, String engineNo) {
        InsureRemote insureRemote = new InsureRemote();
        VehicleRemote vehicleRemote = new VehicleRemote();
        vehicleRemote.setPlateNo(platNo);
        vehicleRemote.setVinCode(vin);
        vehicleRemote.setEngineNo(engineNo);
        insureRemote.setVehicleRemote(vehicleRemote);
        MyRestValueModel<InsureRemote> result = insureRemoteService.postRestResult(RENEW_POLICY_URL,
                JSON.toJSONString(insureRemote), InsureRemote.class);
        if("0000".equals(result.getStatus())) {
            insureModelService.createInsureResultDTOByInsureRemote(result.getData());
        }
        return null;
    }

    /**
     *
     * @param vehicleCode 车型编码
     * @return 车辆模型数据
     */
    VehicleTemplateDTO getVehicleTemplateDTO(String vehicleCode){
        ThirdPartyVehicle thirdPartyVehicle = thirdPartyModelWarehouseClient.getVehicleTemplateDTO(vehicleCode);
        return VehicleConvertUtil.convertThirdPartyVehicle(thirdPartyVehicle);
    }

    /**
     * 获取交管车辆数据
     * @param plateNo
     * @param vin
     * @param engineNo
     * @return
     */
    AllVehicleDTO getTrafficMangement(String plateNo, String vin, String engineNo){
        return null;
    }


}
