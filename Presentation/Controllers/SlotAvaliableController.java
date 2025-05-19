package Presentation.Controllers;

import Business.Entities.Slot;
import Business.Managers.UserSlotManager;
import Presentation.Views.SlotAvailableView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class SlotAvaliableController {
    private SlotAvailableView slotAvaliableView;
    private UserMenuView userMenuView;

    public SlotAvaliableController(SlotAvailableView slotAvaliableView, UserMenuView userMenuView) {
        this.slotAvaliableView = slotAvaliableView;
        this.userMenuView = userMenuView;
        slotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        slotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        slotAvaliableView.getCarButton().addActionListener(e -> updateTable("Car"));
        slotAvaliableView.getLargeCarButton().addActionListener(e -> updateTable("Large Car"));
        slotAvaliableView.getMotorcycleButton().addActionListener(e -> updateTable("Motorcycle"));
        updateTable("Car");
    }

    private void openUserProfileView() {
        slotAvaliableView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }
    private void returnToMenu() {
        slotAvaliableView.dispose();
        userMenuView.setVisible(true);
    }
    private void updateTable(String vehicleType) {
        UserSlotManager userSlotManager = new UserSlotManager();
        List<Slot> availableSlots = null;
        DefaultTableModel model = new DefaultTableModel(new String[]{"Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);;
        try {
            availableSlots = userSlotManager.readAllSlot();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Slot slot : availableSlots) {
            if (!slot.isOccupation() && !slot.isReservation() && slot.getTypeOfPlace().equals(vehicleType)) {
                model.addRow(new Object[]{slot.getTypeOfPlace(), slot.getNumber(), slot.getFloor()});
            }
        }
        slotAvaliableView.updateSlotsAvailableTable(model);
    }
}
