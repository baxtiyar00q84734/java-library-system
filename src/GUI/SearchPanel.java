package GUI;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {

    private JTextField searchField;

    public SearchPanel() {
        setLayout(new BorderLayout());
        initComponents();
    }

    private void initComponents() {
        searchField = new JTextField();
        add(new JLabel("Search:"), BorderLayout.WEST);
        add(searchField, BorderLayout.CENTER);
    }

    public String getSearchQuery() {
        return searchField.getText();
    }
}
