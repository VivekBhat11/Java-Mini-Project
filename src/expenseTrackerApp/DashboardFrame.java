package expenseTrackerApp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardFrame extends JFrame {
    private User user;

    public DashboardFrame(User user) {
        this.user = user;
        setTitle("Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + user.getUsername());
        welcomeLabel.setBounds(10, 20, 200, 25);
        panel.add(welcomeLabel);

        JButton createExpenseButton = new JButton("Create Expense");
        createExpenseButton.setBounds(10, 60, 150, 25);
        panel.add(createExpenseButton);

        createExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateExpenseFrame(user).setVisible(true);
            }
        });

        JButton viewExpensesButton = new JButton("View Expenses");
        viewExpensesButton.setBounds(10, 100, 150, 25);
        panel.add(viewExpensesButton);

        viewExpensesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewExpensesFrame(user).setVisible(true);
            }
        });

        JButton updateExpenseButton = new JButton("Update Expense");
        updateExpenseButton.setBounds(10, 140, 150, 25);
        panel.add(updateExpenseButton);

        updateExpenseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UpdateExpenseFrame(user).setVisible(true);
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(10, 180, 150, 25);
        panel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });

        add(panel);
    }
}
