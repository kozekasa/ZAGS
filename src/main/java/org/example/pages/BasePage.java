package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.example.elements.CustomInput;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected BasePage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }

    public void fillField (WebElement field, String value) {
        new CustomInput(field).fillAndEnter(value);
    }
}
