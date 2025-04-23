package Presentation.Controllers;

import Presentation.Views.*;


public class AdminMenuController {
    private AdminMenuView adminMenuView;

    public AdminMenuController(AdminMenuView adminMenuView) {
        this.adminMenuView = adminMenuView;

        adminMenuView.getSlots().addActionListener(e -> openManagementView());
        adminMenuView.getSimulate().addActionListener(e -> openSimulateView());
        adminMenuView.getslotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        adminMenuView.getgraphButton().addActionListener(e -> openGraphView());
        adminMenuView.getUserProfileButton().addActionListener(e -> openUserProfileView());


    }

    private void openUserProfileView() {
        adminMenuView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
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
