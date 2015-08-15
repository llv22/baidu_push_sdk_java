package com.baidu.channel.sample;

import java.util.List;

import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.client.BaiduPushClient;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.QueryTopicRecordsRequest;
import com.baidu.cloud.push.model.QueryTopicRecordsResponse;
import com.baidu.cloud.push.model.Record;

public class QueryTopicRecords {
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
			QueryTopicRecordsRequest request = new QueryTopicRecordsRequest()
					.addTopicId("xxxxx").addStart(0).addLimit(5).
					// addRangeStart(new Long(xxxxxxxx)).
					// addRangeEnd(System.currentTimeMillis() / 1000).
					addDeviceType(3);
			// 5. http request
			QueryTopicRecordsResponse response = pushClient
					.queryTopicRecords(request);
			// Http请求结果解析打印
			StringBuilder strBuilder = new StringBuilder();
			if (null != response) {
				strBuilder.append("topicId: " + response.getTopicId() + "\n"
						+ "result:{\n");
				List<?> list = response.getTopicRecords();
				for (int i = 0; i < list.size(); i++) {
					Object object = list.get(i);
					if (i != 0) {
						strBuilder.append(",");
					}
					if (object instanceof Record) {
						Record record = (Record) object;
						strBuilder
								.append("{msgId: " + record.getMsgId()
										+ ", status: " + record.getMsgStatus()
										+ ", sendTime: " + record.getSendTime()
										+ "}\n");
					}
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
