package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminManagement extends JFrame {
    private JButton create, Edit, Delete,returnButton,userProfileButton;
    private JLabel titleLabel, subTitleLabel;

    public JButton getcreate() { return create; }
    public JButton getEdit() { return Edit; }
    public JButton getDelete() { return Delete; }
    public JButton getReturnButton() { return returnButton; }
    public JButton getUserProfileButton() {
        return userProfileButton;
    }


    public AdminManagement() {
        setTitle("Parking LS - AdminManagament");
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

        subTitleLabel = new JLabel("Managament", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        create = new JButton("Crear");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(create, gbc);

        Edit = new JButton("Editar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Edit, gbc);

        Delete = new JButton("Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(Delete, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}
