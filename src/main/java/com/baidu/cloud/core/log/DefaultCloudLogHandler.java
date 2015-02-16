package com.baidu.cloud.core.log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultCloudLogHandler implements CloudLogHandler {

    private Logger logger = Logger.getLogger(DefaultCloudLogHandler.class
            .getName());

    public void onHandle(CloudLogEvent event) {
        if (event.getLevel() == CloudLogEvent.FATAL) {
            logger.log(Level.SEVERE, event.getMessage());
        } else if (event.getLevel() == CloudLogEvent.WARNING) {
            logger.log(Level.WARNING, event.getMessage());
        } else if (event.getLevel() == CloudLogEvent.NOTICE) {
            logger.log(Level.INFO, event.getMessage());
        }
    }

}
