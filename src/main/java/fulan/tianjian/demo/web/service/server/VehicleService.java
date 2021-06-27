package fulan.tianjian.demo.web.service.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.web.eo.VehicleCurd;
import fulan.tianjian.demo.model.web.eo.VehicleEo;
import fulan.tianjian.demo.model.web.vo.VehicleVo;

@Service
public class VehicleService {
	
	@Autowired
	private VehicleCurd vehicleCurd;

	public List<VehicleVo> findVehicleByIdCarNum(String identityCardNumber) {
		List<VehicleEo> vehicleEos = vehicleCurd.findByIdentityCardNumber(identityCardNumber);
		if(!CollectionUtils.isEmpty(vehicleEos)) {
			return vehicleEos.stream().map(e -> {
				return e.convertToVo();
			}).collect(Collectors.toList());
		}
		return new ArrayList<VehicleVo>();
	}

	public void deleteVehicleByOrderNumber(String orderNumber) {
		vehicleCurd.deleteByOrderNumber(orderNumber);
	}

	public VehicleVo findVehicleByOrderNumber(String orderNumber) {
		VehicleVo vehicleVo= new VehicleVo();
		VehicleEo vehicleEo = vehicleCurd.findByOrderNumber(orderNumber);
		if(vehicleEo == null) {
			return vehicleVo;
		}
		BeanUtils.copyProperties(vehicleEo, vehicleVo);
		return vehicleVo;
	}

	public Boolean saveOrUpdateVehicle(VehicleVo vehicleVo) {
		
		VehicleEo vehicleEo = vehicleCurd.findByOrderNumber(vehicleVo.getOrderNumber());
		
		//主表没有数据
		if(vehicleEo == null) {
			vehicleEo = new VehicleEo();
		}
		
		//主表已有数据覆盖
		BeanUtils.copyProperties(vehicleVo, vehicleEo);
		
		return vehicleCurd.save(vehicleEo) != null;
	}
	

}
