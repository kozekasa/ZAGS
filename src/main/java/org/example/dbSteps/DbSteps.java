package org.example.dbSteps;

import io.qameta.allure.Step;
import org.example.models.ApplicationData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DbSteps {
    private final Connection connection;

    public DbSteps(Connection connection) {
        this.connection = connection;
    }

    @Step("БД: Проверка наличия сотрудника с ID {staffId}")
    public boolean isStaffExists(int staffId) {
        String query = "SELECT 1 FROM reg_office.staff WHERE staffid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, staffId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при проверке наличия сотрудника ID: " + staffId, e);
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
    public boolean isRecordExists(String tableName, String columnName, int id) {
        String query = String.format("SELECT 1 FROM reg_office.%s WHERE %s = ?", tableName, columnName);

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(String.format("Ошибка при проверке наличия записи в таблице [%s] по колонке [%s] с ID [%d]",
                    tableName, columnName, id), e);
        }
    }

    @Step("БД: Получение связанных ID для заявки {appId}")
    public ApplicationData getApplicationData(int appId) {
        String query = "SELECT citizenid, applicantid, kindofapplication FROM reg_office.applications WHERE applicationid = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ApplicationData data = new ApplicationData();
                    data.citizenId = rs.getInt("citizenid");
                    data.applicantId = rs.getInt("applicantid");
                    data.kind = rs.getString("kindofapplication");
                    return data;
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при проверке наличия заявки ID: " + appId, e);
        }
    }


    @Step("БД: Полная очистка данных для заявки {appId}")
    public void cleanUpApplicationData(int appId, String currentKind) throws SQLException {
        if (appId == 0) return;

        int citizenId = 0;
        int applicantId = 0;

        String findIdsQuery = "SELECT citizenid, applicantid FROM reg_office.applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(findIdsQuery)) {
            stmt.setInt(1, appId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    citizenId = rs.getInt("citizenid");
                    applicantId = rs.getInt("applicantid");
                }
            }
        }

        if (citizenId != 0 && currentKind != null) {
            String certificateTable = switch (currentKind.toLowerCase()) {
                case "marriage" -> "merrigecertificates";
                case "birth" -> "birthcertificates";
                case "death" -> "deathcertificates";
                default -> null;
            };

            if (certificateTable != null) {
                deleteByColumn(certificateTable, "citizenid", citizenId);
            }
        }

        deleteByColumn("applications", "applicationid", appId);

        if (citizenId != 0) {
            deleteByColumn("citizens", "citizenid", citizenId);
        }
        if (applicantId != 0) {
            deleteByColumn("applicants", "applicantid", applicantId);
        }
    }

    private void deleteByColumn(String table, String column, int id) throws SQLException {
        String sql = String.format("DELETE FROM reg_office.%s WHERE %s = ?", table, column);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Step("БД: Получение статуса заявки {appId}")
    public String getApplicationStatus(int appId) {
        String query = "SELECT statusofapplication FROM reg_office.applications WHERE applicationid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, appId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("statusofapplication");
                } else {
                    throw new RuntimeException(
                            String.format("Заявка с ID [%d] не найдена в таблице reg_office.applications", appId)
                    );
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Техническая ошибка при получении статуса заявки ID: " + appId, e);
        }
    }
}
