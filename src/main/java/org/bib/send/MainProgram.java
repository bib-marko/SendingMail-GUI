package org.bib.send;

import javax.swing.*;
import java.io.*;

public class MainProgram extends JFrame {
    private JTextField txtTO, txtCC, txtBCC, txtSubject, txtFilename;
    private JLabel lblTO, lblCC, lblBCC, lblSubject;
    private JTextArea txtBody;
    private JButton btnSend, btnAttachement;
    private JPanel mainPanel;


    public static void main(String[] args) {
        new MainProgram();
    }

    public MainProgram() {
        super("Send Email");

        setContentPane(mainPanel);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setVisible(true);
        txtFilename.setEditable(false);

        btnSend.addActionListener(e -> {
            if(EmailDataHolder.validateFieldIfEmpty(new String[]{txtTO.getText(), txtCC.getText(), txtBCC.getText(), txtSubject.getText(), txtBody.getText(), txtFilename.getText()})){
                new EmailDataHolder(txtTO.getText(), txtCC.getText(), txtBCC.getText(), txtSubject.getText(), txtBody.getText(), txtFilename.getText());
                Processor.processMessage();
                loadDefaults();
            }else{
                JOptionPane.showMessageDialog(this, "Please fill out any of recipient type!", "Empty Recipient Field", JOptionPane.WARNING_MESSAGE);
            }

        });

        btnAttachement.addActionListener(e -> attachFile());
    }

    private void loadDefaults() {
        txtTO.setText("");
        txtCC.setText("");
        txtBCC.setText("");
        txtSubject.setText("");
        txtBody.setText("");
        txtFilename.setText("");
    }

    private void attachFile() {
        JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(mainPanel);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fc.getSelectedFile();
                String path = selectedFile.getAbsolutePath();
                txtFilename.setText(path);
        }
    }

}
