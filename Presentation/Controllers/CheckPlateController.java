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
    private UserMenuView userMenuView;

    public CheckPlateController(CheckPlateView checkPlateView, UserMenuView userMenuView) {
        this.checkPlateView = checkPlateView;
        this.userMenuView = userMenuView;

        checkPlateView.getReturnButton().addActionListener(e -> returnToEnterParkingView());
        checkPlateView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateView.getConfirmButton().addActionListener(e-> confirmEntrance());
    }

    private void openUserProfileView() {
        checkPlateView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToEnterParkingView() {
        checkPlateView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView, userMenuView);
        enterParkingView.setVisible(true);
    }
    private void confirmEntrance() {
        String plate = checkPlateView.getPlate();
        UserSlotManager userSlotManager = new UserSlotManager();

        if (plate.isEmpty()) {
            checkPlateView.setErrorMessage("Introduzca una matrícula valida.");
        } else {
            System.out.println(userSlotManager.checkIfVehicleIsInSlot(plate));
            if (userSlotManager.checkIfCarIsInReservedSlot(plate) && !userSlotManager.checkIfVehicleIsInSlot(plate)) {
                if (userSlotManager.markVehicleAsOccupyingSlot(plate)){
                    JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente.");
                    checkPlateView.dispose();
                    userMenuView.setVisible(true);
                } else {
                    checkPlateView.setErrorMessage("No se ha podido entrar al Parking.");
                }

            } else {
               checkPlateView.dispose();
               CheckTypeVehicleView checkTypeVehicleView = new CheckTypeVehicleView();
               new CheckTypeVehicleController(checkTypeVehicleView, plate, userMenuView);
                checkTypeVehicleView.setVisible(true);
            }
        }
    }
}
