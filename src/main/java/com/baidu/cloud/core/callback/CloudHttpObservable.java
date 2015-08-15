package com.baidu.cloud.core.callback;

import java.util.List;

import com.baidu.cloud.core.event.CloudHttpEvent;

public interface CloudHttpObservable {
	
	public void addHttpCallback(CloudHttpObserver callback);
	
	public void addBatchHttpCallBack(List<CloudHttpObserver> callbacks);
	
	public void removeCallBack(CloudHttpObserver callback);
	
	public void notifyAndCallback(CloudHttpEvent event);

}
