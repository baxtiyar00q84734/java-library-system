import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LibraryManagementView extends JFrame {
    private JTable generalTable;
    private JTable personalTable;

    public LibraryManagementView() {
        setTitle("Library Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create table models with specified columns
        DefaultTableModel generalTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Title", "Author", "Rating", "Reviews", "Status", "Time Spent", "Start Date", "End Date", "User Rating", "User Review"}
        );

        DefaultTableModel personalTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Title", "Author", "Rating", "Reviews", "Status", "Time Spent", "Start Date", "End Date", "User Rating", "User Review"}
        );

        // Create tables with table models
        generalTable = new JTable(generalTableModel);
        personalTable = new JTable(personalTableModel);

        // Add tables to scroll panes
        JScrollPane generalScrollPane = new JScrollPane(generalTable);
        JScrollPane personalScrollPane = new JScrollPane(personalTable);

        // Create JPanel to hold tables
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(generalScrollPane);
        panel.add(personalScrollPane);

        add(panel);
    }

    public DefaultTableModel getGeneralTableModel() {
        return (DefaultTableModel) generalTable.getModel();
    }

    public DefaultTableModel getPersonalTableModel() {
        return (DefaultTableModel) personalTable.getModel();
    }
}
