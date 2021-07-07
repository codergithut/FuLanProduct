package fulan.tianjian.demo.quartz;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CronMetadataCurd extends JpaRepository<CronMetadataEo, String>{

	/**
	 * 根据任务名称和组名删除任务
	 * @param cronName 任务名称
	 * @param cronGroup 组名名称
	 */
	void deleteByCronNameAndCronGroup(String cronName, String cronGroup);

	List<CronMetadataEo> findByCronNameAndCronGroup(String cronName, String cronGroup);

}
