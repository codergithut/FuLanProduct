package fulan.tianjian.demo.client.insure;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.client.HttpClient;
import fulan.tianjian.demo.client.vehicle.ThirdPartyModelWarehouseClient;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.convert.VehicleConvertUtil;
import fulan.tianjian.demo.model.client.insure.database.InsuranceRiskInformationEo;
import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsEo;
import fulan.tianjian.demo.model.client.insure.database.VehicleDetailEo;
import fulan.tianjian.demo.model.client.insure.dto.InsureDTO;
import fulan.tianjian.demo.model.client.insure.dto.InsureResultDTO;
import fulan.tianjian.demo.model.client.insure.dto.VehicleDTO;
import fulan.tianjian.demo.model.client.insure.notice.NoticeMessage;
import fulan.tianjian.demo.model.client.insure.remote.InsurePersonRemote;
import fulan.tianjian.demo.model.client.insure.remote.InsureRemote;
import fulan.tianjian.demo.model.client.insure.remote.VehicleRemote;
import fulan.tianjian.demo.model.client.order.OrderCenterVo;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.vehicle.ThirdPartyVehicle;
import fulan.tianjian.demo.rabbitmq.MessageProducer;
import fulan.tianjian.demo.rabbitmq.QueueConstants;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static fulan.tianjian.demo.constant.ConstantCls.*;

/**
 * 核心请求类
 */
@Service
public class InsureClient {
	
	@Autowired
	private MessageProducer messageProducer;

	@Autowired
	private ThirdPartyModelWarehouseClient thirdPartyModelWarehouseClient;

	@Autowired
	private HttpClient httpClient;

	@Autowired
	private InsureModelService insureModelService;

	@Autowired
	private StagingDataService stagingDataService;

	/**
	 * 报价接口
	 * 
	 * @param insureDTO
	 * @return
	 */
	public InsureResultDTO quotedPrice(InsureDTO insureDTO) throws PureRiskLossException {
		InsureRemote insureRemote = insureModelService.createInsureRemoteByInsureDTO(insureDTO, QUOTE);
		return getRemoteValue(insureRemote, insureDTO.getOrderNumber(), QUOTE);
	}

	/**
	 * 获取续保数据
	 * 
	 * @param platNo
	 * @param vin
	 * @param engineNo
	 * @return
	 */
	public InsureResultDTO getRenewPolicy(String platNo, String vin, String engineNo) {
		InsureRemote insureRemote = new InsureRemote();
		VehicleRemote vehicleRemote = new VehicleRemote();
		vehicleRemote.setPlateNo(platNo);
		vehicleRemote.setVinCode(vin);
		vehicleRemote.setEngineNo(engineNo);
		insureRemote.setVehicleRemote(vehicleRemote);
		MyRestValueModel<InsureRemote> result = httpClient.postRestResult(RENEW_POLICY_URL,
				JSON.toJSONString(insureRemote), InsureRemote.class);
		if ("0000".equals(result.getStatus())) {
			InsureResultDTO inSureResutDTO = insureModelService.createInsureResultDTOByInsureRemote(result.getData());
			InsureRemote v = result.getData();
			saveVehicleDetail(v.getVehicleRemote(), RENEW_VEHICLE_DETAIL);
			return inSureResutDTO;
		}
		return null;
	}

	/**
	 *
	 * @param vehicleCode 车型编码
	 * @return 车辆模型数据
	 */
	public VehicleDetailEo saveVehicleTemplate(String vehicleCode) {
		VehicleDetailEo vehicleDetailEo = stagingDataService.getVehicleDetailByVehicleCode(vehicleCode);
		if (vehicleDetailEo == null) {
			ThirdPartyVehicle thirdPartyVehicle = thirdPartyModelWarehouseClient.getVehicleTemplateDTO(vehicleCode);
			vehicleDetailEo = VehicleConvertUtil.convertThirdPartyVehicle(thirdPartyVehicle);
			stagingDataService.saveVehicleDetail(vehicleDetailEo);
		}
		return vehicleDetailEo;
	}

	/**
	 * 获取交管车辆数据
	 * 
	 * @param plateNo
	 * @param vin
	 * @param engineNo
	 * @return
	 */
	public VehicleRemote saveVehicleTrafficMangement(String plateNo, String vin, String engineNo) {
		InsureRemote insureRemote = new InsureRemote();
		VehicleRemote vehicleRemote = new VehicleRemote();
		vehicleRemote.setPlateNo(plateNo);
		vehicleRemote.setVinCode(vin);
		vehicleRemote.setEngineNo(engineNo);
		insureRemote.setVehicleRemote(vehicleRemote);
		MyRestValueModel<InsureRemote> result = httpClient.postRestResult(TRAFFIC_VEHICLE_URL,
				JSON.toJSONString(insureRemote), InsureRemote.class);
		if ("0000".equals(result.getStatus())) {
			VehicleRemote v = result.getData().getVehicleRemote();
			insureModelService.createInsureResultDTOByInsureRemote(result.getData());
			saveVehicleDetail(v, TRAFFIC_MANGEMENT_VEHICLE_DETAIL);
			return v;

		}
		return null;
	}

	/**
	 * 根据车辆用户录入模型获取车辆详细数据
	 * 
	 * @param vehicleDTO 车辆基础数据模型
	 * @return 车辆详细数据，包含续保查询，三方车型数据，和交管数据覆盖后的数据
	 */
	public VehicleDTO getVehicle(VehicleDTO vehicleDTO) {
		List<VehicleDetailEo> vehicleDetailEos = stagingDataService.findVehicleDetailByVehicleDTO(vehicleDTO);
		// 按照续保查询数据，三方车型库，交管数据顺序覆盖车辆数据
		List<VehicleDetailEo> vehicleDatas = vehicleDetailEos.stream()
				.sorted(Comparator.comparing(VehicleDetailEo::getDataSource)).collect(Collectors.toList());
		for (VehicleDetailEo vehicleDetailEo : vehicleDatas) {
			BeanUtils.copyProperties(vehicleDetailEo, vehicleDTO, getNullPropertyNames(vehicleDetailEo));
		}

		return vehicleDTO;

	}

	/**
	 * 保存车辆数据
	 * 
	 * @param vehicleRemote 返回的车辆数据
	 * @param dataSource    数据类型
	 * @return 是否成功
	 */
	private boolean saveVehicleDetail(VehicleRemote vehicleRemote, String dataSource) {
		VehicleDetailEo vehicleDetailEo = stagingDataService
				.getVehicleDetailByMd5ValueAndSource(vehicleRemote.getMd5Value(), dataSource);
		if (vehicleDetailEo == null) {
			VehicleDetailEo saveData = VehicleConvertUtil.convertRemoteToEo(vehicleRemote, dataSource);
			stagingDataService.saveVehicleDetail(saveData);
		}
		return true;
	}

	public InsureResultDTO underwritingByOrderCenter(OrderCenterVo orderCenterVo) {
		InsureRemote insureRemote = insureModelService.createInsureRemoteByOrderCenterVo(orderCenterVo, UNDERWRITING);
		return getRemoteValue(insureRemote, orderCenterVo.getCode(), UNDERWRITING);
	}

	private InsureResultDTO getRemoteValue(InsureRemote insureRemote, String orderNumber, String type) {
		
		InsureResultDTO insureReult = null;
		
		MyRestValueModel<InsureRemote> result = httpClient.postRestResult(QUOTED_PRICE_URL,
				JSON.toJSONString(insureRemote), InsureRemote.class);
		
		NoticeMessage noticeMessage = createNotcieMessage(insureRemote);
		noticeMessage.setOrderCenterCode(orderNumber);
		noticeMessage.setOrderNumber(orderNumber);
		noticeMessage.setStatus(type);
	

		InsureRemote backData = null;
		if (result.getData() != null) {
			backData = result.getData();
			noticeMessage.setRemoteMessage(backData.getMessage());
			insureReult = insureModelService.createInsureResultDTOByInsureRemote(backData);
		}
		
		

		if ("0000".equals(result.getStatus())) {

			PremiumFloatingItemsEo premiumFloatingItemsEo = backData.getPremiumFloatingItemsRemote()
					.convertToEo(orderNumber);

			if (premiumFloatingItemsEo != null) {
				stagingDataService.savePremiumFloatingItemsEo(premiumFloatingItemsEo);
			}

			InsuranceRiskInformationEo insuranceRiskInformationEo = backData.getInsuranceRiskInformationRemote()
					.convertToEo(orderNumber);
			if (insuranceRiskInformationEo != null) {
				stagingDataService.saveInsureRiskInformationEo(insuranceRiskInformationEo);
			}
			noticeMessage.setIsSuccess("Y");
			
		} else {
			// 核保或报价失败，按照返回数据封装数据给前端使用
			if (backData != null) {
				noticeMessage.setIsSuccess("N");
			}
		}
		
		messageProducer.sendMessage(QueueConstants.MESSAGE_EXCHANGE_RECORD,
				QueueConstants.MESSAGE_ROUTE_KEY_REMOTE, noticeMessage);
//		sendNoticeService.publish(noticeMessage);
		return insureReult;
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null || StringUtils.isBlank(srcValue.toString())) {
				emptyNames.add(pd.getName());
			}
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}
	
	private NoticeMessage createNotcieMessage(InsureRemote insureRemote) {
		NoticeMessage noticeMessage = new NoticeMessage();
	    
		//设置车辆基础数据
		noticeMessage.setEngineNo(insureRemote.getVehicleRemote().getEngineNo());
		noticeMessage.setPlateNo(insureRemote.getVehicleRemote().getPlateNo());
		noticeMessage.setVinCode(insureRemote.getVehicleRemote().getVinCode());
		
		//地区信息
		noticeMessage.setRegionCode(insureRemote.getRegionCode());
		
		//设置投保人数据
		InsurePersonRemote insurePerson = insureRemote.getInsurePersonRemotes().stream().filter(e -> "0".equals(e.getType())).findAny().get();
		noticeMessage.setInsurantName(insurePerson.getName());
		noticeMessage.setInsurantIdentityCardNumber(insurePerson.getIdentityCardNumber());
		noticeMessage.setInsurantMobileNumber(insurePerson.getMobileNumber());
		
		return noticeMessage;
		
	}

}
