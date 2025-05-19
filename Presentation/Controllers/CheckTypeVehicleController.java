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
    private UserMenuView userMenuView;

    public CheckTypeVehicleController(CheckTypeVehicleView checkTypeVehicleView, String plate, UserMenuView userMenuView) {
        this.checkTypeVehicleView = checkTypeVehicleView;
        this.userMenuView = userMenuView;
        checkTypeVehicleView.getReturnButton().addActionListener(e -> returnToEnterParking());
        checkTypeVehicleView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkTypeVehicleView.getConfirmButton().addActionListener(e -> confirmEntrance(plate));
    }

    private void openUserProfileView() {
        checkTypeVehicleView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToEnterParking() {
        checkTypeVehicleView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView, userMenuView);
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
                returnToEnterParking();
            } else {
                checkTypeVehicleView.setErrorMessage("No hay plazas disponibles.");
            }
        } else {
            checkTypeVehicleView.setErrorMessage("El tipo de vehículo no es correcto.");
        }
    }
}