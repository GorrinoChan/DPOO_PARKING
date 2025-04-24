package Presentation.Controllers;

import Presentation.Views.*;


public class AdminCreateSlotController {
    private AdminCreateSlot adminCreateSlot;

    public AdminCreateSlotController(AdminCreateSlot adminCreateSlot) {
        this.adminCreateSlot = adminCreateSlot;
        adminCreateSlot.getReturnButton().addActionListener(e -> returnToMenu());
        adminCreateSlot.getCreateButton().addActionListener(e -> opencreateButton());
        adminCreateSlot.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }
    private void openUserProfileView() {
        adminCreateSlot.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void opencreateButton() {
        adminCreateSlot.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }
    private void returnToMenu() {
        adminCreateSlot.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}