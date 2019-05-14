//package com;
//
//import com.dingtalk.api.DefaultDingTalkClient;
//import com.dingtalk.api.DingTalkClient;
//import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
//import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
//import com.taobao.api.ApiException;
//import com.util.AccessTokenUtil;
//import com.util.TimeTaskUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ApplicayionTest1 {
//
//    @Autowired
//    private TimeTaskUtil timeTask;
//    @Test
//
//
//
//
//
//
//
//    public void contextLoads() throws ApiException {
//        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/get");
//        OapiProcessinstanceGetRequest instance = new OapiProcessinstanceGetRequest();
//        instance.setProcessInstanceId("efa9650c-b5fc-4e90-9ec9-f218288e0df0");
//        OapiProcessinstanceGetResponse response = client.execute(instance, AccessTokenUtil.getToken());
//        timeTask.timeTask1();
//    }
//}
