package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.UserAccountManager;
import Business.Managers.UserSlotManager;
import Presentation.Views.RemoveReservationView;
import Presentation.Views.SlotControlView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class RemoveReservationController {
    private RemoveReservationView removeReservationView;
    private UserMenuView userMenuView;

    public RemoveReservationController(RemoveReservationView removeReservationView, UserMenuView userMenuView) {
        this.removeReservationView = removeReservationView;
        this.userMenuView = userMenuView;

        removeReservationView.getReturnButton().addActionListener(e -> returnToMenu());
        removeReservationView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        removeReservationView.getRemoveReservationButton().addActionListener(e-> eliminateSlotReservation());
        removeReservationView.getCarButton().addActionListener(e -> updateTable("Car"));
        removeReservationView.getLargeCarButton().addActionListener(e -> updateTable("Large Car"));
        removeReservationView.getMotorcycleButton().addActionListener(e -> updateTable("Motorcycle"));
        updateTable("Car");
    }

    private void openUserProfileView() {
        removeReservationView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        removeReservationView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView, userMenuView);
        slotControlView.setVisible(true);
    }
    private void updateTable(String vehicleType) {
        UserSlotManager userSlotManager = new UserSlotManager();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();
        List<Reservation> reservations = null;
        DefaultTableModel model = null;

        reservations = userSlotManager.readUserReservationByUserName(userName);

        model = new DefaultTableModel(new String[]{"Fecha", "Matrícula", "Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);

        for (Reservation reservation : reservations) {
            if (reservation.getTypeOfPlace().equals(vehicleType) && !reservation.isCancelled()) {
                model.addRow(new Object[]{reservation.getDate(), reservation.getLicencePlate(), reservation.getTypeOfPlace(), reservation.getNumber(), reservation.getFloor()});

            }
            removeReservationView.updateReservationsTable(model);
        }
    }


    private void eliminateSlotReservation() {
        JTable table = removeReservationView.getReservationTable();
        DefaultTableModel model = null;
        int selectedRow = 0;

        model = new DefaultTableModel(new String[]{"Fecha", "Matrícula", "Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);
        if (model.getRowCount() == 0) {
            removeReservationView.setErrorMessage("No hay reservas para eliminar.");
        }
        selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            removeReservationView.setErrorMessage("Seleccione reserva para eliminar.");
        } else {
            JOptionPane.showMessageDialog(null, "Reserva eliminada correctamente.");
            removeReservationView.dispose();
            userMenuView.setVisible(true);
        }

    }
}
