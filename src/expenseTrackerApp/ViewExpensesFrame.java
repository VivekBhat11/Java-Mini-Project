package expenseTrackerApp;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ViewExpensesFrame extends JFrame {
    public ViewExpensesFrame(User user) {
        setTitle("View Expenses");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        List<Expense> expenses = ExpenseManager.getExpenses(user.getId());
        String[] columnNames = {"ID", "User ID", "Amount", "Date", "Category", "Description"};
        String[][] data = new String[expenses.size()][6];
        for (int i = 0; i < expenses.size(); i++) {
            Expense expense = expenses.get(i);
            data[i][0] = String.valueOf(expense.getId());
            data[i][1] = String.valueOf(expense.getUserId());
            data[i][2] = String.valueOf(expense.getAmount());
            data[i][3] = expense.getDate();
            data[i][4] = expense.getCategory();
            data[i][5] = expense.getDescription();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        add(scrollPane, BorderLayout.CENTER);
    }
}
