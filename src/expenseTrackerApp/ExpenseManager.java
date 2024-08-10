package expenseTrackerApp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseManager {

    // Register a new user
    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO user_accounts (username, password) VALUES (?, ?)";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // User login
    public static User loginUser(String username, String password) {
        String sql = "SELECT * FROM user_accounts WHERE username = ? AND password = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Create a new expense
    public static boolean createExpense(Expense expense) {
        String sql = "INSERT INTO user_expenses (user_id, amount, date, category, description) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, expense.getUserId());
            pstmt.setDouble(2, expense.getAmount());
            pstmt.setDate(3, Date.valueOf(expense.getDate()));
            pstmt.setString(4, expense.getCategory());
            pstmt.setString(5, expense.getDescription());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve expenses for a user
    public static List<Expense> getExpenses(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM user_expenses WHERE user_id = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getDouble("amount"),
                        rs.getDate("date").toString(),
                        rs.getString("category"),
                        rs.getString("description")
                );
                expenses.add(expense);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return expenses;
    }

    // Update an existing expense
    public static boolean updateExpense(Expense expense) {
        String sql = "UPDATE user_expenses SET amount = ?, date = ?, category = ?, description = ? WHERE id = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDouble(1, expense.getAmount());
            pstmt.setDate(2, Date.valueOf(expense.getDate()));
            pstmt.setString(3, expense.getCategory());
            pstmt.setString(4, expense.getDescription());
            pstmt.setInt(5, expense.getId());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an expense
    public static boolean deleteExpense(int expenseId) {
        String sql = "DELETE FROM user_expenses WHERE id = ?";
        try (Connection conn = DBHelper.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, expenseId);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
