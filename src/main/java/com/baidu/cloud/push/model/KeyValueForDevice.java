package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class KeyValueForDevice {

	@JSonPath(path="key")
	private String timestamp;
	
	@JSonPath(path="value")
	private DeviceStatUnit value = null;
	
	public void setKey (String key) {
		this.timestamp = key;
	}
	
	public void setValue (DeviceStatUnit value) {
		this.value = value;
	}
	
	public String getKey () {
		return timestamp;
	}
	public DeviceStatUnit getValue () {
		return value;
	}
}
