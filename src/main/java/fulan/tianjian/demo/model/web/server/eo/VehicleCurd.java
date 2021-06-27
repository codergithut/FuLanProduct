package fulan.tianjian.demo.model.web.server.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleCurd extends JpaRepository<VehicleEo, String>{

	VehicleEo findByOrderNumber(String orderNumber);

	void deleteByOrderNumber(String orderNumber);

	List<VehicleEo> findByIdentityCardNumber(String identityCardNumber);

}
