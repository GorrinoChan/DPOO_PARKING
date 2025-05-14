package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.UserAccountManager;
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
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();
        boolean checkType = userSlotManager.checkTypeOfVehicle(plate, type);
        String slot = userSlotManager.assignVehicleToFirstAvailableSLot(userName, plate, type);
        String[] informationSlot = null;
        System.out.println (checkType);

        if (checkType){
            if (!slot.equals("00")) {
                informationSlot = slot.split("/");
                JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente. Su plaza está en la planta " + informationSlot[1] + " y es la número: " + informationSlot[0]);
                userSlotManager.deleteSlot(informationSlot[0]);
                userAccountManager.augmentInOneTheNumberOfReservationsOfUserAccount(userName);
                checkTypeVehicleView.dispose();
                EnterParkingView enterParkingView = new EnterParkingView();
                new EnterParkingController(enterParkingView);
                enterParkingView.setVisible(true);
            } else {
                checkTypeVehicleView.setErrorMessage("No hay plazas disponibles.");
            }
        } else {
            checkTypeVehicleView.setErrorMessage("El tipo de vehículo no es correcto.");

        }

    }
}