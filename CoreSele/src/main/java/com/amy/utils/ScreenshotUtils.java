package com.amy.utils;

import com.amy.constants.CoreConstant;
import com.amy.webdriver.Driver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;


public class ScreenshotUtils {
    private static final LogUtils logback = LogUtils.getInstance();

    private static void takeScreenShot(String fileName, Driver driver) {
        byte[] scrFile = null;

        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver.getWebDriver();
            scrFile = screenshot.getScreenshotAs(OutputType.BYTES);
            logback.logScreenshot(scrFile, "See the attachment for screenshot");
        } catch (NoSuchWindowException var7) {
            logback.error(String.format("Error during capturing the screenshot. %s", var7));
        }

        try {
            File file = new File(CoreConstant.DEFAULT_TESTOUTPUT_PATH);
            if (!file.exists()) {
                file.mkdir();
            }
            String fileID = fileName + "_" + DateUtils.getCurrentTime("HHmmss_MMddyy");
            FileUtils.writeByteArrayToFile(new File(CoreConstant.DEFAULT_TESTOUTPUT_PATH + File.separator + fileID + ".png"), scrFile);
        } catch (Exception var6) {
            Assert.fail(var6.getMessage(), var6);
        }
    }

    public static void attachScreenshotToReport(Driver driver, String fileName) {
        if (driver != null) {
            try {
                takeScreenShot(fileName, driver);
            } catch (Exception var6) {
                logback.error(String.format("Failed to capture the screenshot with reason: %s", var6));
            }
        } else {
            logback.error("Error: Cannot take screenshots as WebDriver is not started.");
        }
    }

}
