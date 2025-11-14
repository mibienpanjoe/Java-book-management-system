package org.exercise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    // interface component
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    //constructor
    public LoginForm() {
        //frame size and title
        setTitle("Login Form");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Layout Panel creation (could use container default ? maybe but as to verify it before)
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Components creation
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        //Input components width
        usernameField.setColumns(20);
        passwordField.setColumns(20);

        // adding elements to the panel created
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(loginButton);

        loginButton.addActionListener((ActionListener) new  ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //Receive user information
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                //Communication with the database
                try(Connection conn = DatabaseConnection.getConnection()){
                    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                    PreparedStatement stmt = conn.prepareStatement(sql);
                    stmt.setString(1,username);
                    stmt.setString(2,password);
                    ResultSet rs = stmt.executeQuery(); // the result will come here

                    if(rs.next()){
                    JOptionPane.showMessageDialog(LoginForm.this, "Login Successful");
                    new BookManagementForm().setVisible(true);
                    dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(LoginForm.this, "Invalid Username or Password");
                    }

                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

        });
        add(panel);
    }


}
