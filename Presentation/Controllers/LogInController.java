package Presentation.Controllers;
import Business.Managers.InitializationManager;
import Presentation.Views.*;

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

        String username = logInView.getUsername();
        String password = logInView.getPassword();
        ArrayList<Boolean> checkLogIn = initializationManager.logIn(username, password);

        System.out.println(checkLogIn.get(0));
        System.out.println(checkLogIn.get(1));
        System.out.println(checkLogIn.get(2));
        if (checkLogIn.get(0) && checkLogIn.get(1) && !checkLogIn.get(2)) {
            logInView.dispose();
            UserMenuView userMenuView = new UserMenuView();
            new UserMenuController(userMenuView);
            userMenuView.setVisible(true);
        } else {
            logInView.setErrorMessage("Usuario o contraseña incorrectos.");
        }
        if (checkLogIn.get(2)) {
            logInView.dispose();
            AdminMenuView adminMenuView = new AdminMenuView();
            new AdminMenuController(adminMenuView);
            adminMenuView.setVisible(true);
        }

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
