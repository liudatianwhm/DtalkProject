package com.config;

/**
 * 项目中的常量定义类
 */
public class Constant {
    /**
     * 企业corpid, 需要修改成开发者所在企业
     */
    public static final String CORP_ID = "dingda9e2bbcab46fd0f35c2f4657eb6378f";
    /**
     * 应用的AppKey，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPKEY = "dings70bufkwa0fv5c92";
    /**
     * 应用的AppSecret，登录开发者后台，点击应用管理，进入应用详情可见
     */
    public static final String APPSECRET = "mF8F69YZQFE4-tLTUvYybdKSafwVhrpg9MJyaNq1Fj9UyUcUimJjl7Oe8MsVEGmb";

    /**
     * 数据加密密钥。用于回调数据的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取,您可以随机生成
     */
    public static final String ENCODING_AES_KEY = "Fx8pkieoC74cgekU2sq845BaOpCvbt04dW6LpxkJQHI";

    /**
     * 加解密需要用到的token，企业可以随机填写。如 "12345"
     */
    public static final String TOKEN = "romens";

    /**
     * 应用的agentdId，登录开发者后台可查看
     */
    public static final Long AGENTID = 261636825L;

    /**
     * 审批模板唯一标识，可以在审批管理后台找到
     */
    /**
     * 雨诺首营产品
     */
    public static final String PROCESS_CODE = "PROC-78DFD4F7-2591-4FB9-839B-48302EFD4D17";
    /**
     * 审批测试
      */
//    public static final String PROCESS_CODE = "PROC-28A109FD-FE04-4253-9CA9-201A663E8AD5";


    /**
     * 回调host
     */
    public static final String CALLBACK_URL_HOST = "http://x6t5ye.natappfree.cc";
}
