package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.AdminEditSlots;
import Presentation.Views.AdminManagement;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;

import javax.swing.*;

/**
 * Controlador que se encarga de gestionar la edición de plazas de aparcamiento por parte del administrador.
 * <p>
 * Permite modificar el número, la planta o el tipo de plaza si esta ya existe en el sistema.
 */
public class AdminEditSlotsController {
    private AdminEditSlots adminEditSlots;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que inicializa la vista de edición de plazas y asigna las acciones a los botones.
     *
     * @param adminEditSlots Vista para editar plazas.
     * @param adminMenuView  Vista del menú principal del administrador.
     */
    public AdminEditSlotsController(AdminEditSlots adminEditSlots, AdminMenuView adminMenuView) {
        this.adminEditSlots = adminEditSlots;
        this.adminMenuView = adminMenuView;
        adminEditSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminEditSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
        adminEditSlots.getEditButton().addActionListener(e -> EditSlot());
    }

    /**
     * Método que se encarga de editar una plaza.
     * Comprueba que los campos sean válidos, y si la plaza existe, intenta actualizarla con los nuevos datos.
     */
    private void EditSlot() {
        String slotNumbersearchText = adminEditSlots.getnumber1(); // número actual
        String slotNumberText = adminEditSlots.getnumber2();       // número nuevo
        String floorNumberText = adminEditSlots.getfloat();
        String typeOfPlace = adminEditSlots.gettipe();

        if (slotNumberText.isEmpty() || slotNumbersearchText.isEmpty() ||
                floorNumberText.isEmpty() || typeOfPlace == null || typeOfPlace.isEmpty()) {
            adminEditSlots.setErrorMessage("Todos los campos son obligatorios.");
            return;
        }

        try {
            int slotNumbersearch = Integer.parseInt(slotNumbersearchText.trim());
            int slotNumber = Integer.parseInt(slotNumberText.trim());
            int floorNumber = Integer.parseInt(floorNumberText.trim());

            AdminSlotManager slotManager = new AdminSlotManager();

            if (slotManager.parkingSlotAlreadyExists(slotNumbersearch)) {
                boolean edit = slotManager.updateParkingSlot(slotNumbersearch, floorNumber, slotNumber, typeOfPlace);
                if (edit) {
                    adminEditSlots.setErrorMessage("");
                    JOptionPane.showMessageDialog(adminEditSlots, "Plaza editada correctamente.");
                    adminEditSlots.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement, adminMenuView);
                    adminManagement.setVisible(true);
                } else {
                    adminEditSlots.setErrorMessage("Error al editar la plaza.");
                }
            } else {
                adminEditSlots.setErrorMessage("No existe una plaza con ese número.");
            }
        } catch (NumberFormatException e) {
            adminEditSlots.setErrorMessage("Los campos numéricos deben contener números válidos.");
        }
    }

    /**
     * Abre la vista del perfil del administrador.
     */
    private void openUserProfileView() {
        adminEditSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Vuelve al menú principal de administración.
     */
    private void returnToMenu() {
        adminEditSlots.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement, adminMenuView);
        adminManagement.setVisible(true);
    }
}