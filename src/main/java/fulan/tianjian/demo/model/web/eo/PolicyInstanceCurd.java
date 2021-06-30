package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyInstanceCurd extends JpaRepository<PolicyInstanceEo, String>{

	@Modifying
	void deleteByOrderNumber(String orderNumber);

	List<PolicyInstanceEo> findByOrderNumber(String orderNumber);

}
