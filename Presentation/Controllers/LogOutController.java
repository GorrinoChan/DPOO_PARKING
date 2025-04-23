package Presentation.Controllers;
import Presentation.Views.*;

import javax.swing.*;

public class LogOutController {
    private LogOutView logOutView;

    public LogOutController(LogOutView logOutView) {
        this.logOutView = logOutView;

        logOutView.getConfirmButton().addActionListener(e -> logOut());
        logOutView.getReturnButton().addActionListener(e -> goBack());
    }

    private void logOut() {
        JOptionPane.showMessageDialog(logOutView, "Sesión cerrada correctamente.");

        logOutView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }

    private void goBack() {
        logOutView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }
}