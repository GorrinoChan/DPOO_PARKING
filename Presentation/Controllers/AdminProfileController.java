package Presentation.Controllers;

import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;
import Presentation.Views.LogOutView;
import Presentation.Views.UserMenuView;

public class AdminProfileController {
    private AdminProfileView adminProfileView;
    private AdminMenuView adminMenuView;

    public AdminProfileController(AdminProfileView adminProfileView, AdminMenuView adminMenuView) {
        this.adminProfileView = adminProfileView;
        this.adminMenuView = adminMenuView;

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
        adminMenuView.setVisible(true);

    }
}