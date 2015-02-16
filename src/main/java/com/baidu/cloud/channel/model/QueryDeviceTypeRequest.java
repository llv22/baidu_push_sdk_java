package com.baidu.cloud.channel.model;

import com.baidu.cloud.core.annotation.HttpPathKeyName;
import com.baidu.cloud.core.annotation.R;

public class QueryDeviceTypeRequest extends ChannelRequest {

    @HttpPathKeyName(param = R.OPTIONAL)
    private Long channelId = null;

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

}
