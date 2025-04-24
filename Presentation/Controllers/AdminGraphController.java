package Presentation.Controllers;

import Presentation.Views.AdminGraphView;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;


public class AdminGraphController {
    private AdminGraphView admingraphView;

    public AdminGraphController(AdminGraphView admingraphView) {
        this.admingraphView = admingraphView;
        admingraphView.getReturnButton().addActionListener(e -> returnToMenu());
        admingraphView.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }

    private void openUserProfileView() {
        admingraphView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        admingraphView.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}
