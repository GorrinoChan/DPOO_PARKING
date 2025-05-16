package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.*;

import javax.swing.*;

/**
 * Controlador que se encarga de manejar la vista para crear nuevas plazas de aparcamiento
 * desde la interfaz del administrador.
 * <p>
 * Esta clase escucha los botones de la vista {@link AdminCreateSlot} y realiza las acciones
 * correspondientes, como crear una nueva plaza o volver al menú.
 */
public class AdminCreateSlotController {

    private AdminCreateSlot adminCreateSlot;
    private AdminMenuView adminMenuView;

    /**
     * Constructor del controlador. Recibe la vista para crear la plaza y la vista del menú del admin.
     * Aquí también se añaden los listeners a los botones de la vista.
     *
     * @param adminCreateSlot Vista para crear una nueva plaza.
     * @param adminMenuView   Vista del menú del administrador.
     */
    public AdminCreateSlotController(AdminCreateSlot adminCreateSlot, AdminMenuView adminMenuView) {
        this.adminCreateSlot = adminCreateSlot;
        this.adminMenuView = adminMenuView;

        // Se asignan los listeners a los botones
        adminCreateSlot.getReturnButton().addActionListener(e -> returnToMenu());
        adminCreateSlot.getCreateButton().addActionListener(e -> createSlot());
        adminCreateSlot.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    /**
     * Método privado que se ejecuta cuando el usuario pulsa el botón de crear plaza.
     * Valida los datos introducidos y llama al manager para intentar crear la plaza.
     */
    private void createSlot() {
        String slotNumberText = adminCreateSlot.getnumber();
        String floorNumberText = adminCreateSlot.getfloaat();
        String typeOfPlace = adminCreateSlot.gettipe();

        if (slotNumberText.isEmpty() || floorNumberText.isEmpty() || typeOfPlace == null || typeOfPlace.isEmpty()) {
            adminCreateSlot.setErrorMessage("Todos los campos son obligatorios.");
            return;
        }

        try {
            int slotNumber = Integer.parseInt(slotNumberText.trim());
            int floorNumber = Integer.parseInt(floorNumberText.trim());

            AdminSlotManager slotManager = new AdminSlotManager();

            if (slotManager.parkingSlotAlreadyExists(slotNumber)) {
                adminCreateSlot.setErrorMessage("Ya existe una plaza con ese número.");
            } else {
                boolean created = slotManager.createNewParkingSlot(floorNumber, slotNumber, typeOfPlace);
                if (created) {
                    adminCreateSlot.setErrorMessage("");
                    JOptionPane.showMessageDialog(adminCreateSlot, "Plaza creada correctamente.");
                    adminCreateSlot.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement, adminMenuView);
                    adminManagement.setVisible(true);
                } else {
                    adminCreateSlot.setErrorMessage("Error al crear la plaza.");
                }
            }
        } catch (NumberFormatException e) {
            adminCreateSlot.setErrorMessage("Los campos numéricos deben contener números válidos.");
        }
    }

    /**
     * Abre la vista del perfil del administrador y cierra la vista actual.
     */
    private void openUserProfileView() {
        adminCreateSlot.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Cierra la vista actual y vuelve al menú principal de administración.
     */
    private void returnToMenu() {
        adminCreateSlot.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement, adminMenuView);
        adminManagement.setVisible(true);
    }
}