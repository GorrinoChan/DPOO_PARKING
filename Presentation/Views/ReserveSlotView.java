package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ReserveSlotView extends JFrame {
    private JButton returnButton, userProfileButton, confirmButton, refreshButton;
    private JLabel titleLabel, subTitleLabel, plateLabel, errorLabel, typeVehicleLabel;
    private JTextField plateTextField, typeVehicleTextField;
    private JTable slotsAvaliableTable;
    private DefaultTableModel tableModel;

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getRefreshButton() {
        return refreshButton;
    }

    public String getPlateTextField() {
        return plateTextField.getText();
    }

    public String getTypeVehicleTextField() {
        return typeVehicleTextField.getText();
    }

    public JTable getSlotsAvaliableTable() {
        return slotsAvaliableTable;
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public ReserveSlotView() {
        setTitle("Parking LS - ReserveSlot");
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

        refreshButton = new JButton("Recarga Tabla");
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(refreshButton, gbc);

        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(4, 0, 4, 0);
        panel.add(titleLabel, gbc);

        subTitleLabel = new JLabel("Reservar", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        plateLabel = new JLabel("Matrícula");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(plateLabel, gbc);

        plateTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(plateTextField, gbc);

        typeVehicleLabel = new JLabel("Tipo de Vehículo");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(typeVehicleLabel, gbc);

        typeVehicleTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(typeVehicleTextField, gbc);

        tableModel= new DefaultTableModel(new String[]{"Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);
        slotsAvaliableTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(slotsAvaliableTable);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        panel.add(slotsAvaliableTable, gbc);
        panel.add(scrollPane, gbc);

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

    public void updateSlotsAvaliableTable(DefaultTableModel model) {
        slotsAvaliableTable.setModel(model);
    }

}