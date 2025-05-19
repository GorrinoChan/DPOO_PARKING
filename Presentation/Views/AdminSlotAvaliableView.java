package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de administrador para mostrar las plazas de aparcamiento disponibles.
 *
 * Esta clase representa una interfaz gráfica con una tabla que lista las plazas disponibles.
 * Incluye botones para volver atrás y acceder al perfil del usuario administrador.
 */
public class AdminSlotAvaliableView extends JFrame {

    private JButton returnButton;
    private JButton userProfileButton;
    private JLabel titleLabel;
    private JTable slotTable;
    private JScrollPane scrollPane;

    /**
     * Establece la tabla que se muestra con la información de las plazas disponibles.
     *
     * @param table JTable que contiene los datos de plazas.
     */
    public void setSlotTable(JTable table) {
        this.slotTable = table;
    }

    /**
     * Devuelve el botón de retorno ("<").
     *
     * @return JButton que permite volver a la vista anterior.
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Devuelve el botón del perfil de usuario.
     *
     * @return JButton que abre el perfil del administrador.
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Constructor de la vista de plazas disponibles para el administrador.
     *
     * Configura la ventana con el título "PARKING LS", una tabla de plazas,
     * y botones para retorno y perfil de usuario.
     */
    public AdminSlotAvaliableView() {
        setTitle("Parking LS - AdminSlotAvaliable");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
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
        // Botón de perfil
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
        // Tabla y scroll
        slotTable = new JTable();
        scrollPane = new JScrollPane(slotTable);
        c.add(scrollPane, BorderLayout.CENTER);
        c.add(panel, BorderLayout.NORTH);
    }
}