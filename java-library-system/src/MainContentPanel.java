import javax.swing.*;
import java.awt.*;

public class MainContentPanel extends JPanel {
    public MainContentPanel() {
        setLayout(new BorderLayout());
        JLabel welcomeLabel = new JLabel("Welcome to the Main Application!", JLabel.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }
}

