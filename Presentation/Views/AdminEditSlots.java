package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista gráfica para que el administrador edite una plaza de aparcamiento existente.
 * Permite buscar una plaza por número, modificar sus datos (número, piso, tipo de vehículo)
 * y navegar a otras vistas relacionadas como el menú anterior o el perfil de usuario.
 */
public class AdminEditSlots extends JFrame {

    // Botones para navegación, edición y perfil de usuario
    private JButton returnButton, EditButton, userProfileButton;

    // Etiquetas de texto para campos y mensajes
    private JLabel numberLabel, floatLabel, tipeLabel, titleLabel, numbereditLabel, errorLabel;

    // Campos de texto para entrada del usuario
    private JTextField numberTextField, floatTextField, tipeTextField, numbersearchTextField;

    /**
     * Obtiene el número de plaza que se desea buscar y editar.
     * @return Número de plaza a editar como cadena.
     */
    public String getNumberOfSlotThatIsGoingToBeSearchAndEdit() {
        return numbersearchTextField.getText();
    }

    /**
     * Obtiene el nuevo número que se desea asignar a la plaza.
     * @return Nuevo número como cadena.
     */
    public String getNumberOfSlotThatIsGoingToBeAssigned() {
        return numberTextField.getText();
    }

    /**
     * Obtiene el nuevo piso que se desea asignar a la plaza.
     * @return Nuevo piso como cadena.
     */
    public String getFloorOfSlot() {
        return floatTextField.getText();
    }

    /**
     * Obtiene el nuevo tipo de vehículo que se desea asignar a la plaza.
     * @return Tipo de vehículo como cadena.
     */
    public String getTypeOfVehicleThatIsGoingToBeAssign() {
        return tipeTextField.getText();
    }

    /**
     * Devuelve el botón para volver a la vista anterior.
     * @return JButton del botón de retorno.
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Establece un mensaje de error visible en la interfaz.
     * @param message Texto del mensaje de error.
     */
    public void setErrorMessage(String message) {
        errorLabel.setText(message);
    }

    /**
     * Devuelve el botón que activa la edición de una plaza.
     * @return JButton del botón de edición.
     */
    public JButton getEditButton() {
        return EditButton;
    }

    /**
     * Devuelve el botón para acceder al perfil del usuario administrador.
     * @return JButton del botón de perfil de usuario.
     */
    public JButton getUserProfileButton() {
        return userProfileButton;
    }

    /**
     * Constructor que configura la ventana de edición de plazas de aparcamiento.
     * Inicializa todos los componentes de la interfaz gráfica.
     */
    public AdminEditSlots() {
        setTitle("Parking LS - AdminEditSlots");
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
        // Título de la ventana
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);
        // Etiqueta y campo para número a editar
        numbereditLabel = new JLabel("Numero de Plaza a Editar");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(numbereditLabel, gbc);
        numbersearchTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(numbersearchTextField, gbc);
        // Etiqueta y campo para nuevo número
        numberLabel = new JLabel("Numero");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        panel.add(numberLabel, gbc);
        numberTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panel.add(numberTextField, gbc);
        // Etiqueta y campo para nuevo piso
        floatLabel = new JLabel("Piso");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panel.add(floatLabel, gbc);
        floatTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panel.add(floatTextField, gbc);
        // Etiqueta y campo para nuevo tipo de vehículo
        tipeLabel = new JLabel("Tipo de vehiculo");
        gbc.gridx = 1;
        gbc.gridy = 7;
        panel.add(tipeLabel, gbc);
        tipeTextField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 8;
        panel.add(tipeTextField, gbc);
        // Etiqueta de error
        errorLabel = new JLabel(" ", SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        gbc.gridx = 1;
        gbc.gridy = 9;
        panel.add(errorLabel, gbc);
        // Botón de edición
        EditButton = new JButton("Editar");
        gbc.gridx = 1;
        gbc.gridy = 10;
        panel.add(EditButton, gbc);
        c.add(panel, BorderLayout.CENTER);
    }

}