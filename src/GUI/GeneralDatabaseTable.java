package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeneralDatabaseTable extends JScrollPane {

    private JTable generalDatabaseTable;

    public GeneralDatabaseTable() {
        initComponents();
    }

    private void initComponents() {
        String[] generalColumns = {"Title", "Author", "Rating", "Reviews", "Status", "Time spent", "Start Date", "End Date", "User Rating", "User Review"};
        DefaultTableModel generalTableModel = new DefaultTableModel(null, generalColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        generalDatabaseTable = new JTable(generalTableModel);
        generalDatabaseTable.setAutoCreateRowSorter(true);

        try (BufferedReader br = new BufferedReader(new FileReader("data/general_database.csv"))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                generalTableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        setViewportView(generalDatabaseTable);
    }
}

