package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para que el administrador elimine una plaza de parking existente.
 * Permite introducir el número de la plaza a eliminar y proporciona botones
 * para volver, eliminar o acceder al perfil del usuario.
 */
public class AdminDeleteSlots extends JFrame {

    // Botones de navegación, eliminación y perfil de usuario
    private JButton returnButton, DeleteButton, userProfileButton;

    // Etiquetas para título, campo de entrada y errores
    private JLabel titleLabel, numbereditLabel, errorLabel;

    // Campo de texto para buscar la plaza por número
    private JTextField numbersearchTextField;

    /**
     * Obtiene el texto ingresado en el campo de número de plaza.
     * @return Número de plaza como cadena de texto.
     */
    public String getnumber() { return numbersearchTextField.getText(); }

    /**
     * Devuelve el botón para regresar a la vista anterior.
     * @return JButton del botón de retorno.
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Devuelve el botón para ejecutar la acción de eliminar.
     * @return JButton del botón de eliminación.
     */
    public JButton getDeleteButton() {
        return DeleteButton;
    }

    /**
     * Devuelve el botón para acceder al perfil del usuario.
     * @return JButton del botón de perfil de usuario.
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Establece un mensaje de error visible para el usuario.
     * @param message Mensaje que se desea mostrar.
     */
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    /**
     * Constructor que configura e inicializa la interfaz gráfica
     * para eliminar plazas de aparcamiento.
     */
    public AdminDeleteSlots() {
        // Configuración básica de la ventana
        setTitle("Parking LS - AdminDeleteSlots");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 4, 0);

        // Botón de regreso
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

        // Título de la ventana
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        // Etiqueta de número a eliminar
        numbereditLabel = new JLabel("Numero de Plaza a Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numbereditLabel, gbc);

        // Campo de texto para introducir número
        numbersearchTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numbersearchTextField, gbc);

        // Etiqueta de error
        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(errorLabel, gbc);

        // Botón de eliminación
        DeleteButton = new JButton("Eliminar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(DeleteButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }

}