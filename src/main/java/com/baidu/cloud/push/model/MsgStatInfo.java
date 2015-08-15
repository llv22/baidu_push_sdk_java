package com.baidu.cloud.push.model;

import java.util.LinkedList;
import java.util.List;

import com.baidu.cloud.core.annotation.JSonPath;

public class MsgStatInfo {
    
	@JSonPath(path="total_num")
	private int totalNum;
	
	@JSonPath(path="range_type")
	private int rangeType;
	
	@JSonPath(path="result")
	private List<KeyValueForMsg> result = new LinkedList<KeyValueForMsg>();
	
	public int getTotalNum () {
	    return totalNum;
	}
	public int getRangeType () {
	    return rangeType;
	}
	public List<KeyValueForMsg> getResult () {
	    return result;
	}
}
