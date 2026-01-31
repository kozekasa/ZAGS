package org.example.pages;

import org.example.driver.WebDriverSingleton;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected BasePage() {
        PageFactory.initElements(WebDriverSingleton.getDriver(), this);
    }
}
