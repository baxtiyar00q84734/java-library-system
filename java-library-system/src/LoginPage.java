import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchToRegisterButton;
    private MainFrame mainFrame;

    public LoginPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(3, 2, 5, 5));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        add(passwordField);

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
        int userIndex = RegistrationPage.usernames.indexOf(username);
        if (userIndex != -1 && RegistrationPage.passwords.get(userIndex).equals(password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            mainFrame.dispose();  // Close the login/registration frame
            launchLibraryManagementSystem();  // Launch the main library system interface
        } else {
            JOptionPane.showMessageDialog(this, "Login failed!", "Error", JOptionPane.ERROR_MESSAGE);
        }
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