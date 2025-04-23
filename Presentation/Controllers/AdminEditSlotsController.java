package Presentation.Controllers;

import Presentation.Views.AdminEditSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;

public class AdminEditSlotsController {
    private AdminEditSlots adminEditSlots;

    public AdminEditSlotsController(AdminEditSlots adminEditSlots) {
        this.adminEditSlots = adminEditSlots;
        adminEditSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminEditSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }

    private void openUserProfileView() {
        adminEditSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        adminEditSlots.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}