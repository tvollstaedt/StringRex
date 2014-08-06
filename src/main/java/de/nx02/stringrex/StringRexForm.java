package de.nx02.stringrex;

import com.mifmif.common.regex.Generex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;

/**
 * StringRexForm class file
 * <p/>
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
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(null, "Error parsing RegEx", "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }

                // Clear text field
                txtList.setText("");

                /**
                 * Add all generated strings to edit field
                 */
                for (String line : generex.getAllMatchedStrings()) {
                    txtList.append(line + "\n");
                }
            }

            @Override
            public void mousePressed(MouseEvent event) { }

            @Override
            public void mouseReleased(MouseEvent event) { }

            @Override
            public void mouseEntered(MouseEvent event) { }

            @Override
            public void mouseExited(MouseEvent event) { }
        });

        btnSave.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent event) {
                // Open file chooser dialog
                fileChooser = new JFileChooser();
                if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(null)) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        // Create file if not exists
                        if (!file.exists()) {
                            file.createNewFile();
                        }

                        if (file.canWrite()) {
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
            public void mousePressed(MouseEvent event) { }

            @Override
            public void mouseReleased(MouseEvent event) { }

            @Override
            public void mouseEntered(MouseEvent event) { }

            @Override
            public void mouseExited(MouseEvent event) { }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 1, new Insets(5, 5, 5, 5), -1, -1));
        panel.setMinimumSize(new Dimension(300, 400));
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        lblRegEx = new JLabel();
        lblRegEx.setFont(new Font(lblRegEx.getFont().getName(), lblRegEx.getFont().getStyle(), lblRegEx.getFont().getSize()));
        lblRegEx.setText("Regular Expression");
        panel.add(lblRegEx, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(478, 16), null, 0, false));
        lblOutput = new JLabel();
        lblOutput.setText("Output");
        panel.add(lblOutput, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        txtRegEx = new JTextField();
        txtRegEx.setFont(new Font("Monospaced", txtRegEx.getFont().getStyle(), txtRegEx.getFont().getSize()));
        panel.add(txtRegEx, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(-1, 400), null, null, 0, false));
        txtList = new JTextArea();
        txtList.setEditable(true);
        txtList.setFont(UIManager.getFont("TextArea.font"));
        txtList.setLineWrap(false);
        txtList.setText("");
        scrollPane1.setViewportView(txtList);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel.add(panel1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btnSave = new JButton();
        btnSave.setText("Save to file");
        panel1.add(btnSave, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btnGenerate = new JButton();
        btnGenerate.setText("Generate");
        panel1.add(btnGenerate, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lblOutput.setLabelFor(scrollPane1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel;
    }
}
