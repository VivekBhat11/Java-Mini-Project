package expenseTrackerApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/expense_tracker_app";
    private static final String USER = "root";
    private static final String PASS = ""; // Your MySQL password here

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}
