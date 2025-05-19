package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RemoveReservationView extends JFrame {
    private JButton returnButton, userProfileButton, removeReservationButton, carButton, largeCarButton, motorcycleButton;
    private JLabel titleLabel, subTitleLabel, errorLabel;
    private JTable reservationTable;
    private DefaultTableModel tableModel;

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getRemoveReservationButton() {
        return removeReservationButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getCarButton() {return carButton;}

    public JButton getLargeCarButton() {return largeCarButton;}

    public JButton getMotorcycleButton() {return motorcycleButton;}

    public JTable getReservationTable() {
        return reservationTable;
    }

    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    public RemoveReservationView() {
        setTitle("Parking LS - RemoveReservation");
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
        gbc.gridwidth = 3;
        gbc.insets = new Insets(4, 0, 4, 0);
        panel.add(titleLabel, gbc);
        subTitleLabel = new JLabel("Eliminar Reserva", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
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
        reservationTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        panel.add(reservationTable, gbc);
        panel.add(scrollPane, gbc);
        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(errorLabel, gbc);
        removeReservationButton = new JButton("Dar de Baja");
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(removeReservationButton, gbc);
        c.add(panel, BorderLayout.CENTER);
    }
    public void updateReservationsTable(DefaultTableModel model) {
        reservationTable.setModel(model);
    }

}