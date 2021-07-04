package fulan.tianjian.demo.model.web.vo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.dto.InsurePersonDTO;
import fulan.tianjian.demo.model.web.eo.PersonEo;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PersonVo {

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

    /**
     * 对应的订单编码
     */
    private String orderNumber;
    
    /**
     * 手机号码
     */
    private String mobileNumber;

    
    
    
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



	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public InsurePersonDTO convertToDTO() {
    	InsurePersonDTO insurePersonDTO = new InsurePersonDTO();
    	BeanUtils.copyProperties(this, insurePersonDTO);
    	return insurePersonDTO;
    }
	

	public PersonEo convertToEo() {
		PersonEo personEo = new PersonEo();
    	BeanUtils.copyProperties(this, personEo);
    	return personEo;
    }
	
	public static List<PersonVo> mockPersonVo(String orderNumber) {
		List<PersonVo> personVos = new ArrayList<PersonVo>();
		
		for(int i = 0; i < 3; i++) {
			PersonVo personVo = new PersonVo();
			personVo.setAddress("天堂之门");
			personVo.setIdentityCardNumber("392493778229174529");
			personVo.setName("无神");
			personVo.setOrderNumber(orderNumber);
			personVo.setSex("1");
			personVo.setType(i + "");
			personVos.add(personVo);
		}
		
		return personVos;
	}
}
