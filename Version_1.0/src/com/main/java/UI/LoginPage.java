package com.main.java.UI;

import com.main.data.UserDataStorage;
import com.main.java.UI.FinanceTrackerUI;
import com.main.java.UI.RegistrationPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginPage() {
        frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window to be larger
        frame.setSize(400, 300);  // You can adjust the width and height
        frame.setLayout(new FlowLayout());

        // Set the window to be centered on the screen
        frame.setLocationRelativeTo(null);

        // Username label and field
        frame.add(new JLabel("Username:"));
        usernameField = new JTextField(20);
        frame.add(usernameField);

        // Password label and field
        frame.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        frame.add(passwordField);

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Authenticate user
                if (UserDataStorage.authenticateUser(username, password)) {
                    // Login successful, proceed to main page
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    new FinanceTrackerUI();  // Open the main page
                    frame.dispose();  // Close the login page
                } else {
                    // Invalid credentials, show error
                    JOptionPane.showMessageDialog(frame, "Invalid credentials", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.add(loginButton);

        // Register Button (Navigates to registration page)
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistrationPage();  // Open registration page
                frame.dispose();  // Close login page
            }
        });
        frame.add(registerButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new LoginPage();
    }
}
