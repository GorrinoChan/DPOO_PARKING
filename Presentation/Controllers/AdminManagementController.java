package Presentation.Controllers;

import Presentation.Views.*;


public class AdminManagementController {
    private AdminManagement adminManagement;
    private AdminMenuView adminMenuView;

    public AdminManagementController(AdminManagement adminManagement, AdminMenuView adminMenuView) {
        this.adminManagement = adminManagement;
        this.adminMenuView = adminMenuView;

        adminManagement.getcreate().addActionListener(e -> openAdminCreate());
        adminManagement.getEdit().addActionListener(e -> openAdminEdit());
        adminManagement.getDelete().addActionListener(e -> openAdminDelete());
        adminManagement.getReturnButton().addActionListener(e -> returnToMenu());
        adminManagement.getUserProfileButton().addActionListener(e -> openUserProfileView());


    }
    private void openUserProfileView() {
        adminManagement.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    private void openAdminCreate() {
        adminManagement.dispose();
        AdminCreateSlot adminCreateSlot = new AdminCreateSlot();
        new AdminCreateSlotController(adminCreateSlot,adminMenuView);
        adminCreateSlot.setVisible(true);
    }

    private void openAdminEdit() {
        adminManagement.dispose();
        AdminEditSlots adminEditSlots = new AdminEditSlots();
        new AdminEditSlotsController(adminEditSlots,adminMenuView);
        adminEditSlots.setVisible(true);
    }

    private void openAdminDelete() {
        adminManagement.dispose();
        AdminDeleteSlots adminDeleteSlots = new AdminDeleteSlots();
        new AdminDeleteSlotsController(adminDeleteSlots,adminMenuView);
        adminDeleteSlots.setVisible(true);
    }

    private void returnToMenu() {
        adminManagement.dispose();
        adminMenuView.setVisible(true);

    }
}
