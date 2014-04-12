package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A simple text box that has text information and an OK button
 * @author Andrew Krischer
 * @version 2014-4-10
 *
 */
@SuppressWarnings("serial")
public class GUITextBox extends JDialog implements ActionListener {
    private String text;

    /**
     * Constructor for text box
     * @param text - message to display
     */
    public GUITextBox(String text) {
        this.text = text;
    }
    
    /**
     * Trigger action for this class
     * @param e the action event
     */
    public void actionPerformed(ActionEvent e) {
        createAndShowGUI();
    }
    
    /**
     * Creates and actually shows the text box
     */
    void createAndShowGUI() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel messagePane = new JPanel();
        getContentPane().add(messagePane);
        messagePane.add(new JLabel(text));

        JPanel buttonPane = new JPanel();
        JButton okButton = new JButton("OK");
        buttonPane.add(okButton);
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}
