package org.example.db;

import io.qameta.allure.*;
import org.example.ApiPreconditions;
import org.example.baseTests.BaseDbTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.ApplicationData;
import org.example.models.UserDataAPI;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@Epic("DB")
@Feature("Регистрация заявки")
public class UserDatabaseTest extends BaseDbTest {

    private int applid;

    @Test
    @Story("Регистрация брака")
    @DisplayName("Проверка создания заявки Регистрация брака в БД")
    public void testMarriageRegistrationCreationInDb() throws SQLException {
        UserDataAPI request = TestDataFactory.createMarriageRegistrationAPIRequest().build();
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        ApplicationData appData = dbSteps.getApplicationData(applid);

        assertThat(appData).as("Заявка не найдена в БД").isNotNull();

        Assertions.assertAll("Проверка целостность данных в БД",
                () -> assertThat(dbSteps.isRecordExists("applicants", "applicantid", appData.applicantId))
                        .as("Запись в applicants не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("citizens", "citizenid", appData.citizenId))
                        .as("Запись в citizens не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("merrigecertificates", "citizenid", appData.citizenId))
                        .as("Свидетельство о смерти не найдено").isTrue()
        );
    }

    @Test
    @Story("Регистрация рождения")
    @DisplayName("Проверка создания заявки Регистрация рождения в БД")
    public void testBirthRegistrationCreationInDb() throws SQLException {
        UserDataAPI request = TestDataFactory.createBirthRegistrationAPIRequest().build(); // Исправлено на Birth
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        ApplicationData appData = dbSteps.getApplicationData(applid);

        assertThat(appData).as("Заявка не найдена в БД").isNotNull();

        Assertions.assertAll("Проверка целостность данных в БД",
                () -> assertThat(dbSteps.isRecordExists("applicants", "applicantid", appData.applicantId))
                        .as("Запись в applicants не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("citizens", "citizenid", appData.citizenId))
                        .as("Запись в citizens не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("birthcertificates", "citizenid", appData.citizenId))
                        .as("Свидетельство о смерти не найдено").isTrue()
        );
    }

    @Test
    @Story("Регистрация смерти")
    @DisplayName("Проверка создания заявки Регистрация смерти в БД")
    public void testDeathRegistrationCreationInDb() throws SQLException {

        UserDataAPI request = TestDataFactory.createDeathRegistrationAPIRequest().build();
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        ApplicationData appData = dbSteps.getApplicationData(applid);

        assertThat(appData).as("Заявка не найдена в БД").isNotNull();

        Assertions.assertAll("Проверка целостность данных в БД",
                () -> assertThat(dbSteps.isRecordExists("applicants", "applicantid", appData.applicantId))
                        .as("Запись в applicants не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("citizens", "citizenid", appData.citizenId))
                        .as("Запись в citizens не найдена").isTrue(),

                () -> assertThat(dbSteps.isRecordExists("deathcertificates", "citizenid", appData.citizenId))
                        .as("Свидетельство о смерти не найдено").isTrue()
        );
    }


    @AfterEach
    @Step("Очистка тестовых данных")
    public void cleanUp() throws SQLException {
        dbSteps.cleanUpApplicationData(applid);
    }
}