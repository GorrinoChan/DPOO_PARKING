package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de administración de plazas de aparcamiento.
 *
 * Esta interfaz permite al administrador gestionar las plazas
 * mediante opciones de crear, editar o eliminar.
 */
public class AdminManagement extends JFrame {

    // Botones de acción y navegación
    private JButton create, Edit, Delete, returnButton, userProfileButton;

    // Etiquetas de título y subtítulo
    private JLabel titleLabel, subTitleLabel;

    /**
     * Devuelve el botón para crear una nueva plaza.
     *
     * @return JButton "Crear"
     */
    public JButton getcreate() {
        return create;
    }

    /**
     * Devuelve el botón para editar una plaza existente.
     *
     * @return JButton "Editar"
     */
    public JButton getEdit() {
        return Edit;
    }

    /**
     * Devuelve el botón para eliminar una plaza.
     *
     * @return JButton "Eliminar"
     */
    public JButton getDelete() {
        return Delete;
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
     * Devuelve el botón del perfil de usuario.
     *
     * @return JButton "User"
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Constructor de la vista de administración.
     *
     * Configura la interfaz gráfica con botones de gestión de plazas:
     * crear, editar y eliminar. Incluye navegación y encabezados.
     */
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

        // Título de la vista
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        // Subtítulo
        subTitleLabel = new JLabel("Managament", SwingConstants.CENTER);
        subTitleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        panel.add(subTitleLabel, gbc);

        // Botón Crear
        create = new JButton("Crear");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(create, gbc);

        // Botón Editar
        Edit = new JButton("Editar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(Edit, gbc);

        // Botón Eliminar
        Delete = new JButton("Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(Delete, gbc);

        // Añadir panel al contenedor principal
        c.add(panel, BorderLayout.CENTER);
    }

}