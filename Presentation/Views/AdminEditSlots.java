package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminEditSlots extends JFrame {
    private JButton returnButton, EditButton,userProfileButton;
    private JLabel numberLabel, floatLabel, tipeLabel,titleLabel,numbereditLabel,errorLabel;
    private JTextField numberTextField, floatTextField,tipeTextField,numbersearchTextField;

    public String getnumber1() {
        return numbersearchTextField.getText();
    }
    public String getnumber2() {
        return numberTextField.getText();
    }
    public String getfloat() {
        return floatTextField.getText();
    }
    public String gettipe() {
        return tipeTextField.getText();
    }

    public JButton getReturnButton() {
        return returnButton;
    }
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public JButton getEditButton() {
        return EditButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }


    public AdminEditSlots() {
        setTitle("Parking LS - AdminEditSlots");
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

        numbereditLabel = new JLabel("Numero de Plaza a Editar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numbereditLabel, gbc);

        numbersearchTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numbersearchTextField, gbc);

        numberLabel = new JLabel("Numero");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(numberLabel, gbc);

        numberTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(numberTextField, gbc);

        floatLabel = new JLabel("Piso");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(floatLabel, gbc);

        floatTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(floatTextField, gbc);

        tipeLabel = new JLabel("Tipo de vehiculo");
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(tipeLabel, gbc);

        tipeTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(tipeTextField, gbc);

        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(errorLabel, gbc);

        EditButton = new JButton("Editar");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(EditButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}