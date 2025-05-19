package Presentation.Controllers;
import Presentation.Views.*;
public class EnterParkingController {
    private EnterParkingView enterParkingView;
    private UserMenuView userMenuView;

    public EnterParkingController(EnterParkingView enterParkingView, UserMenuView userMenuView) {
        this.enterParkingView = enterParkingView;
        this.userMenuView = userMenuView;
        enterParkingView.getReturnButton().addActionListener(e -> returnToMenu());
        enterParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        enterParkingView.getEnterParkingSlotButtonButton().addActionListener(e -> openCheckPlateView());
        enterParkingView.getRemoveFromParkingButton().addActionListener(e-> openCheckPlateInParkingView());
    }

    private void openUserProfileView() {
        enterParkingView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        enterParkingView.dispose();
        userMenuView.setVisible(true);
    }

    private void openCheckPlateView() {
        enterParkingView.dispose();
        CheckPlateView checkPlateView = new CheckPlateView();
        new CheckPlateController(checkPlateView, userMenuView);
        checkPlateView.setVisible(true);
    }

    private void openCheckPlateInParkingView() {
        enterParkingView.dispose();
        CheckPlateInParkingView checkPlateInParkingView = new CheckPlateInParkingView();
        new CheckPlateInParkingController(checkPlateInParkingView, userMenuView);
        checkPlateInParkingView.setVisible(true);
    }
}