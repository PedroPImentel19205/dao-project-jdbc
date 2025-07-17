package JDBC;

import java.sql.*;

public class DatabaseOperations {
    /**
     * Tests JDBC connectivity by connecting to the MySQL server and listing all available databases.
     */

    public static void testConnection() {
        // Use try-with-resources to ensure the connection is closed automatically
        try (Connection conn = DriverManager.getConnection(ConnectionConfig.URL, ConnectionConfig.USER, ConnectionConfig.PASSWORD)) {
            System.out.println("Connected to mySQL server sucessfuly.");

        } catch (SQLException e) {
            // Handle any SQL exceptions that occur during the connection
            System.err.println("Connection Failed.");
            e.printStackTrace();
        }
    }
}