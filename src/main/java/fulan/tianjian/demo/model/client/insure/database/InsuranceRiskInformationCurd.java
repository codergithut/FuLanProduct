package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRiskInformationCurd extends JpaRepository<InsuranceRiskInformationEo, String>{

	InsuranceRiskInformationEo findByOrderNumber(String orderNumber);

	void deleteByOrderNumber(String orderNumber);

}
