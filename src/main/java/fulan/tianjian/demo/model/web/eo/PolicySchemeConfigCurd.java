package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicySchemeConfigCurd extends JpaRepository<PolicySchemeConfigEo, String>{

	@Modifying
	void deleteByRegionCode(String regionCode);

	List<PolicySchemeConfigEo> findByRegionCode(String regionCode);


}
