package gui;

import gradebook.MyGradeBook;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Represents the graphic for creating a NEW Gradebook
 * from a string
 * @author Andrew Krischer
 * @version 2014-4-10
 *
 */
@SuppressWarnings("serial")
public class GUINewGradeBookFromString extends JPanel 
    implements ActionListener {
    private static JFrame frame;
    private JTextAreaWithScroll textArea;

    /**
     * Constructor for this graphic
     * @param mainFrame the frame to initialize with
     */
    GUINewGradeBookFromString(GUIFrame mainFrame) {
        JPanel pane1 = new JPanel();
        JPanel pane2 = new JPanel();

        JButton okButton;
        JButton cancelButton;
        JLabel description;

        description = new JLabel("String:");

        textArea = new JTextAreaWithScroll(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        textArea.setColumns(20);
        textArea.setLineWrap(true);
        textArea.setRows(8);
        textArea.setWrapStyleWord(true);

        okButton = new JButton("OK");
        okButton.addActionListener(this);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
            }
        });

        //put it together
        pane1.add(description);
        pane1.add(okButton);
        pane1.add(cancelButton);

        pane2.add(textArea.getScrollPane());

        add(pane1, BorderLayout.NORTH);
        add(pane2, BorderLayout.SOUTH);
    }

    /**
     * Extends JTextArea-- a custom scrolling text area!
     * @author Andrew Krischer
     * @version 2014-4-10
     */
    class JTextAreaWithScroll extends JTextArea {
        private JScrollPane scrollPane;

        /**
         * Constructor
         * @param vertSBPolicy the vert policy
         * @param horizSBPolicy the horiz policy
         */
        JTextAreaWithScroll(int vertSBPolicy, int horizSBPolicy) {
            scrollPane = new JScrollPane(this, vertSBPolicy, horizSBPolicy);
        }

        /**
         * Getter for the scroll pane
         * @return the scroll pane
         */
        JScrollPane getScrollPane() {
            return scrollPane;
        }
    }

    /**
     * Listener action to perform
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        try {
            String str = textArea.getText();
            
            MyGradeBook gb = MyGradeBook.initializeWithString(str);
            GUIFrame.createAndShowGUI(gb);
            
            frame.dispose();
            frame.setVisible(false);
        }

        catch (NullPointerException exc) {
            String str = "You must input a string";
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

        frame.add(new GUINewGradeBookFromString(mainFrame));

        frame.pack();
        frame.setVisible(true);
    }
}
