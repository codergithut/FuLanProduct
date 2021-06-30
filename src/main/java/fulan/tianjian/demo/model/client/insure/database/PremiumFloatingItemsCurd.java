package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PremiumFloatingItemsCurd extends JpaRepository<PremiumFloatingItemsEo, String>{

	PremiumFloatingItemsEo findByOrderNumber(String orderNumber);

	void deleteByOrderNumber(String orderNumber);

}
