package com.baidu.cloud.channel.model;

import com.baidu.cloud.channel.constants.BaiduChannelConstants;
import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.R;

public abstract class ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.VERSION, param = R.OPTIONAL)
    protected String v = null;

    @HttpParamKeyName(name = BaiduChannelConstants.TIMESTAMP, param = R.REQUIRE)
    protected Long timestamp = System.currentTimeMillis() / 1000L;

    @HttpParamKeyName(name = BaiduChannelConstants.EXPIRES, param = R.OPTIONAL)
    protected Long expires = null;

}
