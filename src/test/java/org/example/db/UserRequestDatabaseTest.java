package org.example.db;

import io.qameta.allure.*;
import org.example.ApiPreconditions;
import org.example.baseTests.BaseDbTest;
import org.example.dataFactory.TestDataFactory;
import org.example.models.UserDataAPI;
import org.junit.jupiter.api.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@Epic("DB")
@Feature("Регистрация заявки")
public class UserRequestDatabaseTest extends BaseDbTest {

    private int applid;

    @Test
    @Story("Регистрация брака")
    @DisplayName("Проверка создания заявки Регистрация брака в БД")
    public void testMarriageRegistrationCreationInDb() throws SQLException {
        UserDataAPI request = TestDataFactory.createMarriageRegistrationAPIRequest().build();
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        dbSteps.verifyApplicationIntegrity(applid, "merrigecertificates");
    }

    @Test
    @Story("Регистрация рождения")
    @DisplayName("Проверка создания заявки Регистрация рождения в БД")
    public void testBirthRegistrationCreationInDb() throws SQLException {
        UserDataAPI request = TestDataFactory.createBirthRegistrationAPIRequest().build(); // Исправлено на Birth
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        dbSteps.verifyApplicationIntegrity(applid, "birthcertificates");
    }

    @Test
    @Story("Регистрация смерти")
    @DisplayName("Проверка создания заявки Регистрация смерти в БД")
    public void testDeathRegistrationCreationInDb() throws SQLException {
        UserDataAPI request = TestDataFactory.createDeathRegistrationAPIRequest().build(); // Исправлено на Death
        applid = ApiPreconditions.createApplicationAndGetIntId(request);

        dbSteps.verifyApplicationIntegrity(applid, "deathcertificates");
    }


    @AfterEach
    @Step("Полная очистка связанных тестовых данных в БД")
    public void cleanUpAllData() throws SQLException {
        if (applid == 0) return;

        int citizenId = 0;
        int applicantId = 0;
        String kind = "";

        String findIdsQuery = "SELECT citizenid, applicantid, kindofapplication FROM reg_office.applications WHERE applicationid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(findIdsQuery)) {
            stmt.setInt(1, applid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                citizenId = rs.getInt("citizenid");
                applicantId = rs.getInt("applicantid");
                kind = rs.getString("kindofapplication");
            }
        }

        try {
            if (citizenId != 0) {
                deleteFromTableByColumn("merrigecertificates", "citizenid", citizenId);
                deleteFromTableByColumn("birthcertificates", "citizenid", citizenId);
                deleteFromTableByColumn("deathcertificates", "citizenid", citizenId);
            }

            deleteFromTableByColumn("applications", "applicationid", applid);

            if (citizenId != 0) {
                deleteFromTableByColumn("citizens", "citizenid", citizenId);
            }
            if (applicantId != 0) {
                deleteFromTableByColumn("applicants", "applicantid", applicantId);
            }

            System.out.println("БД успешно очищена для applicationid: " + applid);

        } catch (SQLException e) {
            System.err.println("Ошибка при очистке данных: " + e.getMessage());
            throw e;
        }
    }
}