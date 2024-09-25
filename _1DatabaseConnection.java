package PayEase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class _1DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/PayeaseDB";
    private static final String USER = "root";
    private static final String PASSWORD = "1998"; 

    // Establish a connection to the database
    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Close the database connection
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
