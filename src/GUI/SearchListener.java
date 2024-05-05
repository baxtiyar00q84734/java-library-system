package GUI;

import GUI.MainFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;


public class SearchListener implements DocumentListener {

    private MainFrame mainFrame;
    private JTable generalTable;
    private JTable personalTable;

    public SearchListener(MainFrame mainFrame, JTable generalTable, JTable personalTable) {
        this.mainFrame = mainFrame;
        this.generalTable = generalTable;
        this.personalTable = personalTable;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        search(mainFrame.getSearchQuery());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        search(mainFrame.getSearchQuery());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        search(mainFrame.getSearchQuery());
    }

    private void search(String query) {
        DefaultTableModel generalModel = (DefaultTableModel) generalTable.getModel();
        TableRowSorter<DefaultTableModel> generalSorter = new TableRowSorter<>(generalModel);
        generalTable.setRowSorter(generalSorter);

        DefaultTableModel personalModel = (DefaultTableModel) personalTable.getModel();
        TableRowSorter<DefaultTableModel> personalSorter = new TableRowSorter<>(personalModel);
        personalTable.setRowSorter(personalSorter);

        if (query.length() == 0) {
            generalSorter.setRowFilter(null);
            personalSorter.setRowFilter(null);
        } else {
            generalSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
            personalSorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        }
    }
}
