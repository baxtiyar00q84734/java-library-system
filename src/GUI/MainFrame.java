package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private SearchPanel searchPanel;
    private GeneralDatabaseTable generalDatabaseTable;
    private PersonalDatabaseTable personalDatabaseTable;

    public MainFrame() {
        setTitle("Library Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponents();
    }

    private void initComponents() {
        searchPanel = new SearchPanel();
        add(searchPanel, BorderLayout.NORTH);

        generalDatabaseTable = new GeneralDatabaseTable();
        add(generalDatabaseTable, BorderLayout.CENTER);

        personalDatabaseTable = new PersonalDatabaseTable();
        add(personalDatabaseTable, BorderLayout.SOUTH);
    }

    public String getSearchQuery() {
        return searchPanel.getSearchQuery();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame app = new MainFrame();
            app.setVisible(true);
        });
    }
}
