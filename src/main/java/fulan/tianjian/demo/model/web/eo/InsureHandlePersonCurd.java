package fulan.tianjian.demo.model.web.eo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InsureHandlePersonCurd extends JpaRepository<InsureHandlePersonEo, String>{

	void deleteByRegionCode(String key);

	List<InsureHandlePersonEo> findByRegionCode(String key);

}
