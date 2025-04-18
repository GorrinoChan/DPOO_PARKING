package Presentation.Controllers;

import Presentation.Views.AdminSlotAvaliableView;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;


public class AdminInfoSlotsController {
    private AdminInfoSlots adminInfoSlots;

    public AdminInfoSlotsController(AdminInfoSlots adminInfoSlots) {
        this.adminInfoSlots = adminInfoSlots;
        adminInfoSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminInfoSlots.getCancelButton().addActionListener(e -> openCancelButton());
    }

    private void openCancelButton() {
        adminInfoSlots.dispose();
        AdminSlotAvaliableView adminSlotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminSlotAvaliableView);
        adminSlotAvaliableView.setVisible(true);
    }
    private void returnToMenu() {
        adminInfoSlots.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}