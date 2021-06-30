package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCurd extends JpaRepository<PersonEo, String>{

	@Modifying
	void deleteByOrderNumber(String orderNumber);

	List<PersonEo> findByOrderNumber(String orderNumber);

}
