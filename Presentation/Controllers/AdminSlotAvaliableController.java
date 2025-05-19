package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;
import Presentation.Views.AdminSlotAvaliableView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Controlador para la vista de plazas disponibles del administrador.
 * <p>
 * Gestiona la visualización de las plazas de parking y las acciones del usuario
 * como navegar al perfil o ver detalles de una plaza específica.
 */
public class AdminSlotAvaliableController {
    private AdminSlotAvaliableView adminslotAvaliableView;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que inicializa el controlador y configura los listeners.
     *
     * @param adminslotAvaliableView Vista de plazas disponibles.
     * @param adminMenuView          Vista principal del menú del administrador.
     */
    public AdminSlotAvaliableController(AdminSlotAvaliableView adminslotAvaliableView, AdminMenuView adminMenuView) {
        this.adminslotAvaliableView = adminslotAvaliableView;
        this.adminMenuView = adminMenuView;
        adminslotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        adminslotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        mostrarTablaParking();
    }

    /**
     * Muestra la tabla con la información de todas las plazas de parking.
     * Cada fila incluye número, planta, tipo de vehículo, estado de ocupación,
     * estado de reserva y matrícula si está ocupada.
     * <p>
     * Al hacer clic en una fila, se muestra una vista con más información sobre la plaza.
     */
    private void mostrarTablaParking() {
        String[] columns = {"Numero", "Planta", "Tipo Vehículo", "Ocupación", "Reserva", "Matrícula"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            List<String> infoOfAllSlots = adminSlotManager.allSlotsAndReservationInformationForTable();
            for (String lineOfInfo : infoOfAllSlots) {
                String[] parts = lineOfInfo.split("/");
                String numberOfSlot = parts[1];
                String floorOfSlot = parts[0];
                String licensePlateOfVehicleInSlot = parts[2];
                String typeOfVehicle = parts[3];
                String occupationState = parts[4];
                String occupation;
                String reservation;
                if (licensePlateOfVehicleInSlot.equals("FREE")) {
                    occupation = "Libre";
                    reservation = "Disponible";
                    licensePlateOfVehicleInSlot = "";
                } else {
                    if (occupationState.equals("true")) {
                        occupation = "Ocupado";
                        reservation = "Reservado";
                    } else {
                        occupation = "Libre";
                        reservation = "Reservado";
                    }
                }
                Object[] row = {
                        numberOfSlot,
                        floorOfSlot,
                        typeOfVehicle,
                        occupation,
                        reservation,
                        licensePlateOfVehicleInSlot
                };
                model.addRow(row);
            }
            JTable tabla = new JTable(model);
            this.adminslotAvaliableView.setSlotTable(tabla);
            JScrollPane scrollPane = new JScrollPane(tabla);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
            adminslotAvaliableView.getContentPane().add(scrollPane);
            adminslotAvaliableView.revalidate();
            adminslotAvaliableView.repaint();
            // Listener para ver detalles al hacer clic en una fila
            tabla.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 1) {
                        int selectedRow = tabla.getSelectedRow();
                        adminslotAvaliableView.dispose();
                        AdminInfoSlots infoView = new AdminInfoSlots(selectedRow);
                        new AdminInfoSlotsController(infoView, adminMenuView);
                        infoView.setVisible(true);
                    }
                }
            });
        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminslotAvaliableView, "Error al cargar la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Abre la vista del perfil del administrador.
     */
    private void openUserProfileView() {
        adminslotAvaliableView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Regresa al menú principal del administrador.
     */
    private void returnToMenu() {
        adminslotAvaliableView.dispose();
        adminMenuView.setVisible(true);
    }
}