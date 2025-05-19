package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class SlotAvailableView extends JFrame {
    private JButton returnButton, userProfileButton, carButton, largeCarButton, motorcycleButton;
    private JLabel titleLabel, subTitleLabel;
    private JTable slotsAvaliableTable;
    private DefaultTableModel tableModel;

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getCarButton() { return carButton; }

    public JButton getLargeCarButton() { return largeCarButton; }

    public JButton getMotorcycleButton() { return motorcycleButton; }

    public SlotAvailableView() {
        setTitle("Parking LS - SlotAvaliable");
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
        subTitleLabel = new JLabel("Plazas Disponibles", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        carButton = new JButton("Coches");
        largeCarButton = new JButton("Vehículos Grandes");
        motorcycleButton = new JButton("Motos");
        buttonPanel.add(carButton);
        buttonPanel.add(largeCarButton);
        buttonPanel.add(motorcycleButton);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        panel.add(buttonPanel, gbc);
        slotsAvaliableTable = new JTable(tableModel);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(slotsAvaliableTable, gbc);
        slotsAvaliableTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(slotsAvaliableTable);
        panel.add(scrollPane, gbc);
        c.add(panel, BorderLayout.CENTER);
    }

    public void updateSlotsAvailableTable(DefaultTableModel model) {
        slotsAvaliableTable.setModel(model);
    }
}