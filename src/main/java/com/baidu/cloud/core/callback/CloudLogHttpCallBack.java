package com.baidu.cloud.core.callback;

import com.baidu.cloud.core.event.CloudHttpEvent;
import com.baidu.cloud.core.log.NullCloudLogHandler;
import com.baidu.cloud.core.log.CloudLogEvent;
import com.baidu.cloud.core.log.CloudLogHandler;

public class CloudLogHttpCallBack implements
		CloudHttpObserver {

	private CloudLogHandler logHandler = new NullCloudLogHandler();
	
	public void onHandle(CloudHttpEvent event) {
		
		int level = CloudLogEvent.DEBUG;
		if ( event.getHttpStatusCode() == 200 ) {
			level = CloudLogEvent.NOTICE;
		} else {
			level = CloudLogEvent.WARNING;
		}
		String message = String.format("URL:[%s]\n" +
				"params:[%s]\n" +
				"HttpStatusCode:[%d]\n" +
				"Response:[%s]\n",
				event.getUrl(),
				event.getParams(),
				event.getHttpStatusCode(),
				event.getResponse());
		
		CloudLogEvent logEvent = new CloudLogEvent(level, message);
		if ( logHandler != null ) {
			synchronized (this) {
				logHandler.onHandle(logEvent);
			}
		}
	}

	public void setHandler(CloudLogHandler handler) {
		this.logHandler = handler;
	}
	
	
	
}
