package org.example.selenium.baseTests;

import org.example.dbSteps.DbSteps;
import org.example.selenium.driver.DBManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class BaseDbTest {

    protected Connection connection;
    protected DbSteps dbSteps;

    @BeforeEach
    public void setUpConnection() throws SQLException {
        connection = DBManager.getConnection();
        dbSteps = new DbSteps(connection);
    }

    @AfterEach
    public void tearDownConnection() {
        DBManager.closeConnection();
    }

    protected void deleteFromTableByColumn(String tableName, String columnName, int id) throws SQLException {
        if (id == 0) return;
        String sql = String.format("DELETE FROM reg_office.%s WHERE %s = ?", tableName, columnName);
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}