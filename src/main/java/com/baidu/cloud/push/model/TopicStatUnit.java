package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class TopicStatUnit {

	@JSonPath(path="ack")
	private int ack;
	
	public int getAckNum () {
		return ack;
	}
}
