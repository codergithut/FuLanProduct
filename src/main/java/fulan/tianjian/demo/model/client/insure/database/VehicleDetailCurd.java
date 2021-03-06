package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDetailCurd extends JpaRepository<VehicleDetailEo, String>{

	/**
	 * 获取车辆数据，缓存
	 * @param vehicleCode 车辆编码
	 * @param vehicleSource 来源
	 * @return
	 */
	VehicleDetailEo findByMd5ValueAndVehicleSource(String md5ValuePart, String vehicleSource);

	VehicleDetailEo findByVehicleCodeAndVehicleSource(String vehicleCode, String thirdVehicleDetail);

	VehicleDetailEo findByMd5ValueAndDataSource(String md5ValuePart, String vehicleSource);

	VehicleDetailEo findByVehicleCodeAndDataSource(String vehicleCode, String thirdVehicleDetail);


}
