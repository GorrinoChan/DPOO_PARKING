package Presentation.Controllers;

import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminManagement;
import Presentation.Views.AdminSimulation;
import Presentation.Views.AdminSlotAvaliableView;
import Presentation.Views.AdminGraphView;



public class AdminMenuController {
    private AdminMenuView adminMenuView;

    public AdminMenuController(AdminMenuView adminMenuView) {
        this.adminMenuView = adminMenuView;

        adminMenuView.getSlots().addActionListener(e -> openManagementView());
        adminMenuView.getSimulate().addActionListener(e -> openSimulateView());
        adminMenuView.getslotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        adminMenuView.getgraphButton().addActionListener(e -> openGraphView());

    }
    private void openManagementView() {
        adminMenuView.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }

    private void openSimulateView() {
        adminMenuView.dispose();
        AdminSimulation adminSimulation = new AdminSimulation();
        new AdminSimulationController(adminSimulation);
        adminSimulation.setVisible(true);
    }

    private void openSlotAvaliableView() {
        adminMenuView.dispose();
        AdminSlotAvaliableView adminslotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminslotAvaliableView);
        adminslotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        adminMenuView.dispose();
        AdminGraphView admingraphView = new AdminGraphView();
        new AdminGraphController(admingraphView);
        admingraphView.setVisible(true);
    }
}
