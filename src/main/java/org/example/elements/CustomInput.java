package org.example.elements;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class CustomInput {
    private final WebElement element;

    public CustomInput (WebElement element) {
        this.element = element;
    }

    public void fillAndEnter(String text) {
        element.click();
        element.clear();
        element.sendKeys(text);
        element.sendKeys(Keys.TAB);
    }
}
