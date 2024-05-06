import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchToRegisterButton;
    private MainFrame mainFrame;
    private JRadioButton userRadioButton;
    private JRadioButton adminRadioButton;
    private ButtonGroup loginTypeGroup;


    public LoginPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(4, 2, 5, 5));  // Adjust grid layout accordingly

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        add(passwordField);

        userRadioButton = new JRadioButton("User", true);
        adminRadioButton = new JRadioButton("Admin");
        loginTypeGroup = new ButtonGroup();
        loginTypeGroup.add(userRadioButton);
        loginTypeGroup.add(adminRadioButton);
        add(userRadioButton);
        add(adminRadioButton);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this::performLogin);
        add(loginButton);

        switchToRegisterButton = new JButton("Register");
        switchToRegisterButton.addActionListener(e -> mainFrame.switchToRegistration());
        add(switchToRegisterButton);
    }

    private void performLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdminLogin = adminRadioButton.isSelected();

        int userIndex = RegistrationPage.usernames.indexOf(username);
        if (userIndex != -1 && RegistrationPage.passwords.get(userIndex).equals(password)) {
            boolean isAdmin = RegistrationPage.isAdmins.get(userIndex);  // Get the stored admin status
            if (isAdminLogin && isAdmin) {
                // Admin login successful
                JOptionPane.showMessageDialog(this, "Admin login successful!");
                mainFrame.dispose();
                launchAdminView();  // Launch admin-specific view
            } else if (!isAdminLogin && !isAdmin) {
                // User login successful
                JOptionPane.showMessageDialog(this, "User login successful!");
                mainFrame.dispose();
                launchLibraryManagementSystem();  // Launch user-specific view (regular library management system)
            } else {
                // Incorrect role selected
                JOptionPane.showMessageDialog(this, "Login failed! Incorrect role selected.", "Login Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Login failed! Username or password incorrect.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void launchAdminView() {
        SwingUtilities.invokeLater(() -> {
            AdminView adminView = new AdminView();  // Assuming you have an AdminView class
            adminView.setVisible(true);
        });
    }

    private void launchLibraryManagementSystem() {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementView libraryView = new LibraryManagementView();
            LibraryManagementController libraryController = new LibraryManagementController(libraryView);
            libraryController.displayData("data/general_database.csv", "data/personal_database.csv");
            libraryView.setVisible(true);
        });
    }
}
