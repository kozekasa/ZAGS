package org.example.ui.admin;

import io.qameta.allure.*;
import org.example.ApiPreconditions;
import org.example.baseTests.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class GetStatusByApplicationNumberTest extends BaseTest {
    @Owner("Aleksandr")
    @ParameterizedTest(name = "Проверка статуса заявки по номеру в {0}")
    @ValueSource(strings = {"chrome", "firefox", "opera"})
    @Tag("admin")
    @Epic("ЗАГС")
    @Feature("Администрирование")
    @Story("Проверка статуса заявки по номеру")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация администратора: проверка статуса заявки по её номеру")
    public void testAdminCheck(String browser) {
        openUrl(browser);

        AdminData adminForUI = TestDataFactory.createAdminForUI();

        String applicationNumber = ApiPreconditions.createApplicationAndGetId();

        pages.adminPage().StartRegistration()
                         .FillAdminForm(adminForUI)
                         .nextStep().click();

        String actualStatus = pages.adminPage().getStatusByApplicationNumber(applicationNumber);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает с ожидаемым!");
    }
}
