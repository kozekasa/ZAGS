package org.example.selenium.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    public static Connection getConnection() throws SQLException {
        Connection conn = connectionHolder.get();

        if (conn == null || conn.isClosed()) {
            try {
                String url = WebDriverSingleton.getEnv("DB_URL");
                String user = WebDriverSingleton.getEnv("DB_USER");
                String password = WebDriverSingleton.getEnv("DB_PASSWORD");

                conn = DriverManager.getConnection(url, user, password);
                connectionHolder.set(conn);
            } catch (SQLException e) {
                throw new RuntimeException("Не удалось подключиться к БД: " + e.getMessage());
            }
        }
        return conn;
    }

    public static void closeConnection() {
        Connection conn = connectionHolder.get();
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                connectionHolder.remove();
            }
        }
    }
}