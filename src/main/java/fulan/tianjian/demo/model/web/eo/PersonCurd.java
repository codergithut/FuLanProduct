package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonCurd extends JpaRepository<PersonEo, String>{

	void deleteByOrderNumber(String orderNumber);

	List<PersonEo> findByOrderNumber(String orderNumber);

}
