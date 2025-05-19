package Presentation.Controllers;

import Presentation.Views.LogInView;
import Presentation.Views.SignInView;
import Business.Managers.UserAccountManager;

import javax.swing.*;
import java.util.ArrayList;

public class SignInController {
    private SignInView signInView;

    public SignInController(SignInView signInView) {
        this.signInView = signInView;
        signInView.getSignInButton().addActionListener(e -> registerUser());
        signInView.getReturnButton().addActionListener(e -> returnToLogin());
    }

    private void registerUser() {
        String userName = signInView.getUsername();
        String email = signInView.getEmail();
        String password = signInView.getPassword();
        String confirmPassword = signInView.getPasswordConfirmation();
        UserAccountManager userAccountManager = new UserAccountManager();

        ArrayList<Boolean> validateSignIn = userAccountManager.signUp(userName, email, password, confirmPassword);

        if (userName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            signInView.setErrorMessage("Todos los campos son obligatorios.");
        } else if (!validateSignIn.get(0)) {
            signInView.setErrorMessage("El nombre de usuario ya está en uso.");
        } else if (!validateSignIn.get(5)) {
            signInView.setErrorMessage("Las contraseñas no coinciden.");
        } else if (!validateSignIn.get(6)) {
            signInView.setErrorMessage("El correo electrónico no es válido.");
        } else if (!validateSignIn.get(1) || !validateSignIn.get(2) || !validateSignIn.get(3) || !validateSignIn.get(4)) {
            signInView.setErrorMessage("La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número.");
        } else {
            signInView.setErrorMessage("");
            userAccountManager.createNewAccount(userName, email, password);
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
