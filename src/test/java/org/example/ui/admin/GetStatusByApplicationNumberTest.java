package org.example.ui.admin;

import io.qameta.allure.*;
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
        AdminData admin = TestDataFactory.createAdminForUI();

        //Пока не дошли до API, то номер захардкодил, потом будет просто его сюда передать.
        String applicationNumber = "58458";

        pages.adminPage().StartRegistration()
                         .FillAdminForm(admin)
                         .nextStep().click();

        String actualStatus = pages.adminPage().getStatusByApplicationNumber(applicationNumber);

        Assertions.assertEquals("На рассмотрении", actualStatus,
                "Статус заявки №" + applicationNumber + " не совпадает с ожидаемым!");
    }
}
