package fulan.tianjian.demo.model.client;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name = "remote_record")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class RemoteRecordEo {
	
	/**
	 * id
	 */
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	/**
	 * 请求url
	 */
	private String url;
	
	/**
	 * 请求参数
	 */
	@Column(nullable = false,columnDefinition="varchar(2048) COMMENT '用户id'")
	private String params;
	
	/**
	 * 参数md5参数
	 */
	private String md5Value;
	
	/**
	 * 是否成功
	 */
	private String isSuccess;
	
	/**
	 * 重试次数
	 */
	private Integer retryCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
	}
	
	

	
	
	

}
