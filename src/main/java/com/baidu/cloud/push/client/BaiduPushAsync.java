package com.baidu.cloud.push.client;

import java.util.concurrent.Future;

import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.PushMsgToSingleDeviceRequest;
import com.baidu.cloud.push.model.PushMsgToSingleDeviceResponse;

public interface BaiduPushAsync {

	Future<PushMsgToSingleDeviceResponse> pushMsgToSingleDeviceAsync(
			PushMsgToSingleDeviceRequest request)
			throws PushClientException, PushServerException;

}
