package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.*;

import javax.swing.*;

public class AdminLogOutController {
    private AdminLogOutView adminLogOutView;
    private AdminMenuView adminMenuView;

    public AdminLogOutController(AdminLogOutView adminLogOutView, AdminMenuView adminMenuView) {
        this.adminLogOutView = adminLogOutView;
        this.adminMenuView = adminMenuView;

        adminLogOutView.getConfirmButton().addActionListener(e -> logOut());
        adminLogOutView.getReturnButton().addActionListener(e -> goBack());
    }

    private void logOut() {
        UserAccountManager userAccountManager = new UserAccountManager();
        userAccountManager.deleteUserName();
        JOptionPane.showMessageDialog(adminLogOutView, "Sesión cerrada correctamente.");
        adminLogOutView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }

    private void goBack() {
        adminLogOutView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }
}