package Presentation.Controllers;

import Presentation.Views.AdminSlotAvaliableView;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;


public class AdminSlotAvaliableController {
    private AdminSlotAvaliableView adminslotAvaliableView;

    public AdminSlotAvaliableController(AdminSlotAvaliableView adminslotAvaliableView) {
        this.adminslotAvaliableView = adminslotAvaliableView;
        adminslotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        adminslotAvaliableView.getInfoButton().addActionListener(e -> opengetInfoButton());
    }

    private void opengetInfoButton() {
        adminslotAvaliableView.dispose();
        AdminInfoSlots adminInfoSlots = new AdminInfoSlots();
        new AdminInfoSlotsController(adminInfoSlots);
        adminInfoSlots.setVisible(true);
    }
    private void returnToMenu() {
        adminslotAvaliableView.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}