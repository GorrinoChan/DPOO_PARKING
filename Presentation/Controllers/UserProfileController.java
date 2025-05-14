package Presentation.Controllers;

import Presentation.Views.*;

public class UserProfileController {
    private UserProfileView userProfileView;

    public UserProfileController(UserProfileView userProfileView) {
        this.userProfileView = userProfileView;

        userProfileView.getLogOutButton().addActionListener(e -> openLogOutView());
        userProfileView.getDeleteAccountButton().addActionListener(e -> openDeleteAccountView());
        userProfileView.getReturnButton().addActionListener(e -> returnToPreviousView());
        userProfileView.getAddVehicleButton().addActionListener(e -> openAddVehicleView());
    }

    private void openLogOutView() {
        userProfileView.dispose();
        LogOutView logOutView = new LogOutView();
        new LogOutController(logOutView);
        logOutView.setVisible(true);
    }

    private void openDeleteAccountView() {
        userProfileView.dispose();
        DeleteAccountView deleteAccountView = new DeleteAccountView();
        new DeleteAccountController(deleteAccountView);
        deleteAccountView.setVisible(true);
    }

    private void returnToPreviousView() {
        userProfileView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }

    private void openAddVehicleView() {
        userProfileView.dispose();
        AddVehicleView addVehicleView = new AddVehicleView();
        new AddVehicleController(addVehicleView);
        addVehicleView.setVisible(true);
    }
}