package Presentation.Controllers;

import Presentation.Views.AdminSimulation;
import Presentation.Views.AdminMenuView;


public class AdminSimulationController {
    private AdminSimulation adminSimulation;

    public AdminSimulationController(AdminSimulation adminSimulation) {
        this.adminSimulation = adminSimulation;
        adminSimulation.getReturnButton().addActionListener(e -> returnToMenu());
    }

    private void returnToMenu() {
        adminSimulation.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}