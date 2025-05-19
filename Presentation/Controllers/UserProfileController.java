package Presentation.Controllers;

import Presentation.Views.*;

public class UserProfileController {
    private UserProfileView userProfileView;
    private UserMenuView userMenuView;

    public UserProfileController(UserProfileView userProfileView, UserMenuView userMenuView) {
        this.userProfileView = userProfileView;
        this.userMenuView = userMenuView;

        userProfileView.getLogOutButton().addActionListener(e -> openLogOutView());
        userProfileView.getDeleteAccountButton().addActionListener(e -> openDeleteAccountView());
        userProfileView.getReturnButton().addActionListener(e -> returnToPreviousView());
        userProfileView.getAddVehicleButton().addActionListener(e -> openAddVehicleView());
    }

    private void openLogOutView() {
        userProfileView.dispose();
        LogOutView logOutView = new LogOutView();
        new LogOutController(logOutView, userMenuView);
        logOutView.setVisible(true);
    }

    private void openDeleteAccountView() {
        userProfileView.dispose();
        DeleteAccountView deleteAccountView = new DeleteAccountView();
        new DeleteAccountController(deleteAccountView, userMenuView);
        deleteAccountView.setVisible(true);
    }

    private void returnToPreviousView() {
        userProfileView.dispose();
        userMenuView.setVisible(true);
    }

    private void openAddVehicleView() {
        userProfileView.dispose();
        AddVehicleView addVehicleView = new AddVehicleView();
        new AddVehicleController(addVehicleView, userMenuView);
        addVehicleView.setVisible(true);
    }
}