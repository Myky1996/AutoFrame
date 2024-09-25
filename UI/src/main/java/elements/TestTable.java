package elements;

import com.amy.element.TableBase;
import org.openqa.selenium.By;

public class TestTable extends TableBase {
    public TestTable(By locator) {
        super(locator);
        rowXpath = ".//tr[count(th)<1]";
    }
    public static TestTable xpath(String xpath) {
        return new TestTable(By.xpath(xpath));
    }
}
