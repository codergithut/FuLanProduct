package fulan.tianjian.demo.model.client.insure.dto;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.InsurePersonRemote;

/**
 * 投保人 被保人 受益人 信息
 */
public class InsurePersonDTO {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 身份证号码
     */
    private String identityCardNumber;

    /**
     *性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 人员类型 0 投保人 1 受益人 2 车主
     */
    private String type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityCardNumber() {
		return identityCardNumber;
	}

	public void setIdentityCardNumber(String identityCardNumber) {
		this.identityCardNumber = identityCardNumber;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public InsurePersonRemote convertToRemote() {
		InsurePersonRemote insurePersonRemote = new InsurePersonRemote();
		BeanUtils.copyProperties(this, insurePersonRemote);
		return insurePersonRemote;
	}
    
    


}
