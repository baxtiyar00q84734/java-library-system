package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GeneralDatabaseTable extends JScrollPane {

    private JTable generalDatabaseTable;

    public GeneralDatabaseTable() {
        initComponents(); // Add this line to your constructor
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

        // Enable sorting on "Time spent" column by clicking twice
        generalDatabaseTable.getTableHeader().addMouseListener(new MouseInputAdapter() {
            int clickCount = 0;

            @Override
            public void mouseClicked(MouseEvent e) {
                int column = generalDatabaseTable.columnAtPoint(e.getPoint());
                if (column == 5) { // Check if the clicked column is "Time spent"
                    clickCount++;
                    if (clickCount == 2) {
                        sortTimeSpentDescending();
                        clickCount = 0;
                    }
                } else {
                    clickCount = 0;
                }

                if (column == 4) { // Check if the clicked column is "Status"
                    sortStatusAscending();
                }
            }
        });
    }

    private void sortTimeSpentDescending() {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) generalDatabaseTable.getRowSorter();
        sorter.toggleSortOrder(5); // Sort the "Time spent" column
    }

    private void sortStatusAscending() {
        TableRowSorter<DefaultTableModel> sorter = (TableRowSorter<DefaultTableModel>) generalDatabaseTable.getRowSorter();
        sorter.setSortKeys(null); // Clear any existing sort keys
        sorter.toggleSortOrder(4); // Sort the "Status" column in ascending order
    }
}
