package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.*;
import Business.Managers.UserSlotManager;

import javax.swing.*;

public class CheckPlateInParkingController {
    private CheckPlateInParkingView checkPlateInParkingView;
    private UserMenuView userMenuView;

    public CheckPlateInParkingController(CheckPlateInParkingView checkPlateInParkingView, UserMenuView userMenuView) {
        this.checkPlateInParkingView = checkPlateInParkingView;
        this.userMenuView = userMenuView;

        checkPlateInParkingView.getReturnButton().addActionListener(e -> returnToEnterParkingView());
        checkPlateInParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateInParkingView.getConfirmButton().addActionListener(e-> confirmReservation());
    }

    private void openUserProfileView() {
        checkPlateInParkingView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToEnterParkingView() {
        checkPlateInParkingView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView, userMenuView);
        enterParkingView.setVisible(true);
    }
    private void confirmReservation() {
        String plate = checkPlateInParkingView.getPlate();
        UserSlotManager userSlotManager = new UserSlotManager();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();

        if (plate.isEmpty()) {
            checkPlateInParkingView.setErrorMessage("Introduzca una matrícula valida.");
        } else if (userSlotManager.licensePlateExist(plate) && userSlotManager.checkIfLicensePlateIsFromTheUser(userName, plate)) {
                userSlotManager.markVehicleAsNotOccupyingSlot(plate);
                JOptionPane.showMessageDialog(null, "Ha salido de PARKING LS correctamente.");
                checkPlateInParkingView.dispose();
                userMenuView.setVisible(true);
            } else {
                checkPlateInParkingView.setErrorMessage("La matrícula no existe.");
            }
        }

    }

