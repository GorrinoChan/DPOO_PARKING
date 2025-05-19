package Presentation.Controllers;

import Presentation.Views.*;

/**
 * Controlador que gestiona la navegación desde la vista de gestión de plazas para el administrador.
 * <p>
 * Permite acceder a las funcionalidades de crear, editar o eliminar plazas de aparcamiento,
 * así como volver al menú principal o al perfil del usuario.
 */
public class AdminManagementController {
    private AdminManagement adminManagement;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que configura los listeners para los botones de gestión de plazas.
     *
     * @param adminManagement Vista de gestión de plazas.
     * @param adminMenuView   Vista principal del menú del administrador.
     */
    public AdminManagementController(AdminManagement adminManagement, AdminMenuView adminMenuView) {
        this.adminManagement = adminManagement;
        this.adminMenuView = adminMenuView;
        adminManagement.getCreate().addActionListener(e -> openAdminCreate());
        adminManagement.getEdit().addActionListener(e -> openAdminEdit());
        adminManagement.getDelete().addActionListener(e -> openAdminDelete());
        adminManagement.getReturnButton().addActionListener(e -> returnToMenu());
        adminManagement.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    /**
     * Abre la vista del perfil del administrador.
     */
    private void openUserProfileView() {
        adminManagement.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Abre la vista para crear una nueva plaza.
     */
    private void openAdminCreate() {
        adminManagement.dispose();
        AdminCreateSlot adminCreateSlot = new AdminCreateSlot();
        new AdminCreateSlotController(adminCreateSlot, adminMenuView);
        adminCreateSlot.setVisible(true);
    }

    /**
     * Abre la vista para editar una plaza existente.
     */
    private void openAdminEdit() {
        adminManagement.dispose();
        AdminEditSlots adminEditSlots = new AdminEditSlots();
        new AdminEditSlotsController(adminEditSlots, adminMenuView);
        adminEditSlots.setVisible(true);
    }

    /**
     * Abre la vista para eliminar una plaza.
     */
    private void openAdminDelete() {
        adminManagement.dispose();
        AdminDeleteSlots adminDeleteSlots = new AdminDeleteSlots();
        new AdminDeleteSlotsController(adminDeleteSlots, adminMenuView);
        adminDeleteSlots.setVisible(true);
    }

    /**
     * Regresa al menú principal del administrador.
     */
    private void returnToMenu() {
        adminManagement.dispose();
        adminMenuView.setVisible(true);
    }
}