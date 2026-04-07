package org.example.selenium.pages;

public class PageManager {

    private UserRegistrationPage userRegistrationPage;
    private MarriageRegistrationPage marriageRegistrationPage;
    private BirthRegistrationPage birthRegistrationPage;
    private DeathRegistrationPage deathRegistrationPage;
    private AdminPage adminPage;


    public UserRegistrationPage userRegistrationPage() {
        if (userRegistrationPage == null) {
            userRegistrationPage = new UserRegistrationPage();
        }
        return userRegistrationPage;
    }

    public MarriageRegistrationPage marriageRegistrationPage() {
        if (marriageRegistrationPage == null) {
            marriageRegistrationPage = new MarriageRegistrationPage();
        }
        return marriageRegistrationPage;
    }

    public BirthRegistrationPage birthRegistrationPage() {
        if (birthRegistrationPage == null) {
            birthRegistrationPage = new BirthRegistrationPage();
        }
        return birthRegistrationPage;
    }

    public DeathRegistrationPage deathRegistrationPage() {
        if (deathRegistrationPage == null) {
            deathRegistrationPage = new DeathRegistrationPage();
        }
        return deathRegistrationPage;
    }

    public AdminPage adminPage() {
        if (adminPage == null) {
            adminPage = new AdminPage();
        }
        return adminPage;
    }


    public void resetPages() {
        userRegistrationPage = null;
        birthRegistrationPage = null;
        marriageRegistrationPage = null;
        deathRegistrationPage = null;
        adminPage = null;
    }
}