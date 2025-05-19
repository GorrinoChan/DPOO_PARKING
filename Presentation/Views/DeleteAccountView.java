package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class DeleteAccountView extends JFrame {
    private JButton returnButton, confirmButton;
    private JLabel titleLabel, subTitleLabel;
    public JButton getConfirmButton() {
        return confirmButton;
    }
    public JButton getReturnButton() {
        return returnButton;
    }

    public DeleteAccountView() {
        setTitle("Parking LS - DeleteAccount");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 30, 0);
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
        subTitleLabel = new JLabel("¿Quieres eliminar la cuenta?", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);
        confirmButton = new JButton("Confirmar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(confirmButton, gbc);
        c.add(panel, BorderLayout.CENTER);
    }


}