package de.nx02.stringrex;

import com.mifmif.common.regex.Generex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * RegexGenerator class file
 *
 * Contains a swing form which alles to generate strings from given regular expressions
 *
 * @author Thomas Vollstädt <vollstaedt@ofigo.de>
 */
public class StringRexForm {
    private JTextArea txtList;
    private JButton btnGenerate;
    private JButton btnSave;
    private JLabel lblRegEx;
    private JLabel lblOutput;
    private JTextField txtRegEx;
    private JPanel panel;

    private Generex generex;

    private JFileChooser fileChooser;

    // Show main frame
    public static void show() {
        JFrame frame = new JFrame("StringRex");
        frame.setContentPane(new StringRexForm().panel);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        // set native look & feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //make the window startup position be centered
        frame.setLocationRelativeTo(null);

        // Dont allow resizing
        frame.setResizable(false);
    }

    // Constructor which contains click event handlers
    StringRexForm() {
        txtList.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        btnGenerate.addMouseListener(new MouseListener() {

            /**
             *  Implement mouse click event on "Generate"-Button
             */
            @Override
            public void mouseClicked(MouseEvent event) {
                try {
                    // Parse RegEx
                    generex = new Generex(txtRegEx.getText());
                } catch(IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error parsing RegEx", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }

                // Clear text field
                txtList.setText("");

                /**
                 * Add all generated strings to edit field
                 */
                for(String line: generex.getAllMatchedStrings()) {
                    txtList.append(line + "\n");
                }
            }

            @Override
            public void mousePressed(MouseEvent event) {}

            @Override
            public void mouseReleased(MouseEvent event) {}

            @Override
            public void mouseEntered(MouseEvent event) {}

            @Override
            public void mouseExited(MouseEvent event) {}
        });
        btnSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                // Open file chooser dialog
                fileChooser = new JFileChooser();
                if(JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        // Create file if not exists
                        if(!file.exists()) {
                            file.createNewFile();
                        }

                        if(file.canWrite()) {
                            BufferedWriter output = null;
                            /**
                             * Write list to file
                             */
                            output = new BufferedWriter(new FileWriter(file));
                            output.write(txtList.getText());
                            output.close();
                            JOptionPane.showMessageDialog(null, "List saved", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);

                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot write file", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "Error writing to file", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent event) {}

            @Override
            public void mouseReleased(MouseEvent event) {}

            @Override
            public void mouseEntered(MouseEvent event) {}

            @Override
            public void mouseExited(MouseEvent event) {}
        });
    }
}
