package fulan.tianjian.demo.model.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.InsureHandlePersonDTO;
import fulan.tianjian.demo.model.web.eo.InsureHandlePersonEo;

public class InsureHandlePersonVo {
	
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
	
	public InsureHandlePersonEo convertToEo() {
		InsureHandlePersonEo insureHandlePersonEo = new InsureHandlePersonEo();
		BeanUtils.copyProperties(this, insureHandlePersonEo);
		return insureHandlePersonEo;
	}
    
	
	public InsureHandlePersonDTO convertToDTO() {
		InsureHandlePersonDTO insureHandlePersonDTO = new InsureHandlePersonDTO();
		BeanUtils.copyProperties(this, insureHandlePersonDTO);
		return insureHandlePersonDTO;
	}
	
	public static List<InsureHandlePersonVo> mockData() {
		List<InsureHandlePersonVo> insureHandlePersonVos = new ArrayList<InsureHandlePersonVo>();
		for(int i = 0; i < 3; i++) {
			InsureHandlePersonVo insureHandlePerson = new InsureHandlePersonVo();
			insureHandlePerson.setCode("code" + i);
			insureHandlePerson.setDepartmentCode("departMentCode" + i);
			insureHandlePerson.setRegionCode("215000");
			insureHandlePerson.setType(i + "");
			insureHandlePerson.setName("name" + i);
			insureHandlePersonVos.add(insureHandlePerson);
		}
		return insureHandlePersonVos;
	}
    

}
