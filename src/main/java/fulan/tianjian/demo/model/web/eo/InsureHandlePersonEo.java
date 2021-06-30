package fulan.tianjian.demo.model.web.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.InsureHandlePersonDTO;
import fulan.tianjian.demo.model.web.vo.InsureHandlePersonVo;

@Entity
@Table(name = "insure_handle_person")
public class InsureHandlePersonEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public InsureHandlePersonVo convertToVo() {
		InsureHandlePersonVo insureHandlePersonVo = new InsureHandlePersonVo();
		BeanUtils.copyProperties(this, insureHandlePersonVo);
		return insureHandlePersonVo;
	}
	
	public InsureHandlePersonDTO convertToDTO() {
		InsureHandlePersonDTO insureHandlePersonDTO = new InsureHandlePersonDTO();
		BeanUtils.copyProperties(this, insureHandlePersonDTO);
		return insureHandlePersonDTO;
	}
    

}
