import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class RegistrationPage extends JPanel {
    static List<String> usernames = new ArrayList<>();
    static List<String> passwords = new ArrayList<>();
    static List<Boolean> isAdmins = new ArrayList<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton switchToLoginButton;
    private MainFrame mainFrame;
    private JCheckBox adminCheckBox;

    public RegistrationPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(4, 2, 5, 5));  // Adjust layout for additional checkbox

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        add(passwordField);

        adminCheckBox = new JCheckBox("Register as admin");
        add(adminCheckBox);

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
        boolean isAdmin = adminCheckBox.isSelected();  // Obtain the admin status from the checkbox

        if (!usernames.contains(username)) {
            usernames.add(username);
            passwords.add(password);
            isAdmins.add(isAdmin);  // Store the admin status

            JOptionPane.showMessageDialog(this, "Registration successful for: " + username + (isAdmin ? " as Admin." : " as User."));
            mainFrame.switchToLogin();  // Switch back to the login screen
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}