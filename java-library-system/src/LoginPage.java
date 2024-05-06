import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton switchToRegisterButton;
    private JRadioButton userRadioButton;
    private JRadioButton adminRadioButton;
    private ButtonGroup loginTypeGroup;
    private MainFrame mainFrame;
    private AccountManager accountManager;

    public LoginPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new GridLayout(4, 2, 5, 5)); // Updated grid layout to accommodate login type selection

        // Username and password fields
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(200, 25));
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 25));
        add(passwordField);

        // Radio buttons for login type
        userRadioButton = new JRadioButton("User", true);
        adminRadioButton = new JRadioButton("Admin");
        loginTypeGroup = new ButtonGroup();
        loginTypeGroup.add(userRadioButton);
        loginTypeGroup.add(adminRadioButton);
        add(userRadioButton);
        add(adminRadioButton);

        // Login and register buttons
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
        boolean isAdmin = adminRadioButton.isSelected();
        
        // Here you should validate the username and password
        // You might want to fetch these credentials from a database or some other secure source
        // For now, let's assume we have predefined credentials
        if (isAdmin) {
            if ("admin".equals(username) && "adminpass".equals(password)) {
                JOptionPane.showMessageDialog(this, "Admin login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Admin login failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if ("user".equals(username) && "userpass".equals(password)) {
                JOptionPane.showMessageDialog(this, "User login successful!");
            } else {
                JOptionPane.showMessageDialog(this, "User login failed!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
