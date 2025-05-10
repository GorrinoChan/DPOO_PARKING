package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class StartView extends JFrame {
    private JButton startButton;
    private JLabel titleLabel;

    public JButton getStartButton() {
        return startButton;
    }

    public StartView() {
        setTitle("Parking LS - Start");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(50, 0, 50, 0);

        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        panel.add(titleLabel, gbc);

        startButton = new JButton("Comenzar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(startButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }


}