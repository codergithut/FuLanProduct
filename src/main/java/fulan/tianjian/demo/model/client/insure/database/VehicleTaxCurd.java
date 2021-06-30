package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleTaxCurd extends JpaRepository<VehicleTaxEo, String>{

	VehicleTaxEo findByOrderNumber(String orderNumber);

	void deleteByOrderNumber(String orderNumber);

}
