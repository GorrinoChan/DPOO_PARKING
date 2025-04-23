package Presentation.Controllers;
import Presentation.Views.*;

import javax.swing.*;

public class SignInController {
    private SignInView signInView;

    public SignInController(SignInView signInView) {
        this.signInView = signInView;
        signInView.getSignInButton().addActionListener(e -> registerUser());
        signInView.getReturnButton().addActionListener(e -> returnToLogin());
    }

    private void registerUser() {
        String username = signInView.getUsername();
        String email = signInView.getEmail();
        String password = signInView.getPassword();
        String confirmPassword = signInView.getPasswordConfirmation();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            signInView.setErrorMessage("Todos los campos son obligatorios.");
        } else if (!password.equals(confirmPassword)) {
            signInView.setErrorMessage("Las contraseñas no coinciden.");
        } /*else if () { //Comprobacion base datos

        }*/ else {
            signInView.setErrorMessage("");
            JOptionPane.showMessageDialog(null, "Usuario registrado correctamente.");
            signInView.dispose();
            LogInView loginView = new LogInView();
            new LogInController(loginView);
            loginView.setVisible(true);
        }
    }

    private void returnToLogin() {
        signInView.dispose();
        LogInView loginView = new LogInView();
        new LogInController(loginView);
        loginView.setVisible(true);
    }
}