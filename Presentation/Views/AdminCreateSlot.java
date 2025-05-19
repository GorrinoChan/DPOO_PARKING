package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista para la creación de nuevas plazas de parking por parte del administrador.
 * Contiene campos de entrada para número de plaza, piso y tipo de vehículo, así como
 * botones para volver, crear y acceder al perfil de usuario.
 */
public class AdminCreateSlot extends JFrame {

    // Botón para regresar a la vista anterior
    private JButton returnButton, createButton, userProfileButton;

    // Etiquetas para los campos de entrada y título
    private JLabel numberLabel, floatLabel, tipeLabel, titleLabel, errorLabel;

    // Campos de texto para entrada del número, piso y tipo de vehículo
    private JTextField numberTextField, floatTextField, tipeTextField;

    /**
     * Obtiene el botón para regresar a la vista anterior.
     * @return JButton de retorno.
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Obtiene el botón para acceder al perfil del usuario.
     * @return JButton de perfil de usuario.
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Obtiene el botón para crear la plaza.
     * @return JButton de creación.
     */
    public JButton getCreateButton() {
        return createButton;
    }

    /**
     * Obtiene el valor del campo de texto del número de plaza.
     * @return Texto del número de plaza.
     */
    public String getnumber() { return numberTextField.getText(); }

    /**
     * Obtiene el valor del campo de texto del piso.
     * @return Texto del piso.
     */
    public String getfloaat() { return floatTextField.getText(); }

    /**
     * Obtiene el valor del campo de texto del tipo de vehículo.
     * @return Texto del tipo de vehículo.
     */
    public String gettipe() { return tipeTextField.getText(); }

    /**
     * Establece el mensaje de error a mostrar en la vista.
     * @param message Mensaje de error.
     */
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    /**
     * Constructor que configura e inicializa la interfaz gráfica para la creación de plazas.
     */
    public AdminCreateSlot() {
        // Configuración de la ventana
        setTitle("Parking LS - AdminCreateSlot");
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

        // Etiqueta y campo del número
        numberLabel = new JLabel("Numero");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numberLabel, gbc);

        numberTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numberTextField, gbc);

        // Etiqueta y campo del piso
        floatLabel = new JLabel("Piso");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(floatLabel, gbc);

        floatTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(floatTextField, gbc);

        // Etiqueta y campo del tipo de vehículo
        tipeLabel = new JLabel("Tipo de vehiculo");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(tipeLabel, gbc);

        tipeTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(tipeTextField, gbc);

        // Etiqueta de error
        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(errorLabel, gbc);

        // Botón de creación
        createButton = new JButton("Crear");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(createButton, gbc);

        c.add(panel, BorderLayout.CENTER);
    }
}