package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminProfileView extends JFrame {
    private JButton logOutButton, returnButton;
    private JLabel titleLabel;

    public JButton getLogOutButton() {
        return logOutButton;
    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public AdminProfileView() {
        setTitle("Parking LS - AdminProfile");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(100, 0, 100, 0);

        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);

        titleLabel = new JLabel("Usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        panel.add(titleLabel, gbc);

        gbc.insets = new Insets(8, 0, 8, 0);
        logOutButton = new JButton("Cerrar Sesión");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(logOutButton, gbc);

        gbc.insets = new Insets(10, 0, 10, 0);
        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(titleLabel, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(titleLabel, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(titleLabel, gbc);

        titleLabel = new JLabel(" ", SwingConstants.CENTER);
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(titleLabel, gbc);

        c.add(panel, BorderLayout.CENTER);
    }
}