package fulan.tianjian.demo.model.web.server.vo;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.InsurePersonDTO;

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
    
    public InsurePersonDTO convertToDTO() {
    	InsurePersonDTO insurePersonDTO = new InsurePersonDTO();
    	BeanUtils.copyProperties(this, insurePersonDTO);
    	return insurePersonDTO;
    }
}
