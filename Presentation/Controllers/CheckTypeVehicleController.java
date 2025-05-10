package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.UserSlotManager;
import Presentation.Views.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class CheckTypeVehicleController {
    private CheckTypeVehicleView checkTypeVehicleView;

    public CheckTypeVehicleController(CheckTypeVehicleView checkTypeVehicleView, String plate) {
        this.checkTypeVehicleView = checkTypeVehicleView;
        checkTypeVehicleView.getReturnButton().addActionListener(e -> returnToMenu());
        checkTypeVehicleView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkTypeVehicleView.getConfirmButton().addActionListener(e -> confirmEntrance(plate));
    }

    private void openUserProfileView() {
        checkTypeVehicleView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        checkTypeVehicleView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }

    private void confirmEntrance(String plate) {
        String type = checkTypeVehicleView.getTypeVehicle();
        UserSlotManager userSlotManager = new UserSlotManager();
        List<Slot> slots = null;
        String userName = LogInController.userName;

        try {
            slots = userSlotManager.readAllSlot();
            for (Slot slot : slots) {
                if (slot != null && userSlotManager.checkIfVehicleIsCorrectForSlot(plate, slot.getNumber())) {
                    if (userSlotManager.assignVehicleToFirstAvailableSLot(userName, plate, type)) {
                        JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente. Su plaza es la número: " + slot.getNumber());
                    } else {
                        checkTypeVehicleView.setErrorMessage("No hay plazas disponibles.");
                    }
                }
            }
        } catch (SQLException e) {
            checkTypeVehicleView.setErrorMessage("Error en la base de datos: " + e.getMessage());
        }
    }
}