package fulan.tianjian.demo.model.web.eo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.web.vo.PersonVo;

/**
 * Created by tianjian on 2021/6/20.
 */

@Entity
@Table(name = "person")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PersonEo {

    /**
     * 人员id
     */
	@Id
	@GeneratedValue(generator = "jpa-uuid")
    private String id;

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
	
	public PersonVo convertToVo() {
		PersonVo personVo = new PersonVo();
		BeanUtils.copyProperties(this, personVo);
		return personVo;
	}
    
    
}
