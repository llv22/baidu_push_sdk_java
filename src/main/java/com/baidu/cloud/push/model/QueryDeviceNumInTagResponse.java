package com.baidu.cloud.push.model;

import com.baidu.cloud.core.annotation.JSonPath;

public class QueryDeviceNumInTagResponse extends PushResponse{

	@JSonPath(path="response_params\\device_num")
	private int deviceNum;
	
	// get
	public int getDeviceNum () {
		return deviceNum;
	}
}
