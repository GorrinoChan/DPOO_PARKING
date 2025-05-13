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
        String userName = LogInController.userName;
        UserAccountManager userAccountManager = new UserAccountManager(userName);
        String slot = userSlotManager.assignVehicleToFirstAvailableSLot(userName, plate, type);
        String[] informationSlot = null;

        if (!slot.equals("00")) {
            informationSlot = slot.split("/");
            JOptionPane.showMessageDialog(null, "Ha entrado a PARKING LS correctamente. Su plaza está en la planta " + informationSlot[0] + " y es la número: " + informationSlot[1]);
            userSlotManager.deleteSlot(informationSlot[0]);
            userAccountManager.augmentInOneTheNumberOfReservationsOfUserAccount(userName);


        } else {
            checkTypeVehicleView.setErrorMessage("No hay plazas disponibles.");
        }

        checkTypeVehicleView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }
}