package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.UserSlotManager;
import Presentation.Views.*;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class CheckPlateController {
    private CheckPlateView checkPlateView;

    public CheckPlateController(CheckPlateView checkPlateView) {
        this.checkPlateView = checkPlateView;
        checkPlateView.getReturnButton().addActionListener(e -> returnToMenu());
        checkPlateView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateView.getConfirmButton().addActionListener(e-> confirmEntrance());
    }

    private void openUserProfileView() {
        checkPlateView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        checkPlateView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }
    private void confirmEntrance() {
        String plate = checkPlateView.getPlate();
        UserSlotManager userSlotManager = new UserSlotManager();
        List<Reservation> userReservations = userSlotManager.readUserReservationByUserName(plate);
        List<Slot> freeSlots = null;
        Slot assignedSlot = null;
        String userName = LogInController.userName;

        if (plate.isEmpty()) {
            checkPlateView.setErrorMessage("Introduzca una matrícula valida.");
        } else if (!userSlotManager.licensePlateExist(plate)) {
                checkPlateView.setErrorMessage("La matrícula no existe.");
        } else {
            if (!userReservations.isEmpty()) {
                if (userSlotManager.markVehicleAsOccupyingSlot(plate)) {
                    JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente.");
                }
            } else {
                try {
                    freeSlots = userSlotManager.readAllFreeSlot();
                    if (!freeSlots.isEmpty()) {
                        assignedSlot = freeSlots.get(0);
                        if (userSlotManager.checkIfVehicleIsCorrectForSlot(plate, assignedSlot.getNumber())) {
                            if (userSlotManager.assignVehicleToFirstAvailableSLot(userName, plate, assignedSlot.getTypeOfPlace())) {
                                JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente. Su plaza es la número: " + assignedSlot.getNumber());
                            } else {
                                checkPlateView.setErrorMessage("No se ha podido asignar una plaza correctamente.");
                            }
                        }
                    } else {
                        checkPlateView.setErrorMessage("No hay plazas disponibles.");
                    }
                } catch (SQLException e) {
                    checkPlateView.setErrorMessage("Error en la base de datos: " + e.getMessage());
                }
            }
        }
    }
}
