package com.baidu.channel.sample;

import java.util.List;

import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.client.BaiduPushClient;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.KeyValueForTopic;
import com.baidu.cloud.push.model.QueryStatisticTopicRequest;
import com.baidu.cloud.push.model.QueryStatisticTopicResponse;

public class QueryStatisticTopic {
	public static void main(String[] args) throws PushClientException,
			PushServerException {
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
			QueryStatisticTopicRequest request = new QueryStatisticTopicRequest()
					.addTopicId("BaiduPush").addDeviceType(3);
			// 5. http request
			QueryStatisticTopicResponse response = pushClient
					.queryStatisticTopic(request);
			// Http请求结果解析打印
			if (null != response) {
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append("totalNum: " + response.getTotalNum());
				List<KeyValueForTopic> topicStats = response.getResult();
				strBuilder.append("\nresult:{");
				for (int i = 0; i < topicStats.size(); i++) {
					KeyValueForTopic keyValue = topicStats.get(i);
					if (i != 0) {
						strBuilder.append(",");
					}
					strBuilder.append("" + keyValue.getKey() + ":{"
							+ "ackNum: " + keyValue.getValue().getAckNum()
							+ "}");
				}
				strBuilder.append("}");
				System.out.println(strBuilder.toString());
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
