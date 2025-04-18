package Presentation.Controllers;

import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminManagement;
import Presentation.Views.AdminCreateSlot;
import Presentation.Views.AdminEditSlots;
import Presentation.Views.AdminDeleteSlots;



public class AdminManagementController {
    private AdminManagement adminManagement;

    public AdminManagementController(AdminManagement adminManagement) {
        this.adminManagement = adminManagement;

        adminManagement.getcreate().addActionListener(e -> openAdminCreate());
        adminManagement.getEdit().addActionListener(e -> openAdminEdit());
        adminManagement.getDelete().addActionListener(e -> openAdminDelete());
        adminManagement.getReturnButton().addActionListener(e -> returnToMenu());

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
