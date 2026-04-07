package org.example.playwright;

import org.example.dataFactory.TestDataFactory;
import org.example.models.UserData;
import org.junit.jupiter.api.Test;
import org.example.playwright.baseTest.PWBaseTest;

public class PWUserRegistrationTest extends PWBaseTest {

    @Test
    public void testUserRegistration() {
        UserData user = TestDataFactory.createDefaultUser();

        pages.userRegistrationPage()
                .startRegistration()
                .fillUserForm(user)
                .clickNext();

        LOGGER.info("Регистрация успешно инициирована");
    }
}
