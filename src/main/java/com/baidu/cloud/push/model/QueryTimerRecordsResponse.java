package com.baidu.cloud.push.model;

import java.util.List;
import java.util.LinkedList;

import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.JSonPath;
import com.baidu.cloud.core.annotation.R;
import com.baidu.cloud.push.constants.BaiduPushConstants;

public class QueryTimerRecordsResponse extends PushResponse{

	@JSonPath(path="response_params\\timer_id")
	@HttpParamKeyName(name=BaiduPushConstants.TIMER_ID, param=R.REQUIRE)
	private String timerId;
	
	@JSonPath(path="response_params\\result")
	@HttpParamKeyName(name=BaiduPushConstants.TIMER_RECORDS, param=R.REQUIRE)
	private List<Record> timerRecords = new LinkedList<Record>();
	
	// get
	public String getTimerId () {
		return timerId;
	}
	public List<Record> getTimerRecords () {
		return timerRecords;
	}
}
