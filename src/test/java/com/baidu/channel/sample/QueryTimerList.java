package com.baidu.channel.sample;

import java.util.List;

import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.client.BaiduPushClient;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.QueryTimerListRequest;
import com.baidu.cloud.push.model.QueryTimerListResponse;
import com.baidu.cloud.push.model.TimerResultInfo;

public class QueryTimerList {
	public static void main(String[] args) 
			throws PushClientException,PushServerException {
		// 1. get apiKey and secretKey from developer console
		String apiKey = "xxxxxxxxxxxxxxxxxxxx";
		String secretKey = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";
		PushKeyPair pair = new PushKeyPair(apiKey, secretKey);

		// 2. build a BaidupushClient object to access released interfaces
		BaiduPushClient pushClient = new BaiduPushClient(pair,
				BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information
		// in this request.
		pushClient.setChannelLogHandler(new CloudLogHandler() {
			@Override
			public void onHandle(CloudLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			QueryTimerListRequest request = new QueryTimerListRequest().
			// addTimerId("xxxxxxxxxxxx"). //设置查询一个定时任务
					addStart(0).addLimit(6).addDeviceType(3);
			// 5. http request
			QueryTimerListResponse response = pushClient
					.queryTimerList(request);
			// Http请求结果解析打印
			System.out.println("totalNum: " + response.getTotalNum() + "\n"
					+ "result:");
			if (null != response) {
				List<?> list = response.getTimerResultInfos();
				for (int i = 0; i < list.size(); i++) {
					Object object = list.get(i);
					StringBuilder strBuilder = new StringBuilder();
					if (object instanceof TimerResultInfo) {
						TimerResultInfo timerResult = (TimerResultInfo) object;
						strBuilder.append("List[" + i + "]: " + "timerId= "
								+ timerResult.getTimerId() + ",sendTime= "
								+ timerResult.getSendTime() + ",message= "
								+ timerResult.getMessage() + ",msgType= "
								+ timerResult.getMsgType() + ",rangeType= "
								+ timerResult.getRangeType() + "\n");
					}
					System.out.println(strBuilder.toString());
				}
			}
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				e.printStackTrace();
			}
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				throw e;
			} else {
				System.out.println(String.format(
						"requestId: %d, errorCode: %d, errorMessage: %s",
						e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
