package com.baidu.channel.sample;

import java.util.List;

import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.client.BaiduPushClient;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.QueryTopicListRequest;
import com.baidu.cloud.push.model.QueryTopicListResponse;
import com.baidu.cloud.push.model.TopicResultInfo;

public class QueryTopicList {
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
			QueryTopicListRequest request = new QueryTopicListRequest()
					.addStart(0).addLimit(6).addDeviceType(3);
			// 5. http request
			QueryTopicListResponse response = pushClient
					.queryTopicList(request);
			// Http请求结果解析打印
			System.out.println("totalNum: " + response.getTotalNum() + "\n"
					+ "result:");
			if (null != response) {
				List<?> list = response.getTimerResultInfos();
				for (int i = 0; i < list.size(); i++) {
					Object object = list.get(i);
					StringBuilder strBuilder = new StringBuilder();
					if (object instanceof TopicResultInfo) {
						TopicResultInfo topicResult = (TopicResultInfo) object;
						strBuilder.append("List[" + i + "]: " + "topicId= "
								+ topicResult.getTopicId() + ",firstPushTime= "
								+ topicResult.getFirstPushTime()
								+ ",lastPushTime= "
								+ topicResult.getLastPushTime()
								+ ",totalPushDevsNum= "
								+ topicResult.getTotalPushDevsNum()
								+ ",totalAckDevsNum= "
								+ topicResult.getTotalAckDevsNum());
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
