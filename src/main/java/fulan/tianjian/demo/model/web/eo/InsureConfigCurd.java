package fulan.tianjian.demo.model.web.eo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsureConfigCurd extends JpaRepository<InsureConfigEo, String>{

	void deleteByProvinceCode(String key);

	InsureConfigEo findByProvinceCode(String key);

}
