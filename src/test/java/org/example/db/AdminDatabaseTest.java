package org.example.db;

import io.qameta.allure.*;
import org.example.ApiPreconditions;
import org.example.baseTests.BaseDbTest;
import org.junit.jupiter.api.*;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@Epic("DB")
@Feature("Регистрация администратора")
public class AdminDatabaseTest extends BaseDbTest {

    private int staffId;

    @Test
    @Owner("Aleksandr")
    @Tag("positive")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Проверка создания администратора в БД")
    public void testStaffCreationInDb() throws SQLException {

        staffId = ApiPreconditions.createStaffAndGetId();

        assertThat(dbSteps.isStaffExists(staffId))
                .as("Сотрудник с ID %d должен существовать в БД", staffId)
                .isTrue();
    }

    @AfterEach
    @Step("Очистка тестовых данных: удаление staff")
    public void cleanUpData() throws SQLException {
        dbSteps.deleteStaff(staffId);
    }
}