package Presentation.Controllers;

import Presentation.Views.SlotAvaliableView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
public class SlotAvaliableController {
    private SlotAvaliableView slotAvaliableView;

    public SlotAvaliableController(SlotAvaliableView slotAvaliableView) {
        this.slotAvaliableView = slotAvaliableView;
        slotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        slotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    private void openUserProfileView() {
        slotAvaliableView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }
    private void returnToMenu() {
        slotAvaliableView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}