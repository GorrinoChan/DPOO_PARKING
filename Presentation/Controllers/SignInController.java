package Presentation.Controllers;

import Presentation.Views.LogInView;
import Presentation.Views.SignInView;
import Business.Managers.UserAccountManager;

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
        UserAccountManager userAccountManager = new UserAccountManager(username);
        boolean checkUserName = userAccountManager.accountUsernameExistByUsername(username);
        boolean checkUserMail = userAccountManager.accountUsernameExistByMail(email);
        boolean checkPassword = userAccountManager.passwordIsCorrect(password, confirmPassword);

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            signInView.setErrorMessage("Todos los campos son obligatorios.");
        } else if (!checkUserName) {
            signInView.setErrorMessage("El nombre de usuario ya está en uso.");
        } else if (!checkUserMail) {
            signInView.setErrorMessage("El mail del usuario ya está en uso.");
        } else if (!checkPassword) {
            signInView.setErrorMessage("Las contraseñas no coinciden.");
        } else {
            signInView.setErrorMessage("");
            userAccountManager.createNewAccount(username, email, password);
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
