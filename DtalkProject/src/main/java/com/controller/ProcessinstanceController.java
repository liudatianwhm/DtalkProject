package com.controller;

import com.alibaba.fastjson.JSON;

import com.config.Constant;
import com.config.URLConstant;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiCallBackDeleteCallBackRequest;
import com.dingtalk.api.request.OapiCallBackRegisterCallBackRequest;
import com.dingtalk.api.request.OapiProcessinstanceCreateRequest;
import com.dingtalk.api.request.OapiProcessinstanceGetRequest;
import com.dingtalk.api.response.OapiCallBackRegisterCallBackResponse;
import com.dingtalk.api.response.OapiProcessinstanceCreateResponse;
import com.dingtalk.api.response.OapiProcessinstanceGetResponse;
import com.github.pagehelper.PageInfo;
import com.model.ProcessInstanceInputVO;
import com.models.Student;
import com.service.StudentSerive;
import com.util.AccessTokenUtil;
import com.util.LogFormatter;
import com.util.LogFormatter.LogEvent;
import com.util.ServiceResult;
import com.util.ServiceResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

/**
 * 企业 E应用审批解决方案示例代码
 * 实现了审批的基础功能
 */
@RestController
public class ProcessinstanceController {
	private static final Logger bizLogger = LoggerFactory.getLogger(ProcessinstanceController.class);

	@Autowired
	private StudentSerive studentSerive;

	/**
	 * 欢迎页面
	 */
	@RequestMapping(value = "/Processinstance/welcome", method = RequestMethod.GET)
	public PageInfo<Student> welcome(@RequestParam Map<String,Object> map) {

		return studentSerive.getPageInfo(map);
	}


	/**
	 * 发起审批
	 */
	@RequestMapping(value = "/processinstance/start", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult<String> startProcessInstance(@RequestBody ProcessInstanceInputVO processInstance) {
		try {
			DefaultDingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_START);
			OapiProcessinstanceCreateRequest request = new OapiProcessinstanceCreateRequest();
			request.setProcessCode(Constant.PROCESS_CODE);

			request.setFormComponentValues(processInstance.generateForms());

			/**
			 * 如果想复用审批固定流程，使用或签会签的话，可以不传审批人，具体请参考文档： https://open-doc.dingtalk.com/microapp/serverapi2/ebkwx8
			 * 本次quickstart，演示不传审批人的场景
			 */
//			request.setApprovers(processInstance.getOriginatorUserId());
			request.setOriginatorUserId(processInstance.getOriginatorUserId());
			request.setDeptId(processInstance.getDeptId());
//			request.setCcList(processInstance.getOriginatorUserId());
//			request.setCcPosition("FINISH");

			OapiProcessinstanceCreateResponse response = client.execute(request, AccessTokenUtil.getToken());

			if (response.getErrcode().longValue() != 0) {
				return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
			}
			// 先删除企业已有的回调
			DingTalkClient client1 = new DefaultDingTalkClient(URLConstant.DELETE_CALLBACK);
			OapiCallBackDeleteCallBackRequest oapiCallBackDeleteCallBackRequest = new OapiCallBackDeleteCallBackRequest();
			oapiCallBackDeleteCallBackRequest.setHttpMethod("GET");
			client1.execute(oapiCallBackDeleteCallBackRequest, AccessTokenUtil.getToken());

			// 重新为企业注册回调
			client1 = new DefaultDingTalkClient(URLConstant.REGISTER_CALLBACK);
			OapiCallBackRegisterCallBackRequest registerRequest = new OapiCallBackRegisterCallBackRequest();
			registerRequest.setUrl(Constant.CALLBACK_URL_HOST + "/callback");
			registerRequest.setAesKey(Constant.ENCODING_AES_KEY);
			registerRequest.setToken(Constant.TOKEN);
			registerRequest.setCallBackTag(Arrays.asList("bpms_instance_change", "bpms_task_change"));
			OapiCallBackRegisterCallBackResponse registerResponse = client1.execute(registerRequest,AccessTokenUtil.getToken());
			if (registerResponse.isSuccess()) {
				System.out.println("回调注册成功了！！！");
			}
			return ServiceResult.success(response.getProcessInstanceId());

		} catch (Exception e) {
			String errLog = LogFormatter.getKVLogData(LogEvent.END,
				LogFormatter.KeyValue.getNew("processInstance", JSON.toJSONString(processInstance)));
			bizLogger.info(errLog,e);
			return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
		}
	}

	/**
	 * 根据审批实例id获取审批详情
	 * @param instanceId
	 * @return
	 */
	@RequestMapping(value = "/pricessinstance/get", method = RequestMethod.POST)
	@ResponseBody
	public ServiceResult getProcessinstanceById(@RequestParam String instanceId) {
		try {
			DingTalkClient client = new DefaultDingTalkClient(URLConstant.URL_PROCESSINSTANCE_GET);
			OapiProcessinstanceGetRequest request = new OapiProcessinstanceGetRequest();
			request.setProcessInstanceId(instanceId);
			OapiProcessinstanceGetResponse response = client.execute(request, AccessTokenUtil.getToken());
			if (response.getErrcode().longValue() != 0) {
				return ServiceResult.failure(String.valueOf(response.getErrorCode()), response.getErrmsg());
			}
			return ServiceResult.success(response.getProcessInstance());
		} catch (Exception e) {
			String errLog = LogFormatter.getKVLogData(LogEvent.END,
				LogFormatter.KeyValue.getNew("instanceId", instanceId));
			bizLogger.info(errLog,e);
			return ServiceResult.failure(ServiceResultCode.SYS_ERROR.getErrCode(),ServiceResultCode.SYS_ERROR.getErrMsg());
		}
	}
}


