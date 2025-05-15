package Presentation.Controllers;

import Presentation.Views.*;

public class AdminProfileController {
    private AdminProfileView adminProfileView;
    private AdminMenuView adminMenuView;

    public AdminProfileController(AdminProfileView adminProfileView, AdminMenuView adminMenuView) {
        this.adminProfileView = adminProfileView;
        this.adminMenuView = adminMenuView;

        adminProfileView.getLogOutButton().addActionListener(e -> openAdminLogOutView());
        adminProfileView.getReturnButton().addActionListener(e -> returnToPreviousView());
    }

    private void openAdminLogOutView() {
        adminProfileView.dispose();
        AdminLogOutView adminLogOutView = new AdminLogOutView();
        new AdminLogOutController(adminLogOutView, adminMenuView);
        adminLogOutView.setVisible(true);
    }

    private void returnToPreviousView() {
        adminProfileView.dispose();
        adminMenuView.setVisible(true);

    }
}