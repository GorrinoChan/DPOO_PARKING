package Presentation.Controllers;
import Presentation.Views.*;

public class LogInController {
    private LogInView logInView;

    public LogInController(LogInView logInView) {
        this.logInView = logInView;
        logInView.getLoginButton().addActionListener(e -> validateLogin());
        logInView.getSignInButton().addActionListener(e -> openSignInView());
        logInView.getReturnButton().addActionListener(e -> returnToStart());
    }

    private void validateLogin() {
        String username = logInView.getUsername();
        String password = logInView.getPassword();

        if (username.equals("admin") && password.equals("admin")) {
            /*logInView.dispose();
            AdminMenuView adminMenuView = new AdminMenuView();
            new AdminMenuController(adminMenuView);
            adminMenuView.setVisible(true);*/
        } else if (username.isEmpty() || password.isEmpty()){
            logInView.setErrorMessage("Todos los campos son obligatorios.");
        }else {
            if (username.equals("user") && password.equals("user")){
                logInView.dispose();
                UserMenuView userMenuView = new UserMenuView();
                new UserMenuController(userMenuView);
                userMenuView.setVisible(true);
            } else {
            logInView.setErrorMessage("Usuario o contraseña incorrectos.");
            }
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
