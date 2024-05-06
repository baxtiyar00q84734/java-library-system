import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private MainContentPanel mainContentPanel;

    public MainFrame() {
        super("Account Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        loginPage = new LoginPage(this);
        registrationPage = new RegistrationPage(this);
        mainContentPanel = new MainContentPanel();

        cardPanel.add(loginPage, "Login");
        cardPanel.add(registrationPage, "Register");
        cardPanel.add(mainContentPanel, "MainContent");

        add(cardPanel);
        cardLayout.show(cardPanel, "Login");
    }

    public void switchToRegistration() {
        cardLayout.show(cardPanel, "Register");
    }

    public void switchToLogin() {
        cardLayout.show(cardPanel, "Login");
    }

    public void switchToMainContent() {
        cardLayout.show(cardPanel, "MainContent");
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
    }
}
