package Presentation.Controllers;

import Presentation.Views.DeleteAccountView;
import Presentation.Views.StartView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
import Business.Managers.UserAccountManager;
import javax.swing.*;

public class DeleteAccountController {
    private DeleteAccountView deleteAccountView;

    public DeleteAccountController(DeleteAccountView deleteAccountView) {
        this.deleteAccountView = deleteAccountView;
        deleteAccountView.getConfirmButton().addActionListener(e -> deleteAccount());
        deleteAccountView.getReturnButton().addActionListener(e -> returnUserProfileView());
    }

    private void deleteAccount() {
        String userName = LogInController.userName;
        UserAccountManager userAccountManager = new UserAccountManager(userName);
        if (userAccountManager.deleteExistingAccount(userName)) {
            JOptionPane.showMessageDialog(deleteAccountView, "Cuenta eliminada correctamente.");
            deleteAccountView.dispose();
            StartView startView = new StartView();
            new StartController(startView);
            startView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(deleteAccountView, "Cuenta no existe");
            deleteAccountView.dispose();
            UserProfileView userProfileView = new UserProfileView();
            new UserProfileController(userProfileView);
            userProfileView.setVisible(true);
        }

    }

    private void returnUserProfileView() {
        deleteAccountView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }
}