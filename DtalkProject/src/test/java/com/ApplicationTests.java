package com;

import com.config.Constant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.taobao.api.ApiException;
import com.util.AccessTokenUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Test
	public void contextLoads() throws ApiException {
		System.out.println("hello world");
		DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/processinstance/create");
		OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
		request.setAgentId(Constant.AGENTID);
		request.setProcessCode(Constant.PROCESS_CODE);
		List<OapiProcessinstanceCreateRequest.FormComponentValueVo> formComponentValues = new ArrayList<OapiProcessinstanceCreateRequest.FormComponentValueVo>();

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo1 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo1.setName("商品编号");
		vo1.setValue("001");
		formComponentValues.add(vo1);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo2 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo2.setName("商品名称");
		vo2.setValue("阿莫西林");
		formComponentValues.add(vo2);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo3 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo3.setName("规格");
		vo3.setValue("10");
		formComponentValues.add(vo3);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo4 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo4.setName("生产单位");
		vo4.setValue("雨诺");
		formComponentValues.add(vo4);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo5 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo5.setName("批准文号");
		vo5.setValue("pass123");
		formComponentValues.add(vo5);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo6 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo6.setName("条码");
		vo6.setValue("123");
		formComponentValues.add(vo6);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo7 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo7.setName("计量单位");
		vo7.setValue("克");
		formComponentValues.add(vo7);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo8 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo8.setName("参考进价（元）");
		vo8.setValue("90");
		formComponentValues.add(vo8);

		OapiProcessinstanceCreateRequest.FormComponentValueVo vo9 = new OapiProcessinstanceCreateRequest.FormComponentValueVo();
		vo9.setName("零售价");
		vo9.setValue("10");
		formComponentValues.add(vo9);
		request.setFormComponentValues(formComponentValues);
		request.setApprovers("2639522755675565");
		request.setOriginatorUserId("2639522755675565");
		request.setDeptId(94138444L);
//		request.setCcList("userid1,userid2");
//		request.setCcPosition("START_FINISH");
		OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());
		System.out.println(AccessTokenUtil.getToken());
		System.out.println(response.getProcessInstanceId());
		System.out.println(response);

	}

}
