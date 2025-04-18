package Presentation.Controllers;

import Presentation.Views.AdminDeleteSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminManagement;


public class AdminDeleteSlotsController {
    private AdminDeleteSlots adminDeleteSlots;

    public AdminDeleteSlotsController(AdminDeleteSlots adminDeleteSlots) {
        this.adminDeleteSlots = adminDeleteSlots;
        adminDeleteSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminDeleteSlots.getDeleteButton().addActionListener(e -> openDeleteButton());
    }

    private void openDeleteButton() {
        adminDeleteSlots.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }
    private void returnToMenu() {
        adminDeleteSlots.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}