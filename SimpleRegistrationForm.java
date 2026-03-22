import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimpleRegistrationForm extends JFrame implements ActionListener {

    private JLabel lblTitle, lblName, lblEmail, lblPassword;
    private JTextField textName, textEmail;
    private JPasswordField textPassword;
    private JButton btnRegister, btnClear;

    public SimpleRegistrationForm() {
        setTitle("Registration System");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Title
        lblTitle = new JLabel("User Registration", JLabel.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setBounds(100, 20, 250, 30);
        add(lblTitle);

        // Full Name
        lblName = new JLabel("Full Name:");
        lblName.setBounds(50, 70, 100, 25);
        add(lblName);

        textName = new JTextField();
        textName.setBounds(160, 70, 200, 25);
        add(textName);

        // Email
        lblEmail = new JLabel("Email:");
        lblEmail.setBounds(50, 110, 100, 25);
        add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(160, 110, 200, 25);
        add(textEmail);

        // Password
        lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 150, 100, 25);
        add(lblPassword);

        textPassword = new JPasswordField();
        textPassword.setBounds(160, 150, 200, 25);
        add(textPassword);

        // Register Button
        btnRegister = new JButton("Register");
        btnRegister.setBounds(100, 200, 100, 30);
        add(btnRegister);

        // Clear Button
        btnClear = new JButton("Clear");
        btnClear.setBounds(220, 200, 100, 30);
        add(btnClear);

        // Add Action Listeners
        btnRegister.addActionListener(this);
        btnClear.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            String name = textName.getText();
            String email = textEmail.getText();
            String password = new String(textPassword.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter all the details!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Registration Successful!\nWelcome, " + name,
                        "Message",
                        JOptionPane.INFORMATION_MESSAGE);

                // Clear fields after success
                textName.setText("");
                textEmail.setText("");
                textPassword.setText("");
            }
        } else if (e.getSource() == btnClear) {
            textName.setText("");
            textEmail.setText("");
            textPassword.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleRegistrationForm().setVisible(true);
        });
    }
}

