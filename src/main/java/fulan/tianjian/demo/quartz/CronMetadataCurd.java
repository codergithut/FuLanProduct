package fulan.tianjian.demo.quartz;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CronMetadataCurd extends JpaRepository<CronMetadataEo, String>{

	void deleteByCronNameAndCronGroup(String cronName, String cronGroup);

}
