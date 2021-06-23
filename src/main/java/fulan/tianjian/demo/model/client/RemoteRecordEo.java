package fulan.tianjian.demo.model.client;

public class RemoteRecordEo {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 请求url
	 */
	private String url;
	
	/**
	 * 请求参数
	 */
	private String params;
	
	/**
	 * 参数md5参数
	 */
	private String md5Value;
	
	/**
	 * 是否删除
	 */
	private String isDelete;

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

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	

}
