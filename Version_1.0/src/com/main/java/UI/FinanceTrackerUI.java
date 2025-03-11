package com.main.java.UI;

import javax.swing.*;
import java.awt.*;

public class FinanceTrackerUI {
    private JFrame frame;

    public FinanceTrackerUI() {
        frame = new JFrame("Finance Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the size of the window to be larger
        frame.setSize(800, 600);  // Adjust the size as needed
        frame.setLayout(new BorderLayout());

        // Set the window to be centered on the screen
        frame.setLocationRelativeTo(null);

        // Add content to your main window
        JTextArea textArea = new JTextArea("Welcome to the Finance Tracker!");
        frame.add(textArea, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FinanceTrackerUI();
    }
}
