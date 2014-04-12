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
 * Represents the graphical element for adding a new student to the
 * table and gradebook (i.e. a new row).
 * @author Andrew Krischer
 * @author Adam Edgett edgett.a@husky.neu.edu
 * @version 2014-4-10
 *
 */
@SuppressWarnings("serial")
public class GUIAddStudent extends JPanel implements ActionListener {
    private JTextField userTextField;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField advisorTextField;
    private JTextField yearTextField;
    
    private static JFrame frame;
    private GUIFrame mainFrame;
    
    /**
     * Constructor for this graphical element
     * @param mainFrame - the original (main) frame, so that we can manipulate
     *                  its table based on what student we add.
     */
    GUIAddStudent(final GUIFrame mainFrame) {
        JLabel actionLabel;
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout());

        // Create text fields
        userTextField = new JTextField(12);
        firstNameTextField = new JTextField(12);
        lastNameTextField = new JTextField(12);
        advisorTextField = new JTextField(12);
        yearTextField = new JTextField(6);

        // Create labels for the text fields
        JLabel userLabel = new JLabel("Username: ");
        userLabel.setLabelFor(userTextField);

        JLabel firstLabel = new JLabel("First name: ");
        firstLabel.setLabelFor(firstNameTextField);

        JLabel lastLabel = new JLabel("Last name: ");
        lastLabel.setLabelFor(lastNameTextField);

        JLabel advisorLabel = new JLabel("Advisor: ");
        advisorLabel.setLabelFor(advisorTextField);

        JLabel yearLabel = new JLabel("Grad Year: ");
        yearLabel.setLabelFor(yearTextField);

        // Create a label for what to do
        actionLabel = new JLabel("Create a student by filling in the text "
                + "boxes.");
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

        JLabel[] labels = {
            userLabel, firstLabel, lastLabel, advisorLabel, yearLabel
        };
        JTextField[] textFields = {
            userTextField, firstNameTextField, lastNameTextField,
            advisorTextField, yearTextField
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

        textPane.add(userTextField);
        textPane.add(firstNameTextField);
        textPane.add(lastNameTextField);
        textPane.add(advisorTextField);
        textPane.add(yearTextField);

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
     * to create a student. If it cannot, an exception is thrown
     * and caught, which creates a new dialog box.
     * @author Andrew Krischer
     * @version 2014-4-10
     *
     */
    /**
     * Implemented method: tries to create a new student
     * @param e the action event
     */
    @SuppressWarnings("null")
    public void actionPerformed(ActionEvent e) {
        String username;
        String firstName;
        String lastName;
        String advisor;
        int year;

        try {
            username = userTextField.getText();
            firstName = firstNameTextField.getText();
            lastName = lastNameTextField.getText();
            advisor = advisorTextField.getText();
            year = Integer.parseInt(yearTextField.getText());

            // check if username taken
            if (mainFrame.getGradeBook().getStudent(username) != null) {
                throw new IllegalArgumentException("Invalid username");
            }

            // Add data into the table
            DefaultTableModel model =
                    (DefaultTableModel) mainFrame.getTable().getModel();
            mainFrame.getGradeBook().addStudent(username, firstName,
                    lastName, year, advisor);
            model.addRow(
                    new Object[] {username, firstName, lastName, advisor, year}
            );
            // reset fields
            userTextField.setText(null);
            firstNameTextField.setText(null);
            lastNameTextField.setText(null);
            advisorTextField.setText(null);
            yearTextField.setText(null);
        }
        catch (NullPointerException exc) {
            String str = "Field cannot be left empty!";
            GUITextBox box = new GUITextBox(str);
            box.createAndShowGUI();
        }
        catch (NumberFormatException exc) {
            String str = "Enter a valid year!";
            GUITextBox box = new GUITextBox(str);
            box.createAndShowGUI();
        }
        catch (IllegalArgumentException exc) {
            String str = "Cannot add new student. The username"
                    + " is already being used.";
            GUITextBox box = new GUITextBox(str);
            box.createAndShowGUI();
        }
    }

    /**
     * Create GUI and show it
     * @param mainFrame the frame
     */
    static void createAndShowGUI(GUIFrame mainFrame) {
        //create and set up window
        frame = new JFrame("Add new Student");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(new GUIAddStudent(mainFrame));

        frame.pack();
        frame.setVisible(true);
    }
}
