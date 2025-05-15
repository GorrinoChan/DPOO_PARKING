package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.UserAccountManager;
import Business.Managers.UserSlotManager;
import Presentation.Views.SlotControlView;
import Presentation.Views.SlotReservationsView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class SlotReservationsController {
    private SlotReservationsView slotReservationsView;
    private UserMenuView userMenuView;

    public SlotReservationsController(SlotReservationsView slotReservationsView, UserMenuView userMenuView) {
        this.slotReservationsView = slotReservationsView;
        this.userMenuView = userMenuView;

        slotReservationsView.getReturnButton().addActionListener(e -> returnToPrevious());
        slotReservationsView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        slotReservationsView.getCarButton().addActionListener(e -> updateTable("Car"));
        slotReservationsView.getLargeCarButton().addActionListener(e -> updateTable("Large Car"));
        slotReservationsView.getMotorcycleButton().addActionListener(e -> updateTable("Motorcycle"));
        updateTable("Car");
    }

    private void openUserProfileView() {
        slotReservationsView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToPrevious() {
        slotReservationsView.dispose();
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
            slotReservationsView.updateReservationsTable(model);
        }
    }
}
