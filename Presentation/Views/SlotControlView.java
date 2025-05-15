package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class SlotControlView extends JFrame {
    private JButton returnButton, userProfileButton, reserveSlotButton, removeReservationButton, reservationsButton;
    private JLabel titleLabel, subTitleLabel;

    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JButton getReserveSlotButton() {
        return reserveSlotButton;
    }

    public JButton getRemoveReservationButton() {
        return removeReservationButton;
    }

    public JButton getReservationsButton() { return reservationsButton; }

    public SlotControlView() {
        setTitle("Parking LS - SlotControl");
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
        gbc.gridwidth = 1;
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

        subTitleLabel = new JLabel("Control de plaza", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        subTitleLabel = new JLabel(" ");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        panel.add(subTitleLabel, gbc);

        subTitleLabel = new JLabel(" ");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(subTitleLabel, gbc);

        reserveSlotButton = new JButton("Reservar Plaza");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        panel.add(reserveSlotButton, gbc);

        reservationsButton = new JButton("Ver reservas");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(reservationsButton, gbc);

        removeReservationButton = new JButton("Retirar Reserva");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        panel.add(removeReservationButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}