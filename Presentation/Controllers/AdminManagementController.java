package Presentation.Controllers;

import Presentation.Views.*;


public class AdminManagementController {
    private AdminManagement adminManagement;

    public AdminManagementController(AdminManagement adminManagement) {
        this.adminManagement = adminManagement;

        adminManagement.getcreate().addActionListener(e -> openAdminCreate());
        adminManagement.getEdit().addActionListener(e -> openAdminEdit());
        adminManagement.getDelete().addActionListener(e -> openAdminDelete());
        adminManagement.getReturnButton().addActionListener(e -> returnToMenu());
        adminManagement.getUserProfileButton().addActionListener(e -> openUserProfileView());


    }
    private void openUserProfileView() {
        adminManagement.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void openAdminCreate() {
        adminManagement.dispose();
        AdminCreateSlot adminCreateSlot = new AdminCreateSlot();
        new AdminCreateSlotController(adminCreateSlot);
        adminCreateSlot.setVisible(true);
    }

    private void openAdminEdit() {
        adminManagement.dispose();
        AdminEditSlots adminEditSlots = new AdminEditSlots();
        new AdminEditSlotsController(adminEditSlots);
        adminEditSlots.setVisible(true);
    }

    private void openAdminDelete() {
        adminManagement.dispose();
        AdminDeleteSlots adminDeleteSlots = new AdminDeleteSlots();
        new AdminDeleteSlotsController(adminDeleteSlots);
        adminDeleteSlots.setVisible(true);
    }

    private void returnToMenu() {
        adminManagement.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}
