package org.example.pages;

import io.qameta.allure.Step;
import org.example.driver.WebDriverSingleton;
import org.example.elements.CustomInput;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    protected BasePage() {
        PageFactory.initElements(WebDriverSingleton.getDriverThreadLocal(), this);
    }

    @Step("Ввод значения '{value}' в поле {field}")
    public void setValue(WebElement field, String value) {
        new CustomInput(field).fillAndEnter(value);
    }
}
