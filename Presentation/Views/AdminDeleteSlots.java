package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminDeleteSlots extends JFrame {
    private JButton returnButton,DeleteButton,userProfileButton;
    private JLabel titleLabel,numbereditLabel,errorLabel;
    private JTextField numbersearchTextField;

    public String getnumber() { return numbersearchTextField.getText(); }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getDeleteButton() {
        return DeleteButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public AdminDeleteSlots() {
        setTitle("Parking LS - AdminDeleteSlots");
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

        userProfileButton = new JButton("User");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(userProfileButton, gbc);

        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        numbereditLabel = new JLabel("Numero de Plaza a Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numbereditLabel, gbc);

        numbersearchTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numbersearchTextField, gbc);

        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(errorLabel, gbc);

        DeleteButton = new JButton("Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(DeleteButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}