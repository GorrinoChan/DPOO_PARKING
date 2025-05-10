package Presentation.Controllers;
import Presentation.Views.*;
public class EnterParkingController {
    private EnterParkingView enterParkingView;

    public EnterParkingController(EnterParkingView enterParkingView) {
        this.enterParkingView = enterParkingView;
        enterParkingView.getReturnButton().addActionListener(e -> returnToMenu());
        enterParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        enterParkingView.getEnterParkingSlotButtonButton().addActionListener(e -> openCheckPlateView());
        enterParkingView.getRemoveFromParkingButton().addActionListener(e-> openCheckPlateInParkingView());
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

    private void openCheckPlateView() {
        enterParkingView.dispose();
        CheckPlateInParkingView checkPlateView = new CheckPlateInParkingView();
        new CheckPlateInParkingController(checkPlateView);
        checkPlateView.setVisible(true);
    }

    private void openCheckPlateInParkingView() {
        enterParkingView.dispose();
        CheckPlateInParkingView checkPlateView = new CheckPlateInParkingView();
        new CheckPlateInParkingController(checkPlateView);
        checkPlateView.setVisible(true);
    }
}