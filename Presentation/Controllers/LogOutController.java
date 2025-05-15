package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.LogOutView;
import Presentation.Views.StartView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.*;

public class LogOutController {
    private LogOutView logOutView;
    private UserMenuView userMenuView;

    public LogOutController(LogOutView logOutView, UserMenuView userMenuView) {
        this.logOutView = logOutView;
        this.userMenuView = userMenuView;

        logOutView.getConfirmButton().addActionListener(e -> logOut());
        logOutView.getReturnButton().addActionListener(e -> goBack());
    }

    private void logOut() {
        UserAccountManager userAccountManager = new UserAccountManager();
        userAccountManager.deleteUserName();
        JOptionPane.showMessageDialog(logOutView, "Sesión cerrada correctamente.");
        logOutView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }

    private void goBack() {
        logOutView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }
}