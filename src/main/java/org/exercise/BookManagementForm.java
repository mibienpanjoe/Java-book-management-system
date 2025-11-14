package org.exercise;

import javax.swing.*;

public class BookManagementForm extends JFrame {

    //Interface components
    JTextField yearField, authorField, titleField;
    private JButton addButton , updateButton , deleteButton, viewButton;

    // constructor
    public BookManagementForm() {

        //Management frame layout
        setTitle("Book Management Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel info
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();

    }
}
