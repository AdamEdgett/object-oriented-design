package gui;

import gradebook.Assignment;
import gradebook.MyGradeBook;
import gradebook.Student;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * ************(RUN THIS CLASS TO RUN GUI)
 * This is THE MAIN GUI CLASS. All other GUI instances
 * originate from here. This is the only class with a main method.
 * Represents the GUI as a whole 
 * @author Andrew Krischer
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2014-4-10
 *
 */
public class GUIFrame extends JFrame {
    private MyGradeBook gradebook; // gradebook state GUI holds
    private GUIFrame thisGUIFrame = this; // used to refer back to this GUI
    private JTable studentGradeTable; // the main JTable

    //    GUIFrame(MyGradeBook gb) {
    //        gradebook = gb;
    //        createAndShowGUI();
    //    }

    /**
     * Used to create a new (empty) Gradebook
     */
    void resetGradebook() {
        createAndShowGUI();
    }

    /**
     * Is this Gradebook empty?
     * @return boolean
     */
    boolean isGBEmpty() {
        boolean isAEmpty = gradebook.getAssignments().size() == 0;
        boolean isSEmpty = gradebook.getStudents().size() == 0;

        return isAEmpty && isSEmpty;
    }

    // GUIFrame accessor methods

    /**
     * Gets this frame's table (to most likely manipulate/use its model)
     * @return JTable - the main table
     */
    JTable getTable() {
        return this.studentGradeTable;
    }

    /**
     * Returns the instance of a gradebook stored
     * @return MyGradeBook - instance stored
     */
    MyGradeBook getGradeBook() {
        return gradebook;
    }

    /**
     * Sets the gradebook
     * @param gradeBook the gradebook to set
     */
    void setGradeBook(MyGradeBook gradeBook) {
        this.gradebook = gradeBook;
    }

    /**
     * Create the MenuBar for our GUI
     * Has | File | Edit | Stats |
     * @return JMenuBar - the menu bar
     */
    public JMenuBar createMenuBar() {
        JMenuItem menuItem;
        JMenuBar menuBar;
        JMenu menu;

        // Create the menu bar
        menuBar = new JMenuBar();

        // Build the first menu (file)
        String str = "Here you can create, edit, and save Gradebooks.";
        menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_F);
        menu.getAccessibleContext().setAccessibleDescription(str);
        menuBar.add(menu);
        createFileMenu(menu);

        // Builds the second menu (edit)
        str = "Here you can add students or assignments, or add a file.";
        menu = new JMenu("Edit");
        menu.setMnemonic(KeyEvent.VK_E);
        menu.getAccessibleContext().setAccessibleDescription(str);
        menuBar.add(menu);
        createEditMenu(menu);

        // Builds the third menu (stats)
        str = "Here you can find various statistics available "
                + "in the Gradebook.";
        menu = new JMenu("Stats");
        menu.setMnemonic(KeyEvent.VK_S);
        menu.getAccessibleContext().setAccessibleDescription(str);
        menuBar.add(menu);

        // Stats Menu items

        // Calculate Statistics
        str = "Calculate various statistics about students' grades.";
        menuItem = new JMenuItem("Calculate Statistics",
                KeyEvent.VK_S);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to Stats menu
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIStatistics.createAndShowGUI(thisGUIFrame);
            }
        });

        return menuBar;
    }

    /**
     * Creates the file menu
     * @param menu the menu to add to
     */
    private void createFileMenu(JMenu menu) {
        // File Menu items

        // New empty Gradebook
        String str = "Creates a new empty Gradebook.";
        JMenuItem menuItem = new JMenuItem("New empty Gradebook",
                KeyEvent.VK_N);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to File menu
        menuItem.addActionListener(new NewEmptyGBListener());

        // Open...
        str = "Opens an existing Gradebook.";
        menuItem = new JMenuItem("Open...",
                KeyEvent.VK_O);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to File menu
        // open file
        menuItem.addActionListener(new GUIFileChooser("open", thisGUIFrame));

        // New Gradebook from String
        str = "Creates a new Gradebook from a string";
        menuItem = new JMenuItem("New Gradebook from string",
                KeyEvent.VK_G);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to File menu
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUINewGradeBookFromString.createAndShowGUI(new GUIFrame());
            }
        });

        // Save as...
        str = "Save this Gradebook file";
        menuItem = new JMenuItem("Save as...",
                KeyEvent.VK_S);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to File menu
        //save file
        menuItem.addActionListener(new GUIFileChooser("save", thisGUIFrame));
    }

    /**
     * Creates the edit menu
     * @param menu the menu to add to
     */
    private void createEditMenu(JMenu menu) {
        // Edit Menu items

        // Add Student
        String str = "Adds a new student row to the Gradebook.";
        JMenuItem menuItem = new JMenuItem("Add Student",
                KeyEvent.VK_S);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to Edit menu
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIAddStudent.createAndShowGUI(thisGUIFrame);
            }
        });

        // Add Assignment
        str = "Adds a new assignment column to the Gradebook.";
        menuItem = new JMenuItem("Add Assignment",
                KeyEvent.VK_A);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to Edit menu
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GUIAddAssignment.createAndShowGUI(thisGUIFrame);
            }
        });

        // Add file to Gradebook
        str = "Adds a file to the data in this Gradebook.";
        menuItem = new JMenuItem("Add file to Gradebook",
                KeyEvent.VK_G);
        menuItem.getAccessibleContext().setAccessibleDescription(str);
        menu.add(menuItem); // Adds to Edit menu
    }

    /**
     * Creates the content pane for our frame, namely put the table
     * on this frame.
     * @return Container - The container that contains our main table
     */
    Container createContentPane() {
        // Create content-pane-to-be
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        //Create a table
        @SuppressWarnings("serial")
        DefaultTableModel table = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return (column != 0) || (column != 5);
            }
        };

        ArrayList<String> columnNames =
                new ArrayList<String>(Arrays.asList("Username", "First Name",
                        "Last Name", "Advisor", "Year Grad.", "Current Grade"));
        for (String columnName : columnNames) {
            table.addColumn(columnName);
        }
        studentGradeTable = new JTable(table);

        studentGradeTable.getModel()
                .addTableModelListener(new TableChanged(thisGUIFrame));

        addStudentsToTable(studentGradeTable);

        JScrollPane tableContainer = new JScrollPane(studentGradeTable);

        //Add table to content pane.
        contentPane.add(tableContainer, BorderLayout.CENTER);

        return contentPane;
    }


    /**
     * Makes the table talk with the GradeBook so that the table
     * is effectively updated
     * @param table - Jtable to update (studentGradeTable)
     */
    void addStudentsToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        ArrayList<Student> studs = gradebook.getStudents();
        ArrayList<Assignment> ass = gradebook.getAssignments();

        ArrayList<String> aList = new ArrayList<String>();

        for (Assignment a : ass) {
            model.addColumn(a.getName());
            
            aList.add(a.getName());
            
        }

        for (int i = 0; i < studs.size(); i++) {
            Student s = studs.get(i);

            ArrayList<Object> oArray = new ArrayList<Object>(
                    Arrays.asList(s.getUsername(), s.getFirstName(),
                    s.getLastName(), s.getAdvisor(), s.getGradYear(),
                    null));

            // now add each assignment into the object array
            for (int a = 0; a < aList.size(); a++) {
                double grade = gradebook.assignmentGrade(
                        aList.get(a), s.getUsername());
                oArray.add(grade);
            }
            
            model.addRow(oArray.toArray());
        }
    }
    
    
    // ActionEvent Listeners

    /**
     * Listens for the action to create a new (empty) gradebook
     * @author Andrew Krischer
     * @version 2014-4-10
     *
     */
    class NewEmptyGBListener implements ActionListener {

        /**
         * Implemented method to perform an action, namely
         * create a new empty gradebook
         * @param e the action event
         */
        public void actionPerformed(ActionEvent e) {
            resetGradebook();
        }
    }


    /**
     * Create the GUI and show it. For thread safety, this method should
     * be invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window
        JFrame frame = new JFrame("Gradebook");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        GUIFrame guiFrame = new GUIFrame();
        guiFrame.gradebook = MyGradeBook.initialize();

        // set JMenuBar
        frame.setJMenuBar(guiFrame.createMenuBar());

        // set ContentPane
        frame.setContentPane(guiFrame.createContentPane());

        //Display the window
        frame.setSize(480, 400);
        frame.setVisible(true);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should
     * be invoked from the event-dispatching thread.
     * @param gb the gradebook to initialize with
     */
    static void createAndShowGUI(MyGradeBook gb) {
        //Create and set up the window
        JFrame frame = new JFrame("Gradebook");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Create and set up the content pane.
        GUIFrame guiFrame = new GUIFrame();
        guiFrame.setGradeBook(gb);

        // set JMenuBar
        frame.setJMenuBar(guiFrame.createMenuBar());

        // set ContentPane
        frame.setContentPane(guiFrame.createContentPane());

        //Display the window
        frame.setSize(480, 400);
        frame.setVisible(true);
    }

    /**
     * The main method which runs this whole shindig
     * @param args - irrelevent
     */
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
