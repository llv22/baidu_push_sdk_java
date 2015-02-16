package com.baidu.cloud.channel.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class QueryDeviceTypeResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\device_type")
    private long deviceType;

    public long getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(long deviceType) {
        this.deviceType = deviceType;
    }

}
