package com.main.java.UI;

import com.main.data.UserDataStorage;
import com.main.java.UI.LoginPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationPage {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public RegistrationPage() {
        frame = new JFrame("Registration Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window to be larger
        frame.setSize(400, 350);  // Adjust the size as needed
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

        // Confirm Password label and field
        frame.add(new JLabel("Confirm Password:"));
        confirmPasswordField = new JPasswordField(20);
        frame.add(confirmPasswordField);

        // Register Button
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());

                // Basic validation for username and password
                if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Register user (save to file)
                    UserDataStorage.registerUser(username, password);
                    JOptionPane.showMessageDialog(frame, "Registration Successful!");
                    new LoginPage();  // Navigate back to login page
                    frame.dispose();  // Close registration page
                }
            }
        });
        frame.add(registerButton);

        // Back to login button
        JButton backButton = new JButton("Back to Login");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();  // Go back to login page
                frame.dispose();  // Close registration page
            }
        });
        frame.add(backButton);

        frame.setVisible(true);
    }
}
