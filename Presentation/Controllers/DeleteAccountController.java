package Presentation.Controllers;
import Presentation.Views.*;
import javax.swing.*;

public class DeleteAccountController {
    private DeleteAccountView deleteAccountView;

    public DeleteAccountController(DeleteAccountView deleteAccountView) {
        this.deleteAccountView = deleteAccountView;
        deleteAccountView.getConfirmButton().addActionListener(e -> deleteAccount());
        deleteAccountView.getReturnButton().addActionListener(e -> returnUserProfileView());
    }

    private void deleteAccount() {
        JOptionPane.showMessageDialog(deleteAccountView, "Cuenta eliminada correctamente.");

        deleteAccountView.dispose();
        StartView startView = new StartView();
        new StartController(startView);
        startView.setVisible(true);
    }

    private void returnUserProfileView() {
        deleteAccountView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }
}