package org.example.elements;

import org.example.driver.WebDriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class StatusField {
    private final WebElement element;

    public StatusField(WebElement element) {
        this.element = element;
    }

    public String getText() {
        new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }
}
