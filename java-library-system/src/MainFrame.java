import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public MainFrame() {
        setTitle("Library Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        LoginPage loginPage = new LoginPage(this);
        RegistrationPage registrationPage = new RegistrationPage(this);

        cardPanel.add(loginPage, "Login");
        cardPanel.add(registrationPage, "Register");

        add(cardPanel);
    }

    public void switchToLogin() {
        cardLayout.show(cardPanel, "Login");
    }

    public void switchToRegistration() {
        cardLayout.show(cardPanel, "Register");
    }}

