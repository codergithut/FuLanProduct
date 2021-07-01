package fulan.tianjian.demo.quartz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cron_instance")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CronInstanceEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	private String cronMetadataId;
	
	private Boolean isSuccess;
	
	private String stage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCronMetadataId() {
		return cronMetadataId;
	}

	public void setCronMetadataId(String cronMetadataId) {
		this.cronMetadataId = cronMetadataId;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
	
	
	

}
