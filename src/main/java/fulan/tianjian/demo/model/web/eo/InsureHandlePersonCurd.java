package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

public interface InsureHandlePersonCurd extends JpaRepository<InsureHandlePersonEo, String>{

	@Modifying
	void deleteByRegionCode(String key);

	List<InsureHandlePersonEo> findByRegionCode(String key);

}
