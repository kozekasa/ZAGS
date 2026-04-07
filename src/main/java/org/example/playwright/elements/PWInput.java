package org.example.playwright.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;

public class PWInput {
    private final Page page;
    private final String selector;

    public PWInput(Page page, String selector) {
        this.page = page;
        this.selector = selector;
    }

    @Step("Очистка и ввод: {text}")
    public void fillFast(String text) {
        page.locator(selector).click();
        page.locator(selector).fill("");
        page.locator(selector).fill(text);
        page.keyboard().press("Tab");
    }

    @Step("Очистка и посимвольный ввод: {text}")
    public void fillSlowly(String text) {
        page.locator(selector).click();
        page.locator(selector).fill("");
        page.locator(selector).pressSequentially(text, new Locator.PressSequentiallyOptions().setDelay(50));
        page.keyboard().press("Tab");
    }
}
