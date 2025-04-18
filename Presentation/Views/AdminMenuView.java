package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminMenuView extends JFrame {

    private JButton Slots, Simulate, slotAvaliableButton, graphButton;
    private JLabel titleLabel, subTitleLabel;

    public JButton getSlots() {
        return Slots;
    }

    public JButton getSimulate() {
        return Simulate;
    }
    public JButton getslotAvaliableButton() {
        return slotAvaliableButton;
    }
    public JButton getgraphButton() {
        return graphButton;
    }

    public AdminMenuView() {
        setTitle("Parking LS - AdminMenuView");
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

        subTitleLabel = new JLabel("Menu", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        Slots = new JButton("Gestion de las plazas");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(Slots, gbc);

        Simulate = new JButton("Simular transito");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Simulate, gbc);

        slotAvaliableButton = new JButton("Ver plazas disponibles");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(slotAvaliableButton, gbc);

        graphButton = new JButton("Gráfico de última hora");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(graphButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}

