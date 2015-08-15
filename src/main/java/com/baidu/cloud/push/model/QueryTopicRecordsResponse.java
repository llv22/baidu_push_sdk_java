package com.baidu.cloud.push.model;

import java.util.List;
import java.util.LinkedList;

import com.baidu.cloud.core.annotation.HttpParamKeyName;
import com.baidu.cloud.core.annotation.JSonPath;
import com.baidu.cloud.core.annotation.R;
import com.baidu.cloud.core.annotation.RangeRestrict;
import com.baidu.cloud.push.constants.BaiduPushConstants;

public class QueryTopicRecordsResponse extends PushResponse{

	@JSonPath(path="response_params\\topic_id")
	@HttpParamKeyName(name=BaiduPushConstants.TOPIC_ID, param=R.REQUIRE)
	@RangeRestrict(minLength=1, maxLength=128)
	private String topicId = null;
	
	@JSonPath(path="response_params\\result")
	@HttpParamKeyName(name=BaiduPushConstants.TOPIC_RECORDS, param=R.REQUIRE)
	private List<Record> topicRecords = new LinkedList<Record>();
	
	// get
	public String getTopicId () {
		return topicId;
	}
	public List<Record> getTopicRecords () {
		return topicRecords;
	}
}

