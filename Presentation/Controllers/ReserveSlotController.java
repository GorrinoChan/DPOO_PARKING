package Presentation.Controllers;

import Business.Entities.Slot;
import Business.Managers.UserSlotManager;
import Presentation.Views.ReserveSlotView;
import Presentation.Views.SlotControlView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

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

    private void updateTable(String vehicleType) {
        UserSlotManager userSlotManager = new UserSlotManager();
        List<Slot> availableSlots = null;
        DefaultTableModel model = null;

        try {
            availableSlots = userSlotManager.readAllSlot();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model = new DefaultTableModel(new String[]{"Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);

        for (Slot slot : availableSlots) {
            if (!slot.isOccupation() && !slot.isReservation() && slot.getTypeOfPlace().equals(vehicleType)) {
                model.addRow(new Object[]{slot.getTypeOfPlace(), slot.getNumber(), slot.getFloor()});
            }
        }
        slotAvaliableView.updateSlotsAvaliableTable(model);
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
