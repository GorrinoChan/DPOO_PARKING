package Presentation.Views;

import javax.swing.*;
import java.awt.*;

/**
 * Vista de información de plazas para el administrador.
 *
 * Muestra una tabla con información sobre las plazas de aparcamiento, incluyendo
 * controles de navegación y botones para cancelar o ver detalles.
 */
public class AdminInfoSlots extends JFrame {

    // Botones de navegación y acción
    private JButton returnButton;
    private JButton cancelButton, userProfileButton;

    // Etiqueta del título
    private JLabel titleLabel;

    // Fila seleccionada previamente (útil para seleccionar datos)
    private int selectedRow;

    // Tabla de datos y su contenedor con scroll
    private JTable slotTable;
    private JScrollPane scrollPane;

    /**
     * Establece la tabla que se visualizará en el scroll pane.
     *
     * @param table JTable que contiene la información de las plazas
     */
    public void setSlotTable(JTable table) {
        this.slotTable = table;
    }

    /**
     * Devuelve el botón para regresar a la vista anterior.
     *
     * @return JButton "<"
     */
    public JButton getReturnButton() {
        return returnButton;
    }

    /**
     * Devuelve el índice de la fila seleccionada.
     *
     * @return índice entero de la fila seleccionada
     */
    public int getSelectedRow() {
        return selectedRow;
    }

    /**
     * Devuelve el botón de cancelación.
     *
     * @return JButton "Cancel"
     */
    public JButton getCancelButton() {
        return cancelButton;
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
     * Constructor de la vista que muestra información detallada de plazas.
     *
     * @param selectedRow la fila seleccionada previamente en otra vista
     */
    public AdminInfoSlots(int selectedRow) {
        this.selectedRow = selectedRow;  // Asignamos la fila seleccionada
        setTitle("Parking LS - AdminInfoSlots");
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

        // Título
        gbc.insets = new Insets(4, 0, 4, 0);
        titleLabel = new JLabel("PARKING LS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        panel.add(titleLabel, gbc);

        // Botón de cancelación
        cancelButton = new JButton("Cancel");
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(cancelButton, gbc);

        c.add(panel, BorderLayout.NORTH);

        // Tabla vacía por defecto
        slotTable = new JTable();
        scrollPane = new JScrollPane(slotTable);
        c.add(scrollPane, BorderLayout.CENTER);
    }

}