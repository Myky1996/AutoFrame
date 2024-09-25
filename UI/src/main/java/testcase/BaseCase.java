package testcase;

import com.amy.testng.AbstractBaseTest;
import com.amy.webdriver.DriverManager;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

@Listeners(com.amy.testng.TestExecutionListener.class)
public class BaseCase extends AbstractBaseTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        context = new ClassPathXmlApplicationContext("settings/UISettings.xml");
    }

    @BeforeMethod(alwaysRun = true)
    @Override
    public void beforeMethod(ITestResult result) {
        navigateToURL();
    }

    protected void navigateToURL() {
        logback.info(" Step 1: Navigate to URL: '{}'", environment.getDashboardURL());
        DriverManager.getDriver().get(environment.getDashboardURL());
    }

}
