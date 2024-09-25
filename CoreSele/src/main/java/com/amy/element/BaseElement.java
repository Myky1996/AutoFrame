package com.amy.element;


import com.amy.webdriver.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static com.amy.constants.CoreConstant.DEFAULT_TIMEOUT_30S;


public class BaseElement {
    protected WebElement instance = null;
    protected By locator;

    public BaseElement(By locator) {
        this.locator = locator;
    }

    public WebElement getWebElement() {
        instance = DriverManager.getDriver().findElement(locator);
        return instance;
    }

    public static BaseElement xpath(String xpath) {
        return new BaseElement(By.xpath(xpath));
    }

    public static BaseElement cssSelector(String xpath) {
        return new BaseElement(By.cssSelector(xpath));
    }

    public void click() {
        DriverManager.getDriver().isElementClickable(locator, DEFAULT_TIMEOUT_30S);
        getWebElement().click();
    }
    public String getText() {
        DriverManager.getDriver().isElementVisible(locator, DEFAULT_TIMEOUT_30S);
        return getWebElement().getText();
    }
    public void type(String text) {
        DriverManager.getDriver().isElementVisible(locator, DEFAULT_TIMEOUT_30S);
        getWebElement().clear();
        getWebElement().sendKeys(text);
    }
    public void scrollToThisControl(boolean isAlignedToTop) {
        DriverManager.getDriver().isElementVisible(locator, DEFAULT_TIMEOUT_30S);
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver().getWebDriver();
        js.executeScript(String.format("arguments[0].scrollIntoView(%s);", isAlignedToTop), this.getWebElement());
    }

    public boolean isDisplayed(int timeoutInSeconds) {
        boolean isVisible = DriverManager.getDriver().isElementVisible(locator, timeoutInSeconds);
        return isVisible;
    }

}
