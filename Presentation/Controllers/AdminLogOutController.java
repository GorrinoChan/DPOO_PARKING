package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.*;

import javax.swing.*;

/**
 * Controlador que gestiona el cierre de sesión del administrador desde la vista de confirmación.
 * <p>
 * Permite al administrador cerrar la sesión o regresar al perfil si decide no continuar con el cierre.
 */
public class AdminLogOutController {
    private AdminLogOutView adminLogOutView;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que configura los botones para confirmar el cierre de sesión o volver atrás.
     *
     * @param adminLogOutView Vista que muestra la confirmación de cierre de sesión.
     * @param adminMenuView Vista del menú principal del administrador.
     */
    public AdminLogOutController(AdminLogOutView adminLogOutView, AdminMenuView adminMenuView) {
        this.adminLogOutView = adminLogOutView;
        this.adminMenuView = adminMenuView;

        adminLogOutView.getConfirmButton().addActionListener(e -> logOut());
        adminLogOutView.getReturnButton().addActionListener(e -> goBack());
    }

    /**
     * Elimina el nombre de usuario guardado, cierra la vista actual
     * y redirige al usuario a la pantalla inicial (login/registro).
     */
    private void logOut() {
        UserAccountManager userAccountManager = new UserAccountManager();
        userAccountManager.deleteUserName();
        JOptionPane.showMessageDialog(adminLogOutView, "Sesión cerrada correctamente.");
        adminLogOutView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }

    /**
     * Cierra la vista actual y vuelve al perfil del administrador sin cerrar sesión.
     */
    private void goBack() {
        adminLogOutView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }
}