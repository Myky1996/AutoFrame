package com.amy.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TableBase extends BaseElement {
    protected String headerXpath = ".//th";
    protected String rowXpath = ".//tr";
    protected String columnXpath = ".//td";

    public TableBase(By locator) {
        super(locator);
    }

    public static TableBase xpath(String xpath) {
       return new TableBase(By.xpath(xpath));
    }
    public List<WebElement> getHeaderElements() {
        return this.getWebElement().findElements(By.xpath(headerXpath));
    }

    public List<String> getAllHeaderNames() {
        List<String> names = new ArrayList<>();
        for (WebElement header : getHeaderElements()) {
            names.add(header.getText());
        }
        return names;
    }
    public List<WebElement> getAllRows(){
      return this.getWebElement().findElements(By.xpath(rowXpath));
    }
    public WebElement getRowByIndex(int rowIndex) {
        return getAllRows().get(rowIndex);
    }

    public List<WebElement> getAllColumnInRow(int rowIndex){
        return getRowByIndex(rowIndex).findElements(By.xpath(columnXpath));
    }
    public WebElement getCell(int rowIndex, int colIndex){
        return getAllColumnInRow(rowIndex).get(colIndex);
    }

    public String getCellValueByHeaderName(int rowIndex, String headerName){
        int colIndex = getAllHeaderNames().indexOf(headerName);
        return getCell(rowIndex,colIndex).getText();
    }

    public List<String> getAllRowValueByHeaderName(String headerName){
        List<String> values = new ArrayList<>();
        for(int i=0; i < getAllRows().size();i++){
           String header = getCellValueByHeaderName(i,headerName);
            values.add(header);
        }
        return values;
    }
}
