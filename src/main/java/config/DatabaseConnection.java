package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;

    private static String url = System.getenv("DB_URL");
    private static String user = System.getenv("DB_USER");
    private static String password = System.getenv("DB_PASSWORD");

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection established");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Error establishing the database connection: " + e.getMessage());
        }
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null || instance.connection.isClosed() || instance.connection == null) {
                instance = new DatabaseConnection();
            }

        } catch (SQLException e) {
            System.out.println("MySQL JDBC Driver not found.");
        }

        return instance;
    }

    public static Connection getConnection() {
        return getInstance().connection;
    }
}
