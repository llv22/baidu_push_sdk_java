package com.baidu.channel.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

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

public class IOSPushNotificationToAll {

	@Test
	@Category(com.baidu.llv23.test.category.LocalCloudPushInterface.class)
    public void testPushBroadcastMessage() throws PushClientException,PushServerException, FileNotFoundException, IOException {
        // see: pre-requisite. ApiKey/SecretKey on developer platform
		Properties defaultProps = new Properties();
		try(FileInputStream in = new FileInputStream("src/main/resources/META-INF/application.properties")){
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
		BaiduPushClient pushClient = new BaiduPushClient(pair, BaiduPushConstants.CHANNEL_REST_URL);

		// 3. register a YunLogHandler to get detail interacting information in this request.
		pushClient.setChannelLogHandler(new CloudLogHandler() {
			@Override
			public void onHandle(CloudLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {
			// 4. specify request arguments
			// see : create iOS notification
			JSONObject notification = new JSONObject();
			JSONObject jsonAPS = new JSONObject();
			jsonAPS.put("alert", "Hello Baidu Push");
			jsonAPS.put("sound", "ttt"); // setup for ringing style, for example "ttt", user defined
			notification.put("aps", jsonAPS);
			notification.put("key1", "value1");
			notification.put("key2", "value2");
			
			PushMsgToAllRequest request = new PushMsgToAllRequest()
					.addMsgExpires(new Integer(3600)).addMessageType(1)
					.addMessage(notification.toString())
					.addSendTime(System.currentTimeMillis() / 1000 + 70) // setup of delay time for sending, must more than 1 minute, unit in second, here set for 70 seconds
					.addDepolyStatus(DeployStatus.development.getValue()).addDeviceType(4);
			// 5. http request
			PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
			// Http response print out
			System.out.println(String.format("msgId: %s ,sendTime: %d,timerId: %s", response.getMsgId(), response.getSendTime(), response.getTimerId()));
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
				System.err.println(String.format("requestId: %d, errorCode: %d, errorMessage: %s",e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
			}
		}
	}
}
