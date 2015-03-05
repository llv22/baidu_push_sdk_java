/**
 * 
 */
package com.baidu.llv23.test.category;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.baidu.cloud.channel.auth.ChannelKeyPair;
import com.baidu.cloud.channel.client.BaiduChannelClient;
import com.baidu.cloud.channel.exception.ChannelClientException;
import com.baidu.cloud.channel.exception.ChannelServerException;
import com.baidu.cloud.channel.model.DeployStatus;
import com.baidu.cloud.channel.model.PushBroadcastMessageRequest;
import com.baidu.cloud.channel.model.PushBroadcastMessageResponse;
import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;

/**
 * @author llv23
 *
 */
public class MyChannelClientTest {
	
	@Test
	@Category(com.baidu.llv23.test.category.LocalCloudPushInterface.class)
    public void testPushBroadcastMessage() throws FileNotFoundException, IOException {

        // see 1. ApiKey/SecretKey on developer platform
		Properties defaultProps = new Properties();
		try(FileInputStream in = new FileInputStream("src/main/resources/META-INF/application.properties")){
			defaultProps.load(in);
		}

        String apiKey = defaultProps.getProperty("apiKey");
        String secretKey = defaultProps.getProperty("secretKey");
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // see 2. create BaiduChannelClient instance
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // see 3. if want to learn interaction details, please register CloudLogHandler instance
        channelClient.setChannelLogHandler(new CloudLogHandler() {
            @Override
            public void onHandle(CloudLogEvent event) {
                System.out.println(event.getMessage());
            }
        });

        try {

        	// see 4. create request instance
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            // see parameters description in - http://developer.baidu.com/wiki/index.php?title=docs/cplat/push/api/list#push_msg
            request.setMessageType(1);
            request.setDeviceType(4, true);
            request.setDeployStatus(DeployStatus.development);
            request.setMessage("{\"title\":\"iContests\",\"description\":\"New Contest is coming\"}");
            // see removal of msg_keys for iOS, must have in version
//            request.setMsgKey(null);
            
            // 5. pushMessage
            PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);
            Assert.assertEquals(1, response.getSuccessAmount());
        } catch (ChannelClientException e) {
            // handle exception in client side
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // handle exception in server side
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        } 
    }
	
}
