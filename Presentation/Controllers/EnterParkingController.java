package Presentation.Controllers;

import Presentation.Views.EnterParkingView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
public class EnterParkingController {
    private EnterParkingView enterParkingView;

    public EnterParkingController(EnterParkingView enterParkingView) {
        this.enterParkingView = enterParkingView;
        enterParkingView.getReturnButton().addActionListener(e -> returnToMenu());
        enterParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    private void openUserProfileView() {
        enterParkingView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        enterParkingView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}