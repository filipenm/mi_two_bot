package com.binance_bot.core.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LogHelper extends HelperBase {
    private static final int LOG_DEPTH = 4;
    private static final Logger logger = LogManager.getLogger();

    public void debug(Object... message) {
        for (Object o : message) {
            logger.debug(o.toString());
        }
    }

    public void info(Object... message) {
        for (Object o : message) {
            logger.info(o.toString());
        }
    }

    public void warn(String message, Exception exception) {
        String warningMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        warningMessage += message;
        logger.warn(warningMessage);
        exception.printStackTrace();
    }

    public void warn(String message) {
        String warningMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        warningMessage += message;
        logger.warn(warningMessage);
    }

    public void error(String message, Exception exception) {
        String errorMessage = getLineNumber() + " - " + getCallerMethodName() + " => ";
        errorMessage += message;
        logger.error(errorMessage);
        exception.printStackTrace();
        throw new Error(exception.getMessage());
    }

    public void error(String message) {
        logger.error(getLineNumber() + " - " + getCallerMethodName() + " => " + message);
        throw new Error(message);
    }

    private String getCallerMethodName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[LOG_DEPTH];
        return stackTraceElement.getMethodName();
    }

    private int getLineNumber() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement stackTraceElement = stackTrace[LOG_DEPTH];
        return stackTraceElement.getLineNumber();
    }
}
