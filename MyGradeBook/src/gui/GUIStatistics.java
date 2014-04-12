package gui;

import gradebook.Assignment;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * The graphical element for calculating statistics
 * @author Andrew Krischer
 * @version 2014-4-10
 */
public class GUIStatistics extends JPanel implements ActionListener {
    private GUIFrame mainFrame;
    private static JFrame frame;

    private JCheckBox minCB;
    private JCheckBox maxCB;
    private JCheckBox medianCB;
    private JCheckBox meanCB;

    private ArrayList<JCheckBox> assignCBAL;

    /**
     * Constructor for this graphic
     * @param mainFrame the frame to initialize with
     */
    GUIStatistics(GUIFrame mainFrame) {
        this.mainFrame = mainFrame;

        JPanel checkPane = new JPanel();
        JPanel statPane = new JPanel();
        JPanel buttonPane = new JPanel();

        JButton okButton;
        JButton cancelButton;

        ArrayList<Assignment> assignments =
                mainFrame.getGradeBook().getAssignments();

        assignCBAL = new ArrayList<JCheckBox>();

        // gives every assignment a checkbox
        for (Assignment assn : assignments) {

            String aName = assn.getName();
            JCheckBox cb = new JCheckBox(aName);
            checkPane.add(cb);
            assignCBAL.add(cb);
        }

        minCB = new JCheckBox("min");
        maxCB = new JCheckBox("max");
        medianCB = new JCheckBox("median");
        meanCB = new JCheckBox("mean");

        statPane.add(minCB);
        statPane.add(maxCB);
        statPane.add(medianCB);
        statPane.add(meanCB);


        okButton = new JButton("OK");
        okButton.addActionListener(this);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
            }
        });

        buttonPane.add(okButton);
        buttonPane.add(cancelButton);

        // put panes together
        add(checkPane, BorderLayout.NORTH);
        add(statPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.SOUTH);
    }


    /**
     * Listener action to perform
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        try {
            ArrayList<String> assNames = new ArrayList<String>();

            for (JCheckBox cb : assignCBAL) {
                if (cb.isSelected()) { assNames.add(cb.getText()); }
            }

            String str = "";

            for (String a : assNames) {
                if (minCB.isSelected()) {
                    str += "min for Assignment " + a + ": " +
                            mainFrame.getGradeBook().min(a) + "\n";
                }

                if (maxCB.isSelected()) {
                    str += "max for Assignment " + a + ": " +
                            mainFrame.getGradeBook().max(a) + "\n";
                }

                if (medianCB.isSelected()) {
                    str += "median for Assignment " + a + ": " +
                            mainFrame.getGradeBook().median(a) + "\n";
                }

                if (meanCB.isSelected()) {
                    str += "mean for Assignment " + a + ": " +
                            mainFrame.getGradeBook().average(a) + "\n";
                }
            }

            // give the results!
            if (str.equals("")) {
                str = "Please check at least one statistics box " +
                        "(min, max, median, or mean).";
                GUITextBox box = new GUITextBox(str);
                box.createAndShowGUI();
            }

            else {
                GUITextBox box = new GUITextBox(str);
                box.createAndShowGUI();

                frame.dispose();
                frame.setVisible(false);
            }
        }

        catch (NullPointerException exc) {
            String str = 
                    "Please select at least one assignment to give stats for.";
            GUITextBox box = new GUITextBox(str);
            box.createAndShowGUI();
        }
    }


    /**
     * Actually creates the frame and then displays it
     * @param mainFrame the frame to initialize with
     */
    static void createAndShowGUI(GUIFrame mainFrame) {
        frame = new JFrame("New GradeBook from String");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.add(new GUIStatistics(mainFrame));

        frame.pack();
        frame.setVisible(true);
    }
}
