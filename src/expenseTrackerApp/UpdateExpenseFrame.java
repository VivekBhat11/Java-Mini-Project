package expenseTrackerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateExpenseFrame extends JFrame {
    private JComboBox<String> expenseDropdown;
    private JTextField amountField;
    private JTextField dateField;
    private JTextField categoryField;
    private JTextArea descriptionArea;
    private User user;
    private List<Expense> expenses;

    public UpdateExpenseFrame(User user) {
        this.user = user;
        setTitle("Update Expense");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel selectExpenseLabel = new JLabel("Select Expense:");
        selectExpenseLabel.setBounds(10, 20, 100, 25);
        panel.add(selectExpenseLabel);

        expenses = ExpenseManager.getExpenses(user.getId());
        String[] expenseOptions = new String[expenses.size()];
        for (int i = 0; i < expenses.size(); i++) {
            expenseOptions[i] = "Expense ID: " + expenses.get(i).getId() + ", Amount: " + expenses.get(i).getAmount();
        }

        expenseDropdown = new JComboBox<>(expenseOptions);
        expenseDropdown.setBounds(120, 20, 250, 25);
        panel.add(expenseDropdown);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 60, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(100, 60, 165, 25);
        panel.add(amountField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(10, 100, 150, 25);
        panel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(160, 100, 105, 25);
        panel.add(dateField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 140, 80, 25);
        panel.add(categoryLabel);

        categoryField = new JTextField(20);
        categoryField.setBounds(100, 140, 165, 25);
        panel.add(categoryField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 180, 80, 25);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 180, 165, 100);
        panel.add(descriptionArea);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(10, 300, 80, 25);
        panel.add(updateButton);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedExpenseIndex = expenseDropdown.getSelectedIndex();
                Expense selectedExpense = expenses.get(selectedExpenseIndex);

                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                String category = categoryField.getText();
                String description = descriptionArea.getText();

                selectedExpense.setAmount(amount);
                selectedExpense.setDate(date);
                selectedExpense.setCategory(category);
                selectedExpense.setDescription(description);

                if (ExpenseManager.updateExpense(selectedExpense)) {
                    JOptionPane.showMessageDialog(null, "Expense updated successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update expense");
                }
            }
        });

        add(panel);
    }
}
