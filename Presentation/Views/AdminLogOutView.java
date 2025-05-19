package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de cierre de sesión para el administrador.
 *
 * Muestra una interfaz simple donde se pregunta al usuario si desea cerrar sesión,
 * junto con botones de confirmación y retorno.
 */
public class AdminLogOutView extends JFrame {

    // Botones de navegación y confirmación
    private JButton returnButton, confirmButton;

    // Etiquetas de título y subtítulo
    private JLabel titleLabel, subTitleLabel;

    /**
     * Devuelve el botón de confirmación para cerrar sesión.
     *
     * @return JButton "Confirmar"
     */
    public JButton getConfirmButton() {
        return confirmButton;
    }

    /**
     * Devuelve el botón para volver a la vista anterior.
     *
     * @return JButton "<"
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Constructor de la vista de cierre de sesión.
     *
     * Configura la ventana con un diseño centrado, mensaje de confirmación y
     * los botones necesarios.
     */
    public AdminLogOutView() {
        setTitle("Parking LS - LogOut");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(30, 0, 30, 0);
        // Botón de retorno
        returnButton = new JButton("<");
        returnButton.setBorderPainted(false);
        returnButton.setContentAreaFilled(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(returnButton, gbc);
        // Título principal
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);
        // Subtítulo con la pregunta
        subTitleLabel = new JLabel("¿Quieres cerrar sessión?", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(subTitleLabel, gbc);
        // Botón de confirmación
        confirmButton = new JButton("Confirmar");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(confirmButton, gbc);
        c.add(panel, BorderLayout.CENTER);
    }
}