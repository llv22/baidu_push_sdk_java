package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.R;
import com.baidu.cloud.push.constants.BaiduPushConstants;

public abstract class PushRequest {

	@HttpParamKeyName(name=BaiduPushConstants.VERSION, param=R.OPTIONAL)
	protected String v = null;
	
	@HttpParamKeyName(name=BaiduPushConstants.TIMESTAMP, param=R.REQUIRE)
	protected Long timestamp = System.currentTimeMillis() / 1000L;
	
	@HttpParamKeyName(name=BaiduPushConstants.EXPIRES, param=R.OPTIONAL)
	protected Long expires = null;
	
	@HttpParamKeyName(name=BaiduPushConstants.DEVICE_TYPE, param=R.OPTIONAL)
	protected Integer deviceType = null;
	
	// get
	public Long getExpires () {
		return expires;
	}
	public Integer getDevice () {
		return deviceType;
	}
	// set
	public void setExpires (Long expires) {
		this.expires = expires;
	}
	public void setDeviceType (Integer deviceType) {
		this.deviceType = deviceType;
	}
}
