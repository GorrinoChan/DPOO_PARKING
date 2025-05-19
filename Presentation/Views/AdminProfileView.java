package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista del perfil de administrador en la aplicación Parking LS.
 *
 * Esta ventana permite al administrador ver su perfil y cerrar sesión.
 * También incluye un botón de retorno para navegar hacia la vista anterior.
 */
public class AdminProfileView extends JFrame {

    // Botones de acción
    private JButton logOutButton, returnButton;

    // Etiqueta de título (usada también como relleno en la parte inferior)
    private JLabel titleLabel;

    /**
     * Devuelve el botón para cerrar sesión.
     *
     * @return JButton "Cerrar Sesión"
     */
    public JButton getLogOutButton() {
        return logOutButton;
    }

    /**
     * Devuelve el botón de retorno.
     *
     * @return JButton "<"
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Constructor de la vista de perfil del administrador.
     *
     * Configura los componentes de la interfaz gráfica, incluyendo el botón de cerrar sesión
     * y el botón de retorno. También incluye espacios de relleno visual con etiquetas vacías.
     */
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
        // Botón de retorno
        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);
        // Título del perfil
        titleLabel = new JLabel("Usuario", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 6;
        panel.add(titleLabel, gbc);
        // Botón de cerrar sesión
        gbc.insets = new Insets(8, 0, 8, 0);
        logOutButton = new JButton("Cerrar Sesión");
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(logOutButton, gbc);
        // Etiquetas vacías para espaciado
        gbc.insets = new Insets(10, 0, 10, 0);
        for (int i = 2; i <= 5; i++) {
            titleLabel = new JLabel(" ", SwingConstants.CENTER);
            gbc.gridy = i;
            panel.add(titleLabel, gbc);
        }
        c.add(panel, BorderLayout.CENTER);
    }
}