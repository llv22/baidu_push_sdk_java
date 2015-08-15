package com.baidu.cloud.push.model;

import java.util.LinkedList;
import java.util.List;

import com.baidu.cloud.core.annotation.JSonPath;

public class QueryStatisticMsgResponse extends PushResponse{

	@JSonPath(path="response_params\\result")
	private List<MsgStatInfo> msgStats = new LinkedList<MsgStatInfo>();
	
	public List<MsgStatInfo> getMsgStats () {
		return msgStats;
	}
}
