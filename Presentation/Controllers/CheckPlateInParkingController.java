package Presentation.Controllers;

import Presentation.Views.*;

public class CheckPlateInParkingController {
    private CheckPlateInParkingView checkPlateInParkingView;

    public CheckPlateInParkingController(CheckPlateInParkingView checkPlateInParkingView) {
        this.checkPlateInParkingView = checkPlateInParkingView;
        checkPlateInParkingView.getReturnButton().addActionListener(e -> returnToMenu());
        checkPlateInParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateInParkingView.getConfirmButton().addActionListener(e-> confirmReservation());
    }

    private void openUserProfileView() {
        checkPlateInParkingView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        checkPlateInParkingView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }
    private void confirmReservation() {
        String plate = checkPlateInParkingView.getPlate();

        if (plate.isEmpty()) {
            checkPlateInParkingView.setErrorMessage("");
            checkPlateInParkingView.dispose();
            UserMenuView userMenuView = new UserMenuView();
            new UserMenuController(userMenuView);
            userMenuView.setVisible(true);
        } else {

        }

    }
}
