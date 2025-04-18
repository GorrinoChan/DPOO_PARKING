package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminManagement extends JFrame {
    private JButton Slots, Simulate, slotAvaliableButton;
    private JLabel titleLabel, subTitleLabel;

    public AdminManagement() {
        setTitle("Parking LS - AdminManagament");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);


        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        subTitleLabel = new JLabel("Managament", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        Slots = new JButton("Crear");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(Slots, gbc);

        Simulate = new JButton("Editar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Simulate, gbc);

        slotAvaliableButton = new JButton("Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(slotAvaliableButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}
