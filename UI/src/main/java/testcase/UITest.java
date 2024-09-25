package testcase;

import com.amy.utils.ScreenshotUtils;
import com.amy.webdriver.DriverManager;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.TestPage;

import java.util.List;
import java.util.stream.Collectors;

import static page.TestPage.SALARY_HEADER;
import static page.TestPage.TITLE_HEADER;
import static page.TestPage.WORK_HEADER;

public class UITest extends BaseCase {

    @Test(groups = "smoke")
    public void Employee_Table_Verify_Title_is_uppercase() {
        TestPage page = new TestPage();
        logback.info("Verify: The title is upper case excluding the preposition");
        List<String> allTitles = page.getAllValueByHeader(TITLE_HEADER);
        SoftAssert softAssert = new SoftAssert();
        for (String title : allTitles) {
            softAssert.assertTrue(page.isFirstLetterUppercase(title), String.format("1st letter of a word in title '%s' is lowercase", title));
        }
        softAssert.assertAll();
    }
    @Test(groups = "smoke")
    public void Employee_Table_Verify_Salary_is_greater_or_equals_100K() {
        int expectedSalary = 100000;
        TestPage page = new TestPage();
        logback.info("Verify: The salary is greater or equals 100K");
        List<String> allSalary = page.getAllValueByHeader(SALARY_HEADER).stream().
                map(t -> t.replace("+", "").replace(",","").replace("$",""))
                .collect(Collectors.toList());;
        SoftAssert softAssert = new SoftAssert();
        for (String item : allSalary) {
            Assert.assertTrue(Integer.parseInt(item) >= expectedSalary, String.format("Actual salary '%s' is less than expected one '%s'", item,expectedSalary));
        }
        softAssert.assertAll();
    }
    @Test(groups = "smoke")
    public void Employee_Table_Verify_Work_column_not_contain_Manual_job() {
        TestPage page = new TestPage();
        logback.info("Verify: The Work column doesn't contain Manual");
        List<String> allWork = page.getAllValueByHeader(WORK_HEADER);
        SoftAssert softAssert = new SoftAssert();
        for (String item : allWork) {
            softAssert.assertFalse(item.equals("Manual"), String.format("Found Manual in Work column"));
        }
        softAssert.assertAll();
    }
    @Test(groups = "smoke", dataProvider = "userData")
    public void Email_Send_10_emails( String name, String email) {
        TestPage page = new TestPage();
        logback.info("Step 2: Submit user name & email");
        page.inputUserEmail(name, email);

        logback.info("Verify: Success message displays");
        Assert.assertTrue(page.successMsg.isDisplayed(30), "Failed to submit email");

    }
    @DataProvider(name="userData")
    private static Object [][] userData(){
        return new Object[][] {
                {"user1", "user1@example.com"},
                {"user2", "user2@example.com"},
                {"user3", "user3@example.com"},
                {"user4", "user4@example.com"},
                {"user5", "user5@example.com"},
                {"user6", "user6@example.com"},
                {"user7", "user7@example.com"},
                {"user8", "user8@example.com"},
                {"user9", "user9@example.com"},
                {"user10","user10@example.com"}
        };
    }

}
