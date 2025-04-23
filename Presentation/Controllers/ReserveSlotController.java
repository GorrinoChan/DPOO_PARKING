package Presentation.Controllers;
import Presentation.Views.*;

import javax.swing.*;

public class ReserveSlotController {
    private ReserveSlotView reserveSlotView;

    public ReserveSlotController(ReserveSlotView reserveSlotView) {
        this.reserveSlotView = reserveSlotView;
        reserveSlotView.getReturnButton().addActionListener(e -> returnToMenu());
        reserveSlotView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        reserveSlotView.getConfirmButton().addActionListener(e-> confirmReservation());
    }

    private void openUserProfileView() {
        reserveSlotView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        reserveSlotView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView);
        slotControlView.setVisible(true);
    }
    private void confirmReservation() {
        reserveSlotView.setErrorMessage("");
        JOptionPane.showMessageDialog(null, "Plaza reservada correctamente.");
        reserveSlotView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}
