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
	
	/**
	 * 元数据id
	 */
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String cronMetadataId;
	
	/**
	 * 任务名称
	 */
	private String cronName;
	
	/**
	 * 任务组
	 */
	private String cronGroup;
	
	/**
	 * cron表达式
	 */
	private String cron;
	
	/**
	 * 请求url
	 */
	private String url;
	
	/**
	 * 任务参数
	 */
	private String params;
	
	/**
	 * 开始时间
	 */
	private Date startTime;
	
	
	/**
	 * 结束时间
	 */
	private Date endTime;
	
	
	/**
	 * 1 运行中， 2 暂停中 
	 */
	private String status;

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
