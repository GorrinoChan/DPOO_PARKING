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

        if (plate.isEmpty()) {
            checkPlateView.setErrorMessage("Introduzca una matrícula valida.");
        } else {
            if (!userReservations.isEmpty()) {
                if (userSlotManager.markVehicleAsOccupyingSlot(plate)) {
                    JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente.");
                }
            } else {
               checkPlateView.dispose();
               CheckTypeVehicleView checkTypeVehicleView = new CheckTypeVehicleView();
               new CheckTypeVehicleController(checkTypeVehicleView, plate);
                checkTypeVehicleView.setVisible(true);
            }
        }
    }
}
