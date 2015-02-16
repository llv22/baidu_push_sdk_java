package com.baidu.cloud.core.log;

public class NullCloudLogHandler implements CloudLogHandler {

    public void onHandle(CloudLogEvent event) {
        // to nothing
    }

}
