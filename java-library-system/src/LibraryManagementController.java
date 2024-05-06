import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LibraryManagementController {

    private LibraryManagementView view;

    public LibraryManagementController(LibraryManagementView view) {
        this.view = view;
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryManagementView view = new LibraryManagementView();
            LibraryManagementController controller = new LibraryManagementController(view);
            controller.displayData("data/general_database.csv", "data/personal_database.csv");
            view.setVisible(true);
        });
    }
}
