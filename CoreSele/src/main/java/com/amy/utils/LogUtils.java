package com.amy.utils;
import com.google.common.io.BaseEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import java.util.Objects;

public class LogUtils {
    private static LogUtils instance = null;

    private LogUtils() {
    }

    public static LogUtils getInstance() {
        if (Objects.isNull(instance)) {
            instance = new LogUtils();
        }
        return instance;
    }

    private static Logger getLogger() {
        final StackTraceElement myCaller = Thread.currentThread().getStackTrace()[2];
        String className = myCaller.getClassName();
        return LoggerFactory.getLogger(className);
    }

    public void error(String var1, Object... var2) {
        getLogger().error(var1, var2);
    }

    public void fail(String var1, Object... var2) {
        getLogger().error(var1, var2);
        Assert.fail(var1);
    }
    public void info(String var1, Object... var2) {
        getLogger().info(var1,var2);
    }

    public void debug(String var1, Object... var2) {getLogger().debug(var1, var2);}

    //log screenshot to Report  Portal
    public void logScreenshot(byte[] bytes, String message) {
        LoggerFactory.getLogger("binary_data_logger").error("RP_MESSAGE#BASE64#{}#{}",
                BaseEncoding.base64().encode(bytes), message);
    }

}
