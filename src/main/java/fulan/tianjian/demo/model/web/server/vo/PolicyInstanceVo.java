package fulan.tianjian.demo.model.web.server.vo;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.PolicySchemeDTO;

public class PolicyInstanceVo {
	 /**
     * 保额
     */
    private String premium;

    /**
     * 保费
     */
    private String sumInsured;

    /**
     * 保险代码
     */
    private String policyCode;

    /**
     * 保险名称
     */
    private String policyName;

    /**
     * 起始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endData;
    

    /**
     * 保险类型
     */
    private String policyType;

    /**
     * 订单id
     */
    private String orderNumber;
    
    public PolicySchemeDTO convertToDTO() {
    	PolicySchemeDTO policySchemeDTO = new PolicySchemeDTO();
    	BeanUtils.copyProperties(this, policySchemeDTO);
    	return policySchemeDTO;
    }

}