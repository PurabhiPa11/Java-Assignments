package swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Registrationform extends JFrame implements ActionListener {
    private final JTextField nameField;
    private final JTextField emailField;
    private final JPasswordField passwordField;
    private final JComboBox<String> roleCombo;
    private final JCheckBox termsCheckbox;
    private final JButton submitButton;
    private final JButton resetButton;
    private final JButton exitButton;

    public Registrationform() {
        super("Registration System");

        // Components
        JLabel titleLabel = new JLabel("User Registration");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel nameLabel = new JLabel("Full Name:");
        nameField = new JTextField(18);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField(18);

        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField(18);

        JLabel roleLabel = new JLabel("Role:");
        roleCombo = new JComboBox<>(new String[] {"Student", "Teacher", "Admin"});

        termsCheckbox = new JCheckBox("I accept the Terms & Conditions");

        submitButton = new JButton("Submit");
        resetButton = new JButton("Reset");
        exitButton = new JButton("Exit");

        // Events
        submitButton.addActionListener(this);
        resetButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Layout using GridBagLayout
        JPanel content = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        // Row 0: title
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.weightx = 1.0;
        content.add(titleLabel, gbc);

        // Row 1: name
        gbc.gridwidth = 1; gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 1;
        content.add(nameLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        content.add(nameField, gbc);

        // Row 2: email
        gbc.gridwidth = 1; gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 2;
        content.add(emailLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        content.add(emailField, gbc);

        // Row 3: password
        gbc.gridwidth = 1; gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 3;
        content.add(passwordLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        content.add(passwordField, gbc);

        // Row 4: role
        gbc.gridwidth = 1; gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 4;
        content.add(roleLabel, gbc);
        gbc.gridx = 1; gbc.weightx = 1.0;
        content.add(roleCombo, gbc);

        // Row 5: terms
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2; gbc.weightx = 1.0;
        content.add(termsCheckbox, gbc);

        // Row 6: buttons
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.add(submitButton);
        buttons.add(resetButton);
        buttons.add(exitButton);
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weightx = 1.0;
        content.add(buttons, gbc);

        setContentPane(content);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 360);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == submitButton) {
            handleSubmit();
        } else if (source == resetButton) {
            handleReset();
        } else if (source == exitButton) {
            dispose();
        }
    }

    private void handleSubmit() {
        String fullName = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String role = (String) roleCombo.getSelectedItem();
        boolean accepted = termsCheckbox.isSelected();

        // Basic validations
        if (fullName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your full name.", "Validation", JOptionPane.WARNING_MESSAGE);
            nameField.requestFocus();
            return;
        }
        if (email.isEmpty() || !email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(this, "Please enter a valid email.", "Validation", JOptionPane.WARNING_MESSAGE);
            emailField.requestFocus();
            return;
        }
        if (password.length() < 6) {
            JOptionPane.showMessageDialog(this, "Password must be at least 6 characters.", "Validation", JOptionPane.WARNING_MESSAGE);
            passwordField.requestFocus();
            return;
        }
        if (!accepted) {
            JOptionPane.showMessageDialog(this, "You must accept the Terms & Conditions.", "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Success – show a simple summary
        String message = "Registered successfully!\n" +
                "Name: " + fullName + "\n" +
                "Email: " + email + "\n" +
                "Role: " + role;
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleReset() {
        nameField.setText("");
        emailField.setText("");
        passwordField.setText("");
        roleCombo.setSelectedIndex(0);
        termsCheckbox.setSelected(false);
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Registrationform().setVisible(true);
        });
    }
}