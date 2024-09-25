package com.amy.webdriver;

import com.amy.utils.LogUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.amy.constants.CoreConstant.CHROME;
import static com.amy.constants.CoreConstant.CHROME_HEADLESS;

public class DriverManager {
    private static Map<String, MutableCapabilities> factories;
    private static ThreadLocal<Driver> driverThread = new ThreadLocal<>();
    public static LogUtils logback = LogUtils.getInstance();

    private static void initDefaultFactories() {
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);

        factories = new ConcurrentHashMap<>();
        factories.put(CHROME, new ChromeOptions()
                .addArguments("start-maximized")
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .addArguments("--disable-blink-features=AutomationControlled"));
        factories.put(CHROME_HEADLESS, new ChromeOptions()
                .setPageLoadStrategy(PageLoadStrategy.EAGER)
                .addArguments("--ignore-certificate-errors")
                .addArguments("--window-size=1920,1080")
                .addArguments("--headless=new")
                .addArguments("--no-sandbox")
                .addArguments("--disable-features=VizDisplayCompositor")
                .addArguments("--disable-gpu").addArguments("--disable-dev-shm-usage")
                .setExperimentalOption("prefs", chromePrefs));
    }

    public static Driver initSeleniumDriver(String browserName) {
        initDefaultFactories();
        switch (browserName) {
            case CHROME:
            case CHROME_HEADLESS:
                Driver chromeDriver = new Driver(browserName);
                try {
                    chromeDriver.setWebDriver(new ChromeDriver((ChromeOptions) factories.get(browserName)));
                    return chromeDriver;
                } catch (RuntimeException exception) {
                    logback.debug("RuntimeException at initSeleniumDriver and then retry to create the second time");
                    chromeDriver.setWebDriver(new ChromeDriver((ChromeOptions) factories.get(browserName)));
                    return chromeDriver;
                }
            default:
                logback.debug(String.format("Cannot create Web driver for browser %s on %s. %n", browserName, System.getProperty("os.version")));
        }
        return null;
    }

    public static void createWebDriver(String browserName) {
        Driver driver = initSeleniumDriver(browserName);
        driverThread.set(driver);
    }

    public static Driver getDriver() {
        return driverThread.get();
    }

    public static void destroyDriver() {
        driverThread.remove();
    }
}
