package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleCurd extends JpaRepository<VehicleEo, String>{

	VehicleEo findByOrderNumber(String orderNumber);

	void deleteByOrderNumber(String orderNumber);

	List<VehicleEo> findByIdentityCardNumber(String identityCardNumber);

}
