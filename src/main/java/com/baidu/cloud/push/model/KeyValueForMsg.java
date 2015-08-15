package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class KeyValueForMsg {

	@JSonPath(path="key")
	private String timestamp;
	
	@JSonPath(path="value")
	private MsgStatUnit value = null;
	
	public void setKey (String key) {
		this.timestamp = key;
	}
	
	public void setValue (MsgStatUnit value) {
		this.value = value;
	}
	
	public String getKey () {
		return timestamp;
	}
	
	public MsgStatUnit getValue () {
		return value;
	}
}
