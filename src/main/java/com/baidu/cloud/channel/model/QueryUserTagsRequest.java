package com.baidu.cloud.channel.model;

import com.baidu.cloud.channel.constants.BaiduChannelConstants;
import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.R;

public class QueryUserTagsRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
