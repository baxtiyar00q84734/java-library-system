import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LibraryManagementController {

    private LibraryManagementView view;
    static AccountManager accountManager;

    public LibraryManagementController(LibraryManagementView view) {
        this.view = view;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementView view = new LibraryManagementView();
            LibraryManagementController controller = new LibraryManagementController(view);
            controller.displayData("data/general_database.csv", "data/personal_database.csv");
            view.setVisible(true);
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });


//        SwingUtilities.invokeLater(() -> {
//            MainFrame mainFrame = new MainFrame();
//            mainFrame.setVisible(true);
//        });
//        java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
//
//        java.awt.EventQueue.invokeLater(() -> {
//            new MainFrame().setVisible(true);
//        });
    }

    public void displayData(String generalFilePath, String personalFilePath) {
        loadCSVData(generalFilePath, view.getGeneralTableModel());
        loadCSVData(personalFilePath, view.getPersonalTableModel());
    }

    private void loadCSVData(String filePath, DefaultTableModel tableModel) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to add a new book to the general database
    public void addBook(String[] bookData) {
        DefaultTableModel generalTableModel = view.getGeneralTableModel();
        generalTableModel.addRow(bookData);
    }

    // Method to delete a book from the general database
    public void deleteBook(int rowIndex) {
        DefaultTableModel generalTableModel = view.getGeneralTableModel();
        generalTableModel.removeRow(rowIndex);
    }

    // Method to modify a book in the general database
    public void modifyBook(int rowIndex, String[] newBookData) {
        DefaultTableModel generalTableModel = view.getGeneralTableModel();
        for (int i = 0; i < newBookData.length; i++) {
            generalTableModel.setValueAt(newBookData[i], rowIndex, i);
        }
    }

    public void removeReview(int rowIndex, int columnIndex) {
        DefaultTableModel generalTableModel = view.getGeneralTableModel();
        generalTableModel.setValueAt("", rowIndex, columnIndex);
    }
}
