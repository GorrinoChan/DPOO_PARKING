package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista principal del menú de administrador en la aplicación Parking LS.
 *
 * Esta interfaz proporciona acceso a funciones clave como la gestión de plazas,
 * visualización de disponibilidad, gráficas y control del sistema (pausar/reanudar).
 */
public class AdminMenuView extends JFrame {

    // Botones de acción del menú
    private JButton Slots, playPauseButton, slotAvaliableButton, graphButton, userProfileButton;

    // Etiquetas de título
    private JLabel titleLabel, subTitleLabel;

    /**
     * Devuelve el botón para acceder a la gestión de plazas.
     *
     * @return JButton "Gestion de las plazas"
     */
    public JButton getSlots() {
        return Slots;
    }

    /**
     * Devuelve el botón de play/pausa del sistema.
     *
     * @return JButton "Play" o "Pause"
     */
    public JButton getPlayPauseButton() {
        return playPauseButton;
    }

    /**
     * Devuelve el botón para ver las plazas disponibles.
     *
     * @return JButton "Ver plazas disponibles"
     */
    public JButton getslotAvaliableButton() {
        return slotAvaliableButton;
    }

    /**
     * Devuelve el botón para ver el gráfico de la última hora.
     *
     * @return JButton "Gráfico de última hora"
     */
    public JButton getgraphButton() {
        return graphButton;
    }

    /**
     * Devuelve el botón del perfil de usuario.
     *
     * @return JButton "User"
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Constructor de la vista del menú de administrador.
     *
     * Configura los elementos visuales y de interacción para
     * acceder a las distintas funciones administrativas.
     */
    public AdminMenuView() {
        setTitle("Parking LS - AdminMenu");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);
        // Botón de play/pause
        playPauseButton = new JButton("Play");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 0, 4, 20);
        panel.add(playPauseButton, gbc);
        // Botón de perfil de usuario
        userProfileButton = new JButton("User");
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(4, 20, 4, 0);
        panel.add(userProfileButton, gbc);
        // Título principal
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);
        // Subtítulo
        subTitleLabel = new JLabel("Menu", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);
        // Botón de gestión de plazas
        Slots = new JButton("Gestion de las plazas");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(Slots, gbc);
        // Botón de plazas disponibles
        slotAvaliableButton = new JButton("Ver plazas disponibles");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(slotAvaliableButton, gbc);
        // Botón de gráfica de última hora
        graphButton = new JButton("Gráfico de última hora");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(graphButton, gbc);
        // Añadir panel al contenedor
        c.add(panel, BorderLayout.CENTER);
    }

}