package org.example.dbSteps;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DbSteps {
    private final Connection connection;

    public DbSteps(Connection connection) {
        this.connection = connection;
    }

    @Step("БД: Проверка наличия сотрудника с ID {staffId}")
    public boolean isStaffExists(int staffId) throws SQLException {
        String query = "SELECT 1 FROM reg_office.staff WHERE staffid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Step("БД: Удаление сотрудника с ID {staffId}")
    public void deleteStaff(int staffId) throws SQLException {
        String query = "DELETE FROM reg_office.staff WHERE staffid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            stmt.executeUpdate();
        }
    }

    @Step("БД: Проверка наличия записи в таблице {tableName}")
    public boolean isRecordExists(String tableName, String columnName, int id) throws SQLException {
        String query = String.format("SELECT 1 FROM reg_office.%s WHERE %s = ?", tableName, columnName);
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    @Step("БД: Комплексная проверка заявки {appId}")
    public void verifyApplicationIntegrity(int appId, String certificateTable) throws SQLException {
        String appQuery = "SELECT citizenid, applicantid FROM reg_office.applications WHERE applicationid = ?";

        int citizenId;
        int applicantId;

        try (PreparedStatement stmt = connection.prepareStatement(appQuery)) {
            stmt.setInt(1, appId);
            ResultSet rs = stmt.executeQuery();
            assertThat(rs.next()).as("Заявка " + appId + " не найдена").isTrue();

            citizenId = rs.getInt("citizenid");
            applicantId = rs.getInt("applicantid");
        }

        Assertions.assertAll("Целостность данных в БД для заявки " + appId,
                () -> assertThat(isRecordExists("applicants", "applicantid", applicantId))
                        .as("Запись в applicants не найдена").isTrue(),
                () -> assertThat(isRecordExists("citizens", "citizenid", citizenId))
                        .as("Запись в citizens не найдена").isTrue(),
                () -> assertThat(isRecordExists(certificateTable, "citizenid", citizenId))
                        .as("Запись в " + certificateTable + " не найдена").isTrue()
        );
    }
}