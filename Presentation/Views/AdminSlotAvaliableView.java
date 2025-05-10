package Presentation.Views;

import javax.swing.*;
import java.awt.*;

public class AdminSlotAvaliableView extends JFrame {
    private JButton returnButton;
    private JButton infoButton, userProfileButton;
    private JLabel titleLabel;
    private JTable slotTable; // Tabla de slots
    private JScrollPane scrollPane; // ScrollPane para la tabla

    // Métodos getter para los botones
    public JButton getReturnButton() {
        return returnButton;
    }

    public JButton getInfoButton() {
        return infoButton;
    }

    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    public JTable getSlotTable() {
        return slotTable;
    }

    public AdminSlotAvaliableView() {
        // Configuración básica de la ventana
        setTitle("Parking LS - AdminSlotAvaliableView");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Contenedor principal
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // Panel superior para los botones y título
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        // Botón de retorno
        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);

        // Botón de perfil de usuario
        userProfileButton = new JButton("User");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(userProfileButton, gbc);

        // Título
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        // Botón de información
        infoButton = new JButton("Informacion");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(infoButton, gbc);

        // Agregar panel al contenedor
        c.add(panel, BorderLayout.NORTH); // Lo añadimos al norte

        // Crear la tabla (sin datos por ahora)
        slotTable = new JTable();
        scrollPane = new JScrollPane(slotTable);
        c.add(scrollPane, BorderLayout.CENTER); // Añadir la tabla con scroll en el centro
    }
}