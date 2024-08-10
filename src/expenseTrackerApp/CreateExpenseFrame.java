package expenseTrackerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateExpenseFrame extends JFrame {
    private JTextField amountField;
    private JTextField dateField;
    private JTextField categoryField;
    private JTextArea descriptionArea;
    private User user;

    public CreateExpenseFrame(User user) {
        this.user = user;
        setTitle("Create Expense");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(10, 20, 80, 25);
        panel.add(amountLabel);

        amountField = new JTextField(20);
        amountField.setBounds(100, 20, 165, 25);
        panel.add(amountField);

        JLabel dateLabel = new JLabel("Date (YYYY-MM-DD):");
        dateLabel.setBounds(10, 60, 150, 25);
        panel.add(dateLabel);

        dateField = new JTextField(20);
        dateField.setBounds(160, 60, 105, 25);
        panel.add(dateField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setBounds(10, 100, 80, 25);
        panel.add(categoryLabel);

        categoryField = new JTextField(20);
        categoryField.setBounds(100, 100, 165, 25);
        panel.add(categoryField);

        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setBounds(10, 140, 80, 25);
        panel.add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setBounds(100, 140, 165, 100);
        panel.add(descriptionArea);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(10, 260, 80, 25);
        panel.add(saveButton);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                String date = dateField.getText();
                String category = categoryField.getText();
                String description = descriptionArea.getText();

                Expense expense = new Expense();
                expense.setUserId(user.getId());
                expense.setAmount(amount);
                expense.setDate(date);
                expense.setCategory(category);
                expense.setDescription(description);

                if (ExpenseManager.createExpense(expense)) {
                    JOptionPane.showMessageDialog(null, "Expense added successfully");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add expense");
                }
            }
        });

        add(panel);
    }
}
