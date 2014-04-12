package gui;

import gradebook.MyGradeBook;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Creates a file chooser for the user. This is used for both
 * opening and saving Gradebooks, depending on the File Chooser's type.
 * @author Andrew Krischer
 * @version 2014-4-10
 *
 */
public class GUIFileChooser extends JPanel implements ActionListener {

    // Whether we should open or save ("open" or "save")
    private String type;
    private JFileChooser fc;
    private GUIFrame mainFrame;

    /**
     * Constructor for the File Chooser
     * @param type - the string, either "open" or "save" which
     *              determines whether it's for saving or opening a file
     * @param mainFrame the frame to initialize with
     */
    GUIFileChooser(String type, GUIFrame mainFrame) {
        super(new BorderLayout());
        this.type = type;
        this.mainFrame = mainFrame;

        // Create a file chooser
        fc = new JFileChooser();
        fc.addActionListener(this);


        //allow files and/or directories to be selected
        if (type.equals("save")) {
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        }
        else if (type.equals("open")) {
            fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        }
        else {
            throw new RuntimeException("GUIFileChooser: Invalid type given-- "
                    + type);
        }
    }

    /**
     * Implemented method which handles whether this file chooser
     * is saving or opening a file.
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        //Handle open request
        if (type.equals("open")) {
            int returnVal = fc.showOpenDialog(GUIFileChooser.this);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filepath = file.getAbsolutePath();

                MyGradeBook newGB = MyGradeBook.initializeWithFile(filepath);
                GUIFrame.createAndShowGUI(newGB);

                String str = "Gradebook successfully opened!";
                GUITextBox box = new GUITextBox(str);
                box.createAndShowGUI();
            }
            // Handle Cancel event here (?)
        }

        //Handle save request
        else if (type.equals("save")) {
            int returnVal = fc.showSaveDialog(GUIFileChooser.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                String filepath = file.getAbsolutePath();

                mainFrame.getGradeBook().outputGradebook(filepath);

                String str = "Gradebook successfully saved!";
                GUITextBox box = new GUITextBox(str);
                box.createAndShowGUI();
            }
            // Handle Cancel event here (?)
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame(type);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //Add content to the window.
        frame.add(new GUIFileChooser(type, mainFrame));

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
}
