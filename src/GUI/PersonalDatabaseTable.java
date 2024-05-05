package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PersonalDatabaseTable extends JScrollPane {

    private JTable personalDatabaseTable;

    public PersonalDatabaseTable() {
        initComponents();
    }

    private void initComponents() {
        String[] personalColumns = {"Title", "Author", "Rating", "Reviews", "Status", "Time spent", "Start Date", "End Date", "User Rating", "User Review"};
        DefaultTableModel personalTableModel = new DefaultTableModel(null, personalColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8 || column == 9;
            }
        };
        personalDatabaseTable = new JTable(personalTableModel);
        personalDatabaseTable.setAutoCreateRowSorter(true);

        try (BufferedReader br = new BufferedReader(new FileReader("data/personal_database.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                personalTableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setViewportView(personalDatabaseTable);
    }
}
