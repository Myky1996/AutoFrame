package com.amy.constants;

import java.io.File;

public class CoreConstant {
    public static final String USER_DIR = "user.dir";
    public static final String DEFAULT_TESTOUTPUT_PATH = System.getProperty(USER_DIR) + File.separator + "test-output" + File.separator + "snapshots";

    /************************* TIMEOUT ***********************/
    public static final int DEFAULT_TIMEOUT_30S = 30;
    public static final String CHROME = "chrome";
    public static final String CHROME_HEADLESS = "chrome_headless";

}
