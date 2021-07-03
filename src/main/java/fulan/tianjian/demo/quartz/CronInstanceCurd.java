package fulan.tianjian.demo.quartz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CronInstanceCurd extends JpaRepository<CronInstanceEo, String>{

	CronInstanceEo findByCronMetadataIdAndJobInsCode(String cronMetadataId, String jobInsId);

}
