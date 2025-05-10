package Presentation.Controllers;

import Presentation.Views.CheckPlateInParkingView;
import Presentation.Views.EnterParkingView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

public class CheckPlateController {
    private CheckPlateInParkingView checkPlateView;

    public CheckPlateController(CheckPlateInParkingView checkPlateView) {
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
        String plate = checkPlateView.getPlate();

        if (plate.isEmpty()) {
            checkPlateView.setErrorMessage("");
            checkPlateView.dispose();
            UserMenuView userMenuView = new UserMenuView();
            new UserMenuController(userMenuView);
            userMenuView.setVisible(true);
        } else {

        }

    }
}
