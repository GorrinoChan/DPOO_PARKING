package Presentation.Controllers;
import Business.Managers.InitializationManager;
import Business.Managers.UserAccountManager;
import Presentation.Views.*;

import javax.swing.*;
import java.util.ArrayList;

public class LogInController {
    private LogInView logInView;

    public LogInController(LogInView logInView) {
        this.logInView = logInView;
        logInView.getLoginButton().addActionListener(e -> validateLogin());
        logInView.getSignInButton().addActionListener(e -> openSignInView());
        logInView.getReturnButton().addActionListener(e -> returnToStart());
    }

    private void validateLogin() {
        InitializationManager initializationManager = new InitializationManager();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = logInView.getUsername();
        String password = logInView.getPassword();
        ArrayList<Boolean> checkLogIn = initializationManager.logIn(userName, password);

        if (userName.isEmpty() || password.isEmpty()) {
            logInView.setErrorMessage("Todos los campos son obligatorios.");
        } else if (!checkLogIn.get(0) || !checkLogIn.get(1)){
            logInView.setErrorMessage("Usuario o contraseña incorrectos.");
        } else if (checkLogIn.get(0) && checkLogIn.get(1) && !checkLogIn.get(2)) {
            logInView.dispose();
            UserMenuView userMenuView = new UserMenuView();
            new UserMenuController(userMenuView);
            userMenuView.setVisible(true);

        } else if (checkLogIn.get(2)) {
            logInView.dispose();
            AdminMenuView adminMenuView = new AdminMenuView();
            new AdminMenuController(adminMenuView);
            adminMenuView.setVisible(true);
        }
            userAccountManager.setUserName(userName);
    }

    private void openSignInView() {
        logInView.dispose();
        SignInView signInView = new SignInView();
        new SignInController(signInView);
        signInView.setVisible(true);
    }
    private void returnToStart() {
        logInView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }
}
