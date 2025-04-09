package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class SignInView extends JFrame {
    private JButton returnButton, signInButton;
    private JLabel subTitleLabel, userLabel, mailLabel, passwordLabel, passwordConfirmLabel, errorLabel;
    private JTextField userTextField, mailTextField;
    private JPasswordField passwordTextField, passwordConfirmTextField;

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getSignInButton() {
        return signInButton;
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getEmail() {
        return mailTextField.getText();
    }

    public String getPassword() {
        return new String(passwordTextField.getPassword());
    }

    public String getPasswordConfirmation() {
        return new String(passwordConfirmTextField.getPassword());
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public SignInView() {
        setTitle("Parking LS - SignIn");
        setSize(500, 400);
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

        subTitleLabel = new JLabel("Registrarse", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);

        userLabel = new JLabel("Usuario");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);

        userTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(userTextField, gbc);

        mailLabel = new JLabel("Correo electrónico");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(mailLabel, gbc);

        mailTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(mailTextField, gbc);

        passwordLabel = new JLabel("Contraseña");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(passwordLabel, gbc);

        passwordTextField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(passwordTextField, gbc);

        passwordConfirmLabel = new JLabel("Comprovación de contraseña");
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(passwordConfirmLabel, gbc);

        passwordConfirmTextField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(passwordConfirmTextField, gbc);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(errorLabel, gbc);

        signInButton = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(signInButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

    }