import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton switchToLoginButton;
    private JRadioButton userRadioButton;
    private JRadioButton adminRadioButton;
    private ButtonGroup registrationTypeGroup;
    private MainFrame mainFrame;
    private AccountManager accountManager;

    public RegistrationPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(5, 2, 5, 5)); // Updated grid layout to accommodate registration type selection

        // Username and password fields
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        add(passwordField);

        // Radio buttons for registration type
        userRadioButton = new JRadioButton("User", true);
        adminRadioButton = new JRadioButton("Admin");
        registrationTypeGroup = new ButtonGroup();
        registrationTypeGroup.add(userRadioButton);
        registrationTypeGroup.add(adminRadioButton);
        add(userRadioButton);
        add(adminRadioButton);

        // Register and back to login buttons
        registerButton = new JButton("Register");
        registerButton.addActionListener(this::performRegistration);
        add(registerButton);

        switchToLoginButton = new JButton("Back to Login");
        switchToLoginButton.addActionListener(e -> mainFrame.switchToLogin());
        add(switchToLoginButton);
    }

    private void performRegistration(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdmin = adminRadioButton.isSelected();

        // Here, you would typically handle saving this info to a secure location.
        // This example just shows a success message.
        if (isAdmin) {
            JOptionPane.showMessageDialog(this, "Admin registration successful for: " + username);
        } else {
            JOptionPane.showMessageDialog(this, "User registration successful for: " + username);
        }
    }
}
