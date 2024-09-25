package com.amy.webdriver;

import com.amy.constants.CoreConstant;
import com.amy.constants.Helper;
import lombok.Getter;
import lombok.Setter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.amy.utils.LogUtils;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Driver implements WebDriver {
    @Getter
    @Setter
    protected WebDriver webDriver;
    protected WebDriverWait driverWait;
    protected static final LogUtils logback = LogUtils.getInstance();

    protected String browserName;

    protected void setDriverSetting(String browserName) {
        this.browserName = browserName;
    }

    protected Driver(String browserName) {
        setDriverSetting(browserName);
    }

    @Override
    public void get(String url) {
        try {
            webDriver.get(url);
        } catch (TimeoutException ex) {
            logback.fail("Exception: TimeoutException at getDriver method on Driver class");
        }
    }

    @Override
    public String getCurrentUrl() {
        try {
            return webDriver.getCurrentUrl();
        } catch (TimeoutException ex) {
            logback.fail("Exception: TimeoutException while getting currentUrl");
            return "";
        }
    }

    @Override
    public String getTitle() {
        try {
            return webDriver.getTitle();
        } catch (TimeoutException ex) {
            logback.fail("Exception: TimeoutException while getting getTitle");
            return "";
        }
    }

    @Override
    public List<WebElement> findElements(By by) {
        setDriverWaitBySecond(webDriver, CoreConstant.DEFAULT_TIMEOUT_30S);
        return driverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    @Override
    public WebElement findElement(By by) {
        setDriverWaitBySecond(webDriver, CoreConstant.DEFAULT_TIMEOUT_30S);
        return driverWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void setDriverWaitBySecond(WebDriver driver, long timeOut) {
        this.driverWait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
    }

    @Override
    public String getPageSource() {
        return webDriver.getPageSource();
    }
    @Override
    public void close() {
        webDriver.close();
    }

    @Override
    public void quit() {
        webDriver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    public void maximize() {
        webDriver.manage().window().maximize();
    }
    @Override
    public TargetLocator switchTo() {
        return null;
    }

    @Override
    public Navigation navigate() {
        return webDriver.navigate();
    }

    @Override
    public Options manage() {
        return webDriver.manage();
    }

    public boolean isElementClickable(By by, int timeout) {
        try {
            setDriverWaitBySecond(webDriver, timeout);
            driverWait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
    public boolean isElementVisible(By by, int timeout) {
        try {
            setDriverWaitBySecond(webDriver, timeout);
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }
    public void waitForPageLoad() {
        waitForPageLoad(webDriver, CoreConstant.DEFAULT_TIMEOUT_30S);
    }
    public void waitForPageLoad(WebDriver driver, long timeout) {
        ExpectedCondition<Boolean> expectation = waitDriver -> {
            assert waitDriver != null;
            return ((JavascriptExecutor) waitDriver).executeScript("return document.readyState").toString().equalsIgnoreCase("complete");
        };
        try {
            Helper.sleep(1);
            setDriverWaitBySecond(driver, timeout);
            driverWait.until(expectation);
        } catch (TimeoutException e) {
            logback.fail(String.format("Timeout waiting for Page Load Request to complete. {%s}", e.getMessage()));
        }
    }
}
