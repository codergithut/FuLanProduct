package fulan.tianjian.demo.model.client.remote;

import java.util.List;

/**
 * 保单的基础配置信息
 */
public class InsureConfigRemote {
    /**
     * 报价模式
     */
    private String quotationMode;

    /**
     * 是否电子投保单
     */
    private String isElectronicApplicationForm;


    /**
     * 是否需要人脸识别
     */
    private String isFaceRecognition;


    /**
     * 销售渠道
     */
    private String distributionChannel;


    /**
     * 代理点名称
     */
    private String agentPointName;


    /**
     * 代理点编码
     */
    private String agentPointCode;

    /**
     * 保险操作人信息
     */
    private List<InsureHandlePersonRemote> insureHandlePersonRemotes;


}
