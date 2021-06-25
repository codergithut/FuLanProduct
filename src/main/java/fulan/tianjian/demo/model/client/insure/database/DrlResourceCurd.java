package fulan.tianjian.demo.model.client.insure.database;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrlResourceCurd extends JpaRepository<DrlResourceEo, String>{

	DrlResourceEo findResourceByDrlName(String drlName);

	@Transactional
	void deleteByDrlName(String drlName);
	
	

}
