package Presentation.Controllers;

import Presentation.Views.AdminEditSlots;
import Presentation.Views.AdminMenuView;

public class AdminEditSlotsController {
    private AdminEditSlots adminEditSlots;

    public AdminEditSlotsController(AdminEditSlots adminEditSlots) {
        this.adminEditSlots = adminEditSlots;
        adminEditSlots.getReturnButton().addActionListener(e -> returnToMenu());
    }

    private void returnToMenu() {
        adminEditSlots.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}