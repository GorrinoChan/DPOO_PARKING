package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista gráfica del administrador para mostrar estadísticas o gráficas relacionadas
 * con el sistema de aparcamiento.
 *
 * Esta clase muestra un encabezado con el nombre del sistema, un botón para regresar
 * a la vista anterior y otro para acceder al perfil del usuario.
 */
public class AdminGraphView extends JFrame {

    // Botones de navegación
    private JButton returnButton, userProfileButton;

    // Etiqueta del título de la vista
    private JLabel titleLabel;

    /**
     * Devuelve el botón de retorno para volver a la vista anterior.
     *
     * @return JButton correspondiente al botón "<"
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Devuelve el botón para acceder al perfil del usuario.
     *
     * @return JButton correspondiente al botón "User"
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Constructor que configura la ventana de vista gráfica.
     * Establece el diseño, los componentes de navegación y el título.
     */
    public AdminGraphView() {
        setTitle("Parking LS - GraphView");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        // Botón para regresar
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

        // Título de la vista
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}