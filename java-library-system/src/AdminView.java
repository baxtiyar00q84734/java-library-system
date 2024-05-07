import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminView extends JFrame {
    private AccountManager accountManager;
    public AdminView(AccountManager accountManager) {
        setTitle("Admin Dashboard");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        // Layout setup
        setLayout(new BorderLayout());

        // Menu for different admin tasks
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Admin Tasks");
        JMenuItem manageUsers = new JMenuItem("Manage Users");
        JMenuItem generateReport = new JMenuItem("Generate Report");
        JMenuItem settings = new JMenuItem("Settings");

        manageUsers.addActionListener(this::manageUsers);
        generateReport.addActionListener(this::generateReport);
        settings.addActionListener(this::openSettings);

        menu.add(manageUsers);
        menu.add(generateReport);
        menu.add(settings);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Welcome label
        JLabel welcomeLabel = new JLabel("Welcome, Admin!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);

        // Button to delete user account
        JButton deleteUserButton = new JButton("Delete User Account");
        deleteUserButton.addActionListener(this::deleteUserAccount);
        add(deleteUserButton, BorderLayout.SOUTH);
    }

    // Event handler for deleting a user account
    private void deleteUserAccount(ActionEvent e) {
        String username = JOptionPane.showInputDialog(this, "Enter username to delete:");
        if (username != null && !username.isEmpty()) {
            accountManager.deleteUserAccount(username);
            JOptionPane.showMessageDialog(this, "User account deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Event handlers for menu items
    private void manageUsers(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "User Management Panel");
    }

    private void generateReport(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Report Generation Panel");
    }

    private void openSettings(ActionEvent e) {
        JOptionPane.showMessageDialog(this, "Settings Panel");
    }


}
