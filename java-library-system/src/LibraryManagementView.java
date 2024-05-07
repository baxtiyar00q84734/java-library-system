import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.RowFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class LibraryManagementView extends JFrame {
    private JTable generalTable;
    private JTable personalTable;
    private JTextField searchField;

    // Track the number of clicks on each column header
    private int[] generalColumnClicks;
    private int[] personalColumnClicks;

    public LibraryManagementView() {
        setTitle("Library Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DefaultTableModel generalTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Title", "Author", "Rating", "Reviews", "Status", "Time Spent", "Start Date", "End Date", "User Rating", "User Review"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8 || column == 9; // Allow editing only for User Rating and User Review columns
            }
        };

        DefaultTableModel personalTableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Title", "Author", "Rating", "Reviews", "Status", "Time Spent", "Start Date", "End Date", "User Rating", "User Review"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 8 || column == 9; // Allow editing only for User Rating and User Review columns
            }
        };

        generalTable = new JTable(generalTableModel);
        personalTable = new JTable(personalTableModel);

        // Enable sorting
        TableRowSorter<DefaultTableModel> generalSorter = new TableRowSorter<>(generalTableModel);
        TableRowSorter<DefaultTableModel> personalSorter = new TableRowSorter<>(personalTableModel);
        generalTable.setRowSorter(generalSorter);
        personalTable.setRowSorter(personalSorter);

        JScrollPane generalScrollPane = new JScrollPane(generalTable);
        JScrollPane personalScrollPane = new JScrollPane(personalTable);

        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.add(generalScrollPane);
        panel.add(personalScrollPane);

        JPanel searchPanel = createSearchPanel(generalSorter, personalSorter);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(searchPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Initialize column click counters
        generalColumnClicks = new int[generalTableModel.getColumnCount()];
        personalColumnClicks = new int[personalTableModel.getColumnCount()];

        // Add mouse listener to handle column header clicks
        generalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = generalTable.columnAtPoint(e.getPoint());
                generalColumnClicks[column]++;
                int clickCount = generalColumnClicks[column];
                sortTable(generalTable, generalSorter, column, clickCount);
            }
        });

        personalTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = personalTable.columnAtPoint(e.getPoint());
                personalColumnClicks[column]++;
                int clickCount = personalColumnClicks[column];
                sortTable(personalTable, personalSorter, column, clickCount);
            }
        });

        // Add mouse listener to handle cell editing
        generalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = generalTable.rowAtPoint(e.getPoint());
                int col = generalTable.columnAtPoint(e.getPoint());
                if (col == 8 || col == 9) { // User Rating or User Review column
                    editTableCell(generalTableModel, row, col);
                }
            }
        });

        personalTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = personalTable.rowAtPoint(e.getPoint());
                int col = personalTable.columnAtPoint(e.getPoint());
                if (col == 8 || col == 9) { // User Rating or User Review column
                    editTableCell(personalTableModel, row, col);
                }
            }
        });
    }

    private void editTableCell(DefaultTableModel model, int row, int col) {
        String currentValue = (String) model.getValueAt(row, col);
        String newValue = JOptionPane.showInputDialog("Enter new value:", currentValue);
        if (newValue != null) {
            model.setValueAt(newValue, row, col);
        }
    }

    private JPanel createSearchPanel(TableRowSorter<DefaultTableModel> generalSorter, TableRowSorter<DefaultTableModel> personalSorter) {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setPreferredSize(new Dimension(200, 30));
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                performSearch(generalSorter, personalSorter);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                performSearch(generalSorter, personalSorter);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                performSearch(generalSorter, personalSorter);
            }
        });
        searchPanel.add(searchField, BorderLayout.CENTER);
        return searchPanel;
    }

    private void performSearch(TableRowSorter<DefaultTableModel> generalSorter, TableRowSorter<DefaultTableModel> personalSorter) {
        String text = searchField.getText();
        RowFilter<DefaultTableModel, Object> generalFilter = RowFilter.regexFilter("(?i)" + text);
        RowFilter<DefaultTableModel, Object> personalFilter = RowFilter.regexFilter("(?i)" + text);
        List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<>();
        filters.add(generalFilter);
        filters.add(personalFilter);
        RowFilter<DefaultTableModel, Object> compoundFilter = RowFilter.orFilter(filters);
        if (text.length() == 0) {
            generalSorter.setRowFilter(null);
            personalSorter.setRowFilter(null);
        } else {
            generalSorter.setRowFilter(compoundFilter);
            personalSorter.setRowFilter(compoundFilter);
        }
    }

    private void sortTable(JTable table, TableRowSorter<DefaultTableModel> sorter, int column, int clickCount) {
        List<RowSorter.SortKey> sortKeys = new ArrayList<>(sorter.getSortKeys());
        // Find existing sort key for this column
        int index = findSortKeyIndex(sortKeys, column);
        if (index != -1) {
            sortKeys.remove(index);
        }
        // Add new sort key based on click count
        if (clickCount % 3 == 1) {
            sortKeys.add(new RowSorter.SortKey(column, SortOrder.ASCENDING));
        } else if (clickCount % 3 == 2) {
            sortKeys.add(new RowSorter.SortKey(column, SortOrder.DESCENDING));
        }
        sorter.setSortKeys(sortKeys);
    }

    private int findSortKeyIndex(List<RowSorter.SortKey> sortKeys, int column) {
        for (int i = 0; i < sortKeys.size(); i++) {
            if (sortKeys.get(i).getColumn() == column) {
                return i;
            }
        }
        return -1;
    }

    public DefaultTableModel getGeneralTableModel() {
        return (DefaultTableModel) generalTable.getModel();
    }

    public DefaultTableModel getPersonalTableModel() {
        return (DefaultTableModel) personalTable.getModel();
    }

    // Method to add a book to the general table
    public void addBookToGeneralTable(String[] bookData) {
        DefaultTableModel generalTableModel = (DefaultTableModel) generalTable.getModel();
        generalTableModel.addRow(bookData);
    }

    // Method to remove a book from the general table
    public void removeBookFromGeneralTable(int rowIndex) {
        DefaultTableModel generalTableModel = (DefaultTableModel) generalTable.getModel();
        generalTableModel.removeRow(rowIndex);
    }

    // Method to modify a book in the general table
    public void modifyBookInGeneralTable(int rowIndex, String[] newBookData) {
        DefaultTableModel generalTableModel = (DefaultTableModel) generalTable.getModel();
        for (int i = 0; i < newBookData.length; i++) {
            generalTableModel.setValueAt(newBookData[i], rowIndex, i);
        }
    }

    // Method to remove a review from the general table
    public void removeReviewFromGeneralTable(int rowIndex, int columnIndex) {
        DefaultTableModel generalTableModel = (DefaultTableModel) generalTable.getModel();
        generalTableModel.setValueAt("", rowIndex, columnIndex);
    }
}
