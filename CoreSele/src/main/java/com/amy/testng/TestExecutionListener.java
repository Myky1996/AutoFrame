package com.amy.testng;

import com.amy.utils.LogUtils;
import com.amy.utils.ScreenshotUtils;
import com.amy.webdriver.Driver;
import com.amy.webdriver.DriverManager;
import org.testng.IConfigurable;
import org.testng.IConfigureCallBack;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.Utils;

public class TestExecutionListener implements IExecutionListener, ITestListener, IConfigurable {
    private static final LogUtils logback = LogUtils.getInstance();

    @Override
    public void onTestStart(ITestResult iTestResult) {
        // Do nothing
        logback.info("******************Beginning TC's "
                + iTestResult.getMethod().getMethodName().trim()
                +"******************");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        // Do nothing
        logback.info("******************Ending TC's name: "
                + iTestResult.getMethod().getMethodName().trim()
                + " is PASSED ******************");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Driver driver = DriverManager.getDriver();
        String methodName = iTestResult.getMethod().getMethodName().trim();

        ScreenshotUtils.attachScreenshotToReport(driver,methodName);

        logback.info("******************Ending TC's name: "
                + methodName
                + " is FAILED ******************");
    }
    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Driver driver = DriverManager.getDriver();
        String methodName = iTestResult.getMethod().getMethodName().trim();

        logback.error(Utils.longStackTrace(iTestResult.getThrowable(), false));

        ScreenshotUtils.attachScreenshotToReport(driver,methodName);

        logback.info("****************** TC's name: "
                + iTestResult.getMethod().getMethodName().trim()
                + " is SKIPPED since PRE-CONDITIONS OR POST-CONDITIONS OR RETRY TEST ARE ERROR ******************");
    }
    @Override
    public void onStart(ITestContext iTestContext) {
        // Do nothing
    }
    @Override
    public void onFinish(ITestContext iTestContext) {}
    @Override
    public void run(IConfigureCallBack callBack, ITestResult testResult) {
        callBack.runConfigurationMethod(testResult);
        String methodName = testResult.getMethod().getMethodName().trim();
        Driver driver = DriverManager.getDriver();

        if (testResult.getThrowable() != null) {
            for (int i = 0; i <= 3; i++) {
                ScreenshotUtils.attachScreenshotToReport(driver, methodName);
                callBack.runConfigurationMethod(testResult);
                if (testResult.getThrowable() == null) {
                    break;
                }
            }
        }
    }
}
