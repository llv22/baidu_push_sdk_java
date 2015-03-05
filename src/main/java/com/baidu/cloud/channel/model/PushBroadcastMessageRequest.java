package com.baidu.cloud.channel.model;

import java.util.UUID;

import com.baidu.cloud.channel.constants.BaiduChannelConstants;
import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.R;
import com.baidu.cloud.core.annotation.RangeRestrict;

public class PushBroadcastMessageRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.PUSH_TYPE, param = R.REQUIRE)
    private final Integer pushType = 3;

    @HttpParamKeyName(name = BaiduChannelConstants.DEVICE_TYPE, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 5)
    private Integer deviceType = null;

    /**
     * default value of deployment status is development status
     */
    @HttpParamKeyName(name = BaiduChannelConstants.DEPLOY_STATUS, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 2)
    private Integer deployStatus = DeployStatus.development.getValue();

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGE_TYPE, param = R.OPTIONAL)
    private Integer messageType = new Integer(0);

    @HttpParamKeyName(name = BaiduChannelConstants.MESSAGES, param = R.REQUIRE)
    private String message = null;

    /**
     * changed into null, in order to do compablity check-up for iOS
     */
    @HttpParamKeyName(name = BaiduChannelConstants.MSG_KEYS, param = R.OPTIONAL)
    private String msgKey = "channel_msg_key";

    public Integer getDeviceType() {
        return deviceType;
    }

    /**
     * generate uuid for msgkey to avoid rewriting of msg_key in cloud BAE platform
     * @param deviceType
     * @param isGeneratedMsgKey
     */
    public void setDeviceType(Integer deviceType, boolean isGeneratedMsgKey) {
        this.deviceType = deviceType;
        if (isGeneratedMsgKey) {
        	// see iOS device type, reset for "msg_keys" fields;
        	this.msgKey = UUID.randomUUID().toString();
        }
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMsgKey() {
        return msgKey;
    }

    public void setMsgKey(String msgKey) {
        this.msgKey = msgKey;
    }

    public Integer getPushType() {
        return pushType;
    }

    public DeployStatus getDeployStatus() {
    	if (this.deployStatus == 1){
    		return DeployStatus.development;
    	}
    	else if (this.deployStatus == 2){
    		return DeployStatus.production;
    	}
    	else {
    		return DeployStatus.development;
    	}
    }

    public void setDeployStatus(DeployStatus deployStatus) {
    	if (this.getDeployStatus() !=  deployStatus) {
    		this.deployStatus = deployStatus.getValue();
    	}
    }

}
