package Presentation.Controllers;

import Presentation.Views.AdminGraphView;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;


public class AdminGraphController {
    private AdminGraphView admingraphView;
    private AdminMenuView adminMenuView;

    public AdminGraphController(AdminGraphView admingraphView, AdminMenuView adminMenuView) {
        this.admingraphView = admingraphView;
        this.adminMenuView = adminMenuView;
        admingraphView.getReturnButton().addActionListener(e -> returnToMenu());
        admingraphView.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }

    private void openUserProfileView() {
        admingraphView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        admingraphView.dispose();
        adminMenuView.setVisible(true);

    }
}
