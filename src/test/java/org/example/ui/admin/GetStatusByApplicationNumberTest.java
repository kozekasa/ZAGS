package org.example.ui.admin;

import io.qameta.allure.*;
import org.example.api.UsefulAPI;
import org.example.ui.BaseTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.AdminData;
import org.junit.jupiter.api.*;

public class GetStatusByApplicationNumberTest extends BaseTest {
    @Owner("Aleksandr")
    @Test
    @Tag("admin")
    @Epic("ЗАГС")
    @Feature("Администрирование")
    @Story("Проверка статуса заявки по номеру")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Регистрация администратора: проверка статуса заявки по её номеру")
    public void testAdminCheck() {
        AdminData adminForUI = TestDataFactory.createAdminForUI();

        String applicationNumber = UsefulAPI.createApplicationAndGetId();

        pages.adminPage().StartRegistration()
                         .FillAdminForm(adminForUI)
                         .nextStep().click();

        String actualStatus = pages.adminPage().getStatusByApplicationNumber(applicationNumber);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает с ожидаемым!");
    }
}
