package Presentation.Controllers;

import Presentation.Views.SlotControlView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

public class SlotControlController {
    private SlotControlView slotControlView;

    public SlotControlController(SlotControlView slotControlView) {
        this.slotControlView = slotControlView;
        slotControlView.getReturnButton().addActionListener(e -> returnToMenu());
        slotControlView.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    private void openUserProfileView() {
        slotControlView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        slotControlView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}