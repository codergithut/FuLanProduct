package fulan.tianjian.demo.model.client.insure.remote;

/**
 * 保单经办人等基础信息
 */
public class InsureHandlePersonRemote {

    /**
     * 操作人姓名
     */
    private String name;

    /**
     * 操作人编码
     */
    private String code;

    /**
     * 操作人类型 0 操作人 1 经办人
     */
    private String type;

    /**
     * 部门编码
     */
    private String departmentCode;

    /**
     * 地区编码
     */
    private String regionCode;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getRegionCode() {
		return regionCode;
	}

	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
    
}
