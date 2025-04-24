package Presentation.Controllers;

import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;
import Presentation.Views.LogOutView;

public class AdminProfileController {
    private AdminProfileView adminProfileView;

    public AdminProfileController(AdminProfileView adminProfileView) {
        this.adminProfileView = adminProfileView;

        adminProfileView.getLogOutButton().addActionListener(e -> openLogOutView());
        adminProfileView.getReturnButton().addActionListener(e -> returnToPreviousView());
    }

    private void openLogOutView() {
        adminProfileView.dispose();
        LogOutView logOutView = new LogOutView();
        new LogOutController(logOutView);
        logOutView.setVisible(true);
    }

    private void returnToPreviousView() {
        adminProfileView.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}