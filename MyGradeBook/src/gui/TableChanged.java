package gui;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * This class makes sure changes in the table trigger
 * changes in the Gradebook itself.
 * @author Andrew Krischer
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2014-4-10
 *
 */
public class TableChanged implements TableModelListener {
    private GUIFrame mainFrame;

    /**
     * Constructor for table changed
     * @param mainFrame the frame to initialize with
     */
    TableChanged(GUIFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * updates table when it changes
     * @param e - when something changes with the table
     */
    public void tableChanged(TableModelEvent e) {
        try {
            int row = e.getFirstRow();
            int col = e.getColumn();
            TableModel model = (TableModel) e.getSource();

            if (e.getType() == TableModelEvent.UPDATE) {
                if (col == TableModelEvent.ALL_COLUMNS) {
                    for (int i = 0; i < model.getColumnCount(); i++) {
                        if (row == TableModelEvent.ALL_COLUMNS) {
                            for (int j = 0; j < model.getRowCount(); j++) {
                                updateModel(model, j, i);
                            }
                        }
                        else {
                            updateModel(model, row, i);
                        }
                    }
                }
                else {
                    updateModel(model, row, col);
                }
            }
        }
        catch (IndexOutOfBoundsException exc) {
            DefaultTableModel model =
                    (DefaultTableModel) mainFrame.getTable().getModel();
            
            Object[] rowData = null;
            
            model.addRow(rowData);
        }
    }

    /**
     * Updates the gradebook model with the changes made
     * @param model the model to update
     * @param row the row to update
     * @param col the column to update
     */
    public void updateModel(TableModel model, int row, int col) {
        String columnName = model.getColumnName(col);
        Object data = model.getValueAt(row, col);

        /* Here we do stuff with the data:
         * If columnName = :
         *      **NOTE: Username is not changeable
         *      First Name -> changeStudentFirst(username, newFirst)
         *      Last Name -> changeStudentLast(username, newLast)
         *      Advisor -> changeStudentAdvisor(username, newAdvisor)
         *      Year -> changeStudentYear(username, newYear)
         *      *an assignment* (i.e. if col > 5) ->
         *      changeGrade(columnName, username, newGrade)
         */
        String username = (String) model.getValueAt(row, 0);
        if (columnName.contains("Username")) {
            // Username changes aren't allowed
            return;
        }
        else if (columnName.contains("Current Grade")) {
            // Current grade is a calculated value
            return;
        }
        else if (columnName.contains("First")) {
            if (data == null) {
                data = "";
            }
            mainFrame.getGradeBook()
                    .changeStudentFirstName(username, (String) data);
        }
        else if (columnName.contains("Last")) {
            if (data == null) {
                data = "";
            }
            mainFrame.getGradeBook()
                    .changeStudentLastName(username, (String) data);
        }
        else if (columnName.contains("Advisor")) {
            if (data == null) {
                data = "";
            }
            mainFrame.getGradeBook()
                    .changeStudentAdvisor(username, (String) data);
        }
        else if (columnName.contains("Year")) {
            if (data == null) {
                data = 0;
            }
            try {
                mainFrame.getGradeBook()
                        .changeStudentGradYear(username, (Integer) data);
            }

            catch (NumberFormatException exc) {
                String str = "You must enter a valid year!";
                new GUITextBox(str).createAndShowGUI();
            }
        }
        else {
            if (data == null) {
                data = -1.0;
            }
            if (data instanceof String) {
                data = Double.parseDouble((String) data);
            }
            boolean result = mainFrame.getGradeBook()
                    .changeGrade(columnName, username, (Double) data);
            // No previous grade
            if (!result) {
                mainFrame.getGradeBook().addGradeForStudent(
                        columnName, username, (Double) data
                );
            }
            updateCurrentGrade(model, row);
        }
    }

    /**
     * Recalculates the user's current grade and updates the table
     * @param model
     * @param row
     */
    private void updateCurrentGrade(TableModel model, int row) {
        String username = (String) model.getValueAt(row, 0);
        int col = -1;
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (model.getColumnName(i).contains("Current Grade")) {
                col = i;
                break;
            }
        }
        model.setValueAt(
            mainFrame.getGradeBook().currentGrade(username), row, col
        );
    }
}
