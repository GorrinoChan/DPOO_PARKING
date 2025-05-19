package Presentation.Controllers;

import Presentation.Views.DeleteAccountView;
import Presentation.Views.StartView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
import Business.Managers.UserAccountManager;
import javax.swing.*;

public class DeleteAccountController {
    private DeleteAccountView deleteAccountView;
    private UserMenuView userMenuView;

    public DeleteAccountController(DeleteAccountView deleteAccountView, UserMenuView userMenuView) {
        this.deleteAccountView = deleteAccountView;
        this.userMenuView = userMenuView;
        deleteAccountView.getConfirmButton().addActionListener(e -> deleteAccount());
        deleteAccountView.getReturnButton().addActionListener(e -> returnUserProfileView());
    }

    private void deleteAccount() {
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();
        if (userAccountManager.deleteExistingAccount(userName)) {
            JOptionPane.showMessageDialog(deleteAccountView, "Cuenta eliminada correctamente.");
            deleteAccountView.dispose();
            StartView startView = new StartView();
            new StartController(startView);
            startView.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(deleteAccountView, "Cuenta no existe");
            returnUserProfileView();
        }
    }

    private void returnUserProfileView() {
        deleteAccountView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }
}