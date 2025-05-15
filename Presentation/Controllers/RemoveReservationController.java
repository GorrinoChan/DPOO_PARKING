package Presentation.Controllers;

import Presentation.Views.RemoveReservationView;
import Presentation.Views.SlotControlView;
import Presentation.Views.UserMenuView;

import javax.swing.*;

public class RemoveReservationController {
    private RemoveReservationView removeReservationView;
    private UserMenuView userMenuView;

    public RemoveReservationController(RemoveReservationView removeReservationView, UserMenuView userMenuView) {
        this.removeReservationView = removeReservationView;
        this.userMenuView = userMenuView;

        removeReservationView.getReturnButton().addActionListener(e -> returnToMenu());
        removeReservationView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        removeReservationView.getRemoveReservationButton().addActionListener(e-> eliminateSlotReservation());
    }

    private void openUserProfileView() {
        removeReservationView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView, userMenuView);
        slotControlView.setVisible(true);
    }

    private void returnToMenu() {
        removeReservationView.dispose();
        userMenuView.setVisible(true);
    }
    private void eliminateSlotReservation() {
        removeReservationView.setErrorMessage("");
        JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
        removeReservationView.dispose();
        userMenuView.setVisible(true);
    }
}
