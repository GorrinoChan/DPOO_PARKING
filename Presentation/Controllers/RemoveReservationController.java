package Presentation.Controllers;
import Presentation.Views.*;

import javax.swing.*;

public class RemoveReservationController {
    private RemoveReservationView removeReservationView;

    public RemoveReservationController(RemoveReservationView removeReservationView) {
        this.removeReservationView = removeReservationView;
        removeReservationView.getReturnButton().addActionListener(e -> returnToMenu());
        removeReservationView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        removeReservationView.getRemoveReservationButton().addActionListener(e-> eliminateSlotReservation());
    }

    private void openUserProfileView() {
        removeReservationView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView);
        slotControlView.setVisible(true);
    }

    private void returnToMenu() {
        removeReservationView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
    private void eliminateSlotReservation() {
        removeReservationView.setErrorMessage("");
        JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
        removeReservationView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}
