package testcase;
import com.amy.Environment;
import com.amy.testng.AbstractBaseTest;
import com.amy.utils.LogUtils;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import org.testng.annotations.Parameters;

import java.lang.reflect.Method;

public class APIBaseTest {
    public static final LogUtils logback = LogUtils.getInstance();
    public static ApplicationContext context;
    @Getter
    public static Environment environment;
    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        context = new ClassPathXmlApplicationContext("settings/APISettings.xml");
    }

    @Parameters({"env"})
    @BeforeClass(alwaysRun = true)
    public void beforeClass(String env) {
        logback.info("Get environment info");
        environment = (Environment) context.getBean(env);
    }
    @BeforeMethod(alwaysRun = true)
    public void beforeMethod(Method m) {
        logback.info("******************Beginning TC's "
                + m.getName() +"******************");
    }
    @AfterMethod(alwaysRun = true)
    public void afterMethod(Method m) {
        logback.info("******************Ending TC's name: "
                + m.getName()
                + "******************");
    }
    }
