package org.exercise;

import javax.swing.*;

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


    }
}
