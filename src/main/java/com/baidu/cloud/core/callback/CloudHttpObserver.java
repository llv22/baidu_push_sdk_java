package com.baidu.cloud.core.callback;

import com.baidu.cloud.core.event.CloudHttpEvent;

public interface CloudHttpObserver {
	
	public void onHandle(CloudHttpEvent event);
	
}
