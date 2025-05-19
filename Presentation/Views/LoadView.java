package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class LoadView extends JFrame {
    private JLabel titleLabel, loadLabel;

    public void setErrorMessage(String message) {
        loadLabel.setText(message);
        loadLabel.setForeground(Color.RED);
    }

    public LoadView() {
        setTitle("Parking LS - Load");
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

        loadLabel = new JLabel("Cargando...", SwingConstants.CENTER);
        loadLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(loadLabel, gbc);

        c.add(panel, BorderLayout.CENTER);
    }


}