package org.example.playwright.pages;

import com.microsoft.playwright.Page;

public class PWPageManager {
    private final Page page;

    private PWUserRegistrationPage userRegistrationPage;
    private PWBirthRegistrationPage birthRegistrationPage;

    public PWPageManager(Page page) {
        this.page = page;
    }

    public PWUserRegistrationPage userRegistrationPage() {
        if (userRegistrationPage == null) {
            userRegistrationPage = new PWUserRegistrationPage(page);
        }
        return userRegistrationPage;
    }

    public PWBirthRegistrationPage birthRegistrationPage() {
        if (birthRegistrationPage == null) {
            birthRegistrationPage = new PWBirthRegistrationPage(page);
        }
        return birthRegistrationPage;
    }

    public void resetPages() {
        userRegistrationPage = null;
        birthRegistrationPage = null;
    }
}
