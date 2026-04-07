package org.example.selenium.elements;

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
        /*
        element.click();
        element.sendKeys(Keys.HOME);
        for (char ch : text.toCharArray()) {
            element.sendKeys(String.valueOf(ch));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        */
        element.sendKeys(Keys.TAB);
    }
}
