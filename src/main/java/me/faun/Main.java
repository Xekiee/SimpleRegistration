package me.faun;

import com.formdev.flatlaf.FlatLightLaf;
import me.faun.authentication.AuthService;
import me.faun.authentication.storage.AccountStorage;
import me.faun.authentication.storage.CsvAccountStorage;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        FlatLightLaf.setup();

        // Manual dependency injection
        AccountStorage storage = new CsvAccountStorage();
        AuthService authService = new AuthService(storage);

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Authentication");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);
            frame.setLocationRelativeTo(null);

            JTabbedPane tabbedPane = new JTabbedPane();

            // Login Panel
            JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
            JTextField loginUsername = new JTextField();
            JPasswordField loginPassword = new JPasswordField();
            JButton loginButton = new JButton("Login");

            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(loginUsername);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(loginPassword);
            loginPanel.add(loginButton);

            loginButton.addActionListener(e -> {
                String username = loginUsername.getText();
                String password = new String(loginPassword.getPassword());

                if (authService.login(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // Register Panel
            JPanel registerPanel = new JPanel(new GridLayout(4, 2, 5, 5));
            JTextField regUsername = new JTextField();
            JTextField regEmail = new JTextField();
            JPasswordField regPassword = new JPasswordField();
            JButton registerButton = new JButton("Register");

            registerPanel.add(new JLabel("Username:"));
            registerPanel.add(regUsername);
            registerPanel.add(new JLabel("Email:"));
            registerPanel.add(regEmail);
            registerPanel.add(new JLabel("Password:"));
            registerPanel.add(regPassword);
            registerPanel.add(new JLabel());
            registerPanel.add(registerButton);

            registerButton.addActionListener(e -> {
                String username = regUsername.getText();
                String email = regEmail.getText();
                String password = new String(regPassword.getPassword());

                if (authService.register(username, email, password)) {
                    JOptionPane.showMessageDialog(frame, "Registration successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Registration failed!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            tabbedPane.addTab("Login", loginPanel);
            tabbedPane.addTab("Register", registerPanel);

            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }
}