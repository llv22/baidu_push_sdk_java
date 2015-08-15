package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class KeyValueForAck {

	@JSonPath(path="key")
	private String timestamp;
	
	@JSonPath(path="value")
	private int value;
	
	public void setKey (String key) {
		this.timestamp = key;
	}
	
	public void setValue (int value) {
		this.value = value;
	}
	
	public String getKey () {
		return timestamp;
	}
	public int getValue () {
		return value;
	}
}
