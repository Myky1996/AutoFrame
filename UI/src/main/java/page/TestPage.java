package page;

import com.amy.constants.Helper;
import com.amy.element.BaseElement;
import com.amy.webdriver.DriverManager;
import elements.TestTable;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TestPage {
    public static final String TITLE_HEADER = "Title";
    public static final String SALARY_HEADER = "Salary";
    public static final String WORK_HEADER = "Work";
    public TestTable tblEmployee = TestTable.xpath("//h2[text()='HTML Table with no id']/..//table");
    public static final List<String> preposition = Arrays.asList("at", "for", "in", "of", "to", "with");

    BaseElement link = BaseElement.xpath("//a[@href='/link-success']");
    BaseElement txtName = BaseElement.xpath("//input[contains(@id,'contact_name')]");
    BaseElement txtEmail = BaseElement.xpath("//input[contains(@id,'contact_email')]");
    BaseElement btnSubmit = BaseElement.xpath("//button[contains(@class, 'contact_submit')]");
    public BaseElement successMsg = BaseElement.xpath("//div[contains(@class, 'contact-message')]/p[text()='Thanks for contacting us']");
    public List<String> getAllValueByHeader(String headerName) {
        tblEmployee.scrollToThisControl(false);
        return tblEmployee.getAllRowValueByHeaderName(headerName);
    }

    public void inputUserEmail(String name, String email){
        link.scrollToThisControl(true);
        txtName.type(name);
        txtEmail.type(email);
        Helper.sleep(3);
        btnSubmit.click();
        DriverManager.getDriver().waitForPageLoad();
        Helper.sleep(1);
    }

    public boolean isFirstLetterUppercase(String text) {
        String[] words = text.split("\\+s");
        for (String word : words) {
            if (preposition.indexOf(word) < 0) {
                if (!Character.isUpperCase(word.charAt(0))) {
                    return false;
                }
            }
        }
        return true;
    }

}
