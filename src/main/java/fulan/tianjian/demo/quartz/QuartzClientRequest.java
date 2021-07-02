package fulan.tianjian.demo.quartz;

public class QuartzClientRequest {
	
	private String jobId;
	
	private String jobInsId;
	
	private String cronName;
	
	private String cronGroup;
	
	private String params;
	
	private String cronMetadataId;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobInsId() {
		return jobInsId;
	}

	public void setJobInsId(String jobInsId) {
		this.jobInsId = jobInsId;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getCronName() {
		return cronName;
	}

	public void setCronName(String cronName) {
		this.cronName = cronName;
	}

	public String getCronGroup() {
		return cronGroup;
	}

	public void setCronGroup(String cronGroup) {
		this.cronGroup = cronGroup;
	}

	public String getCronMetadataId() {
		return cronMetadataId;
	}

	public void setCronMetadataId(String cronMetadataId) {
		this.cronMetadataId = cronMetadataId;
	}
	
	
	
	
	
	


}
