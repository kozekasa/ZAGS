package org.example.playwright.pages;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import org.example.playwright.elements.PWInput;

public abstract class PWBasePage {
    protected final Page page;

    protected PWBasePage(Page page) {
        this.page = page;
    }

    @Step("[PW] Ввод значения '{value}' в поле с селектором {selector}")
    public void setValue(String selector, String value) {
        new PWInput(page, selector).fillSlowly(value);
    }

    public void waitForPageLoad() {
        page.waitForLoadState();
    }
}