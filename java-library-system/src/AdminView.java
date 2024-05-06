import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminView extends JFrame {
    public AdminView() {
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
