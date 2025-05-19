package Presentation.Controllers;

import Presentation.Views.*;

/**
 * Controlador para la vista del perfil del administrador.
 * <p>
 * Gestiona las acciones del usuario en el perfil, como cerrar sesión o volver al menú principal.
 */
public class AdminProfileController {
    private AdminProfileView adminProfileView;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que inicializa el controlador y asigna acciones a los botones de la vista.
     *
     * @param adminProfileView Vista del perfil del administrador.
     * @param adminMenuView    Vista principal del menú del administrador.
     */
    public AdminProfileController(AdminProfileView adminProfileView, AdminMenuView adminMenuView) {
        this.adminProfileView = adminProfileView;
        this.adminMenuView = adminMenuView;
        adminProfileView.getLogOutButton().addActionListener(e -> openAdminLogOutView());
        adminProfileView.getReturnButton().addActionListener(e -> returnToPreviousView());
    }

    /**
     * Abre la vista de cierre de sesión del administrador.
     */
    private void openAdminLogOutView() {
        adminProfileView.dispose();
        AdminLogOutView adminLogOutView = new AdminLogOutView();
        new AdminLogOutController(adminLogOutView, adminMenuView);
        adminLogOutView.setVisible(true);
    }

    /**
     * Regresa al menú principal del administrador.
     */
    private void returnToPreviousView() {
        adminProfileView.dispose();
        adminMenuView.setVisible(true);
    }
}