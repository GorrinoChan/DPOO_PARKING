package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminCreateSlot extends JFrame {
    private JButton returnButton, CreateButton;
    private JLabel numberLabel, floatLabel, tipeLabel,titleLabel;
    private JTextField userTextField, mailTextField;
    private JPasswordField passwordTextField;

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getSignInButton() {
        return CreateButton;
    }

    public String getUsername() {
        return userTextField.getText();
    }

    public String getEmail() {
        return mailTextField.getText();
    }


    public AdminCreateSlot() {
        setTitle("Parking LS - AdminCreateSlot");
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

        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        numberLabel = new JLabel("Numero");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numberLabel, gbc);

        userTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(userTextField, gbc);

        floatLabel = new JLabel("Piso");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(floatLabel, gbc);

        mailTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(mailTextField, gbc);

        tipeLabel = new JLabel("Tipo de vehiculo");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(tipeLabel, gbc);

        passwordTextField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(passwordTextField, gbc);

        CreateButton = new JButton("Crear");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(CreateButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}