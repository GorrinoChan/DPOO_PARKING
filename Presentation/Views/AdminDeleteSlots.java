package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminDeleteSlots extends JFrame {
    private JButton returnButton,confirmButton;
    private JLabel titleLabel;

    public JButton getReturnButton() {
        return returnButton;
    }

    public AdminDeleteSlots() {
        setTitle("Parking LS - AdminDeleteSlots");
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

        confirmButton = new JButton("Confirmar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(confirmButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}