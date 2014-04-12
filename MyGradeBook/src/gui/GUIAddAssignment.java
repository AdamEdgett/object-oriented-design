package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 * Represents the graphical element for adding a new assignment to the
 * table and gradebook (i.e. a new row).
 * @author Andrew Krischer
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 4/11/14
 * @version 2014-4-10
 *
 */
@SuppressWarnings("serial")
public class GUIAddAssignment extends JPanel implements ActionListener {
    private JTextField nameTextField;
    private JTextField pointsTextField;
    private JTextField weightTextField;
    
    private static JFrame frame;

    private String name;
    private Double points;
    private Double weight;

    private GUIFrame mainFrame;
    private GUITextBox box;

    private String str;

    /**
     * Constructor for this graphical element
     * @param mainFrame - the original (main) frame, so that we can manipulate
     *                  its table based on what student we add.
     */
    GUIAddAssignment(GUIFrame mainFrame) {
        JLabel actionLabel;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        // Create text fields
        nameTextField = new JTextField(16);
        pointsTextField = new JTextField(6);
        weightTextField = new JTextField(12);

        // Create labels for the text fields
        JLabel nameLabel = new JLabel("Assignment Name: ");
        nameLabel.setLabelFor(nameTextField);

        JLabel pointsLabel = new JLabel("Total Points: ");
        pointsLabel.setLabelFor(pointsTextField);

        JLabel weightLabel = new JLabel("Weight of total grade: ");
        weightLabel.setLabelFor(weightTextField);

        // Create a label for what to do
        actionLabel = new JLabel("Create a new assignment by filling in the "
                + "text boxes.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

        // Create OK and Cancel buttons
        JButton okButton = new JButton("OK");
        okButton.addActionListener(this);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
            }
        });

        // Lay out controls/labels
        JPanel textPane = new JPanel(new BorderLayout());
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textPane.setLayout(gridbag);

        JLabel[] labels = {nameLabel, pointsLabel, weightLabel};
        JTextField[] textFields = {
            nameTextField, pointsTextField, weightTextField
        };
        addLabelTextRows(labels, textFields, gridbag, textPane);

        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;

        textPane.add(actionLabel, c);
        textPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Text Fields"),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        // put everything together

        textPane.add(nameTextField);
        textPane.add(pointsTextField);
        textPane.add(weightTextField);

        textPane.add(okButton);
        textPane.add(cancelButton);

        add(textPane);
    }


    /**
     * Basically sets up label and the text boxes which create the new student
     * @param labels - array of label (descriptions)
     * @param textFields - array of text fields, representing input
     * @param gridbag - used for manipulating layout
     * @param container - What all elements go on
     */
    private void addLabelTextRows(JLabel[] labels, JTextField[] textFields,
            GridBagLayout gridbag, Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.BELOW_BASELINE;

        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.fill = GridBagConstraints.BOTH;
            c.weightx = 0.0;
            container.add(labels[i], c);

            c.fill = GridBagConstraints.BOTH;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }


    /**
     * Listens for the OK button to be pressed, and tries
     * to create an assignment. If it cannot, an exception is thrown
     * and caught, which creates a new dialog box.
     * @author Andrew Krischer
     * @version 2014-4-10
     *
     */
    /**
     * Implemented method: tries to create a new assignment
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        boolean canContinue = true;
        // Check fields to make sure they're filled
        if (nameTextField.getText() != null
                && pointsTextField.getText() != null
                && weightTextField.getText() != null) {
            try {
                name = nameTextField.getText();
                points = Double.parseDouble(pointsTextField.getText());
                weight = Double.parseDouble(weightTextField.getText());
            }
            
            catch (NumberFormatException exc) {
                str = " Please input a valid number into the text box(es).";
                box = new GUITextBox(str);
                box.createAndShowGUI();
                
                canContinue = false;
            }
        }
        else {
            str = "Field cannot be left empty!";
            box = new GUITextBox(str);
            box.createAndShowGUI();

            canContinue = false;
        }
        
        // Check if assignment name exists already
        if (mainFrame.getGradeBook().getAssignment(name) != null) {
            str = "An assignment with that name has already been made. " +
                    "Please enter a valid name for the assignment.";
            box = new GUITextBox(str);
            box.createAndShowGUI();
            
            canContinue = false;
        }
        
        // Make sure 100 >= weight > 0
        if ((weight > 100) || (weight <= 0)) {
            str = "The weight for an assignment must be between (0, 100]. " +
                    "Please give enter a valid weight for this assignment.";
            box = new GUITextBox(str);
            box.createAndShowGUI();
            
            canContinue = false;
        }


        if (canContinue) {
            // Add data into the table
            // Create a new column (and a new header)
            DefaultTableModel model =
                    (DefaultTableModel) mainFrame.getTable().getModel();
            mainFrame.getGradeBook().addAssignment(name, points, weight);
            model.addColumn(name);
        }
    }

    /**
     * Create GUI and show it
     * @param mainFrame the frame to initialize with
     */
    static void createAndShowGUI(GUIFrame mainFrame) {
        //create and set up window
        frame = new JFrame("Add new Assignment");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(new GUIAddAssignment(mainFrame));

        frame.pack();
        frame.setVisible(true);
    }
}
