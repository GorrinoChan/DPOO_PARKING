package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class CheckPlateInParkingView extends JFrame {
    private JButton returnButton, userProfileButton, confirmButton;
    private JLabel titleLabel, plateLabel, errorLabel, subTitleLabel;
    private JTextField plateTextField;

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public String getPlate() {
        return plateTextField.getText();
    }

    public CheckPlateInParkingView() {
        setTitle("Parking LS - CheckPlateInParking");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 20);

        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);

        userProfileButton = new JButton("User");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(userProfileButton, gbc);

        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 4, 0);
        panel.add(titleLabel, gbc);

        subTitleLabel = new JLabel("Salir del Parking", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);

        plateLabel = new JLabel("Matrícula");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(plateLabel, gbc);

        plateTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(plateTextField, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(titleLabel, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(titleLabel, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(titleLabel, gbc);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(errorLabel, gbc);

        confirmButton = new JButton("Confirmar");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(confirmButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}