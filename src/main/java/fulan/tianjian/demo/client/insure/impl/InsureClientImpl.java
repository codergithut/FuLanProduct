package fulan.tianjian.demo.client.insure.impl;

import com.alibaba.fastjson.JSON;
import fulan.tianjian.demo.client.insure.InsureModelService;
import fulan.tianjian.demo.client.insure.InsureClient;
import fulan.tianjian.demo.client.insure.InsureRemoteService;
import fulan.tianjian.demo.client.vehicle.ThirdPartyModelWarehouseClient;
import fulan.tianjian.demo.model.client.convert.VehicleConvertUtil;
import fulan.tianjian.demo.model.client.insure.AllVehicleDTO;
import fulan.tianjian.demo.model.client.insure.InsureDTO;
import fulan.tianjian.demo.model.client.insure.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.VehicleTemplateDTO;
import fulan.tianjian.demo.model.client.remote.InsureRemote;
import fulan.tianjian.demo.model.client.remote.PureRiskInfoRemote;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;
import org.springframework.beans.factory.annotation.Autowired;

import static fulan.tianjian.demo.constant.ConstantCls.QUOTED_PRICE_URL;

public class InsureClientImpl implements InsureClient {

    @Autowired
    private ThirdPartyModelWarehouseClient thirdPartyModelWarehouseClient;

    @Autowired
    private InsureRemoteService insureRemoteService;

    @Autowired
    private InsureModelService insureModelService;


    @Override
    public InsureResultDTO quotedPrice(InsureDTO insureDTO) {

        InsureRemote insureRemote = insureModelService.createInsureRemoteByInsureDTO(insureDTO, "quotePrice");
        MyRestValueModel<InsureRemote> result = insureRemoteService.postRestResult(QUOTED_PRICE_URL,
                JSON.toJSONString(insureRemote), InsureRemote.class);

        return null;
    }

    @Override
    public InsureResultDTO underwrite(InsureDTO insureDTO) {
        InsureRemote insureRemote = insureModelService.createInsureRemoteByInsureDTO(insureDTO, "underwrite");
        MyRestValueModel<InsureRemote> result = insureRemoteService.postRestResult(QUOTED_PRICE_URL,
                JSON.toJSONString(insureRemote), InsureRemote.class);

        return null;
    }

    @Override
    public InsureResultDTO getRenewPolicy(String platNo, String vin, String engineNo) {
        return null;
    }

    @Override
    public VehicleTemplateDTO getVehicleTemplateDTO(String vehicleCode) {
        ThirdPartyVehicle thirdPartyVehicle = thirdPartyModelWarehouseClient.getVehicleTemplateDTO(vehicleCode);
        return VehicleConvertUtil.convertThirdPartyVehicle(thirdPartyVehicle);
    }

    @Override
    public AllVehicleDTO getTrafficMangement(String plateNo, String vin, String engineNo) {
        return null;
    }


}
