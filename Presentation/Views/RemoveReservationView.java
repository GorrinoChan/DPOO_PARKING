package Presentation.Views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RemoveReservationView extends JFrame {
    private JButton returnButton, userProfileButton, removeReservationButton;
    private JLabel titleLabel, subTitleLabel, plateLabel, errorLabel;
    private JTextField plateTextField;
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

    public String getPlateTextField() {
        return plateTextField.getText();
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
        gbc.gridwidth = 2;
        gbc.insets = new Insets(4, 0, 4, 0);
        panel.add(titleLabel, gbc);

        subTitleLabel = new JLabel("Eliminar Reserva", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);

        reservationTable = new JTable(tableModel);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(reservationTable, gbc);
        reservationTable.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(reservationTable);
        panel.add(scrollPane, gbc);

        plateLabel = new JLabel("Matrícula");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(plateLabel, gbc);

        plateTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(plateTextField, gbc);

        errorLabel = new JLabel("", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(errorLabel, gbc);

        removeReservationButton = new JButton("Dar de Baja");
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(removeReservationButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }
    public void updateReservationsTable(DefaultTableModel model) {
        reservationTable.setModel(model);
    }

}