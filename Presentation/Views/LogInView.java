package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class LogInView extends JFrame {
    private JButton returnButton, forgotPassword, loginButton, signInButton;
    private JLabel titleLabel, subTitleLabel, userLabel, passwordLabel, errorLabel;
    private JTextField userTextField;
    private JPasswordField passwordTextField;

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getPassword() {
        return new String(passwordTextField.getPassword());
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public LogInView() {
        setTitle("Parking LS - LogIn");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        subTitleLabel = new JLabel("Inicia Sesión", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);
        userLabel = new JLabel("Usuario");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);
        userTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(userTextField, gbc);
        passwordLabel = new JLabel("Contraseña");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(passwordLabel, gbc);
        passwordTextField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(passwordTextField, gbc);
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(errorLabel, gbc);
        loginButton = new JButton("Inicia Sesión");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(loginButton, gbc);
        signInButton = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(signInButton, gbc);
        c.add(panel, BorderLayout.CENTER);
    }
}