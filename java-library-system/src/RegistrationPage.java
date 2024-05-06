import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RegistrationPage extends JPanel {
    static List<String> usernames = new ArrayList<>();
    static List<String> passwords = new ArrayList<>();
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton switchToLoginButton;
    private MainFrame mainFrame;

    public RegistrationPage(MainFrame mainFrame) {
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

        if (!usernames.contains(username)) {
            usernames.add(username);
            passwords.add(password);
            JOptionPane.showMessageDialog(this, "Registration successful for: " + username);
            mainFrame.switchToLogin();
        } else {
            JOptionPane.showMessageDialog(this, "Username already exists!", "Registration Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}