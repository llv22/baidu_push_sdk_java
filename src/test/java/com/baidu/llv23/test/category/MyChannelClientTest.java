/**
 * 
 */
package com.baidu.llv23.test.category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.baidu.cloud.channel.module.DeployStatus;
import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;
import com.baidu.cloud.push.auth.PushKeyPair;
import com.baidu.cloud.push.client.BaiduPushClient;
import com.baidu.cloud.push.constants.BaiduPushConstants;
import com.baidu.cloud.push.exception.PushClientException;
import com.baidu.cloud.push.exception.PushServerException;
import com.baidu.cloud.push.model.PushMsgToAllRequest;
import com.baidu.cloud.push.model.PushMsgToAllResponse;

/**
 * @author llv23
 *
 */
public class MyChannelClientTest {

	@Test
	@Category(com.baidu.llv23.test.category.LocalCloudPushInterface.class)
	public void testPushBroadcastMessage()  throws FileNotFoundException, IOException{
		// see 1. ApiKey/SecretKey on developer platform
		Properties defaultProps = new Properties();
		try (FileInputStream in = new FileInputStream("src/main/resources/META-INF/application.properties")) {
			defaultProps.load(in);
		}

		// 1. get apiKey and secretKey from developer console
		String appidKey = defaultProps.getProperty("appidKey");
		if(appidKey.equals("${appidKey}")) {
			//TODO : not defined
			appidKey = System.getProperty("appidKey");
		}
		String secretKey = defaultProps.getProperty("secretKey");
		if(secretKey.equals("${secretKey}")) {
			//TODO : not defined
			secretKey = System.getProperty("secretKey");
		}

		PushKeyPair pair = new PushKeyPair(appidKey, secretKey);

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
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage("{\"title\":\"iContests\",\"description\":\"New Contest is coming, Random\"}")
					.addSendTime(System.currentTimeMillis() / 1000 + 65)
					// 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例2分钟后推送
					.addDepolyStatus(DeployStatus.development.getValue())
					.addDeviceType(4);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http请求结果解析打印
			System.out.println("msgId: " + response.getMsgId() + ",sendTime: " + response.getSendTime() + ",timerId: " + response.getTimerId());
		} catch (PushClientException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				e.printStackTrace();
			} 
			System.err.println("Client Push Error Happened");
		} catch (PushServerException e) {
			if (BaiduPushConstants.ERROROPTTYPE) {
				e.printStackTrace();
			} 
			System.err.println(String.format("Server Push Received Error Happended, requestId: %d, errorCode: %d, errorMessage: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
		}

	}

}
