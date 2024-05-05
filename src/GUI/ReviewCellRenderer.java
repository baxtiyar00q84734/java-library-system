package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ReviewCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (value != null && !value.toString().isEmpty()) {
            cellComponent.setForeground(Color.BLUE);
            cellComponent.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
        return cellComponent;
    }
}
