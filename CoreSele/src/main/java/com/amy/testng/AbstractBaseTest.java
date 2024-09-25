package com.amy.testng;

import com.amy.constants.CoreConstant;
import com.amy.utils.LogUtils;
import com.amy.webdriver.DriverManager;
import org.springframework.context.ApplicationContext;
import com.amy.Environment;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;


@Listeners(com.amy.testng.TestExecutionListener.class)
public abstract class AbstractBaseTest {

    public static final LogUtils logback = LogUtils.getInstance();
    public static ApplicationContext context;

    public Environment environment;

    @BeforeSuite(alwaysRun = true)
    public abstract void beforeSuite();

    @Parameters({"browser","env"})
    @BeforeTest(alwaysRun = true)
    public void beforeTest(@Optional(CoreConstant.CHROME) String browser, String env) {
        environment = (Environment) context.getBean(env);
        createDriver(browser);
    }

    protected void createDriver(String browser) {
        DriverManager.createWebDriver(browser);
        DriverManager.getDriver().maximize();
    }

    @BeforeMethod(alwaysRun = true)
    public abstract void beforeMethod(ITestResult result);

    @AfterTest(alwaysRun = true)
    public void afterTest() {
        DriverManager.getDriver().quit();
        DriverManager.destroyDriver();
    }
}
