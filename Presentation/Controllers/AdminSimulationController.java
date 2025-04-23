package Presentation.Controllers;

import Presentation.Views.AdminProfileView;
import Presentation.Views.AdminSimulation;
import Presentation.Views.AdminMenuView;


public class AdminSimulationController {
    private AdminSimulation adminSimulation;

    public AdminSimulationController(AdminSimulation adminSimulation) {
        this.adminSimulation = adminSimulation;
        adminSimulation.getReturnButton().addActionListener(e -> returnToMenu());
        adminSimulation.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }

    private void openUserProfileView() {
        adminSimulation.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        adminSimulation.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}