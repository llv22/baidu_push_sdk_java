package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.JSonPath;
import com.baidu.cloud.core.annotation.R;
import com.baidu.cloud.push.constants.BaiduPushConstants;

public class PushBatchUniMsgResponse extends PushResponse{

	@JSonPath(path="response_params\\msg_id")
    @HttpParamKeyName(name=BaiduPushConstants.MSG_ID, param=R.REQUIRE)
    private String msgId = null;
    
    @JSonPath(path="response_params\\send_time")
    @HttpParamKeyName(name=BaiduPushConstants.SEND_TIME, param=R.REQUIRE)
    private long sendTime;
    
    public String getMsgId () {
    	return msgId;
    }
    public long getSendTime () {
    	return sendTime;
    }
}
