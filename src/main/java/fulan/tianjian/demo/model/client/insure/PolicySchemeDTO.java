package fulan.tianjian.demo.model.client.insure;

import java.util.List;

/**
 * 订单保险方案数据
 */
public class PolicySchemeDTO {

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
     * 子险
     */
    private List<PolicySchemeDTO> subInsurancePolicySchemes;


}
