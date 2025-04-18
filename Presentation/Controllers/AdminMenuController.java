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
        SlotAvaliableView slotAvaliableView = new SlotAvaliableView();
        new SlotAvaliableController(slotAvaliableView);
        slotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        adminMenuView.dispose();
        GraphView graphView = new GraphView();
        new GraphController(graphView);
        graphView.setVisible(true);
    }
}
