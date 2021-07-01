package fulan.tianjian.demo.quartz;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cron_metadata")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class CronMetadataEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String cronMetadataId;
	
	private String cronName;
	
	private String cronGroup;
	
	private String cron;
	
	private String url;
	
	private String params;
	
	private Date startTime;
	
	private Date endTime;

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCronName() {
		return cronName;
	}

	public void setCronName(String cronName) {
		this.cronName = cronName;
	}

	public String getCronMetadataId() {
		return cronMetadataId;
	}

	public void setCronMetadataId(String cronMetadataId) {
		this.cronMetadataId = cronMetadataId;
	}
	
	public String getCronGroup() {
		return cronGroup;
	}

	public void setCronGroup(String cronGroup) {
		this.cronGroup = cronGroup;
	}
	

}
