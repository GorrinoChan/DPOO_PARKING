package Presentation.Controllers;

import Presentation.Views.*;

import javax.swing.*;

public class CheckPlateController {
    private CheckPlateView checkPlateView;

    public CheckPlateController(CheckPlateView checkPlateView) {
        this.checkPlateView = checkPlateView;
        checkPlateView.getReturnButton().addActionListener(e -> returnToMenu());
        checkPlateView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateView.getConfirmButton().addActionListener(e-> confirmReservation());
    }

    private void openUserProfileView() {
        checkPlateView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        checkPlateView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }
    private void confirmReservation() {
        checkPlateView.setErrorMessage("");
        checkPlateView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}
