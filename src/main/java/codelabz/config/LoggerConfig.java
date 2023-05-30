package codelabz.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerConfig {
    private final static String getClassName(int depth) {
        return Thread.currentThread().getStackTrace()[depth].getClassName();
    }

    private final static String getMethodName(int depth) {
        return Thread.currentThread().getStackTrace()[depth].getMethodName();
    }

    public final static Logger getLogger() {
        return LoggerFactory.getLogger(getClassName(3) + "::" + getMethodName(3));
    }
}
