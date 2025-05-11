package Presentation.Controllers;

import Business.Entities.Slot;
import Business.Managers.UserSlotManager;
import Presentation.Views.SlotAvaliableView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class SlotAvaliableController {
    private SlotAvaliableView slotAvaliableView;

    public SlotAvaliableController(SlotAvaliableView slotAvaliableView) {
        this.slotAvaliableView = slotAvaliableView;
        slotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        slotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        slotAvaliableView.getCarButton().addActionListener(e -> updateTable("Car"));
        slotAvaliableView.getLargeCarButton().addActionListener(e -> updateTable("Large Car"));
        slotAvaliableView.getMotorcycleButton().addActionListener(e -> updateTable("Motorcycle"));
    }

    private void openUserProfileView() {
        slotAvaliableView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }
    private void returnToMenu() {
        slotAvaliableView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
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
}
