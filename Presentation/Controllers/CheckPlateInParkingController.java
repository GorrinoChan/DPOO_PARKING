package Presentation.Controllers;

import Presentation.Views.*;
import Business.Managers.UserSlotManager;

import javax.swing.*;

public class CheckPlateInParkingController {
    private CheckPlateInParkingView checkPlateInParkingView;

    public CheckPlateInParkingController(CheckPlateInParkingView checkPlateInParkingView) {
        this.checkPlateInParkingView = checkPlateInParkingView;
        checkPlateInParkingView.getReturnButton().addActionListener(e -> returnToMenu());
        checkPlateInParkingView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        checkPlateInParkingView.getConfirmButton().addActionListener(e-> confirmReservation());
    }

    private void openUserProfileView() {
        checkPlateInParkingView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        checkPlateInParkingView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }
    private void confirmReservation() {
        String plate = checkPlateInParkingView.getPlate();
        UserSlotManager userSlotManager = new UserSlotManager();
        String userName = LogInController.userName;
        if (plate.isEmpty()) {
            checkPlateInParkingView.setErrorMessage("Introduzca una matrícula valida.");
        } else if (userSlotManager.licensePlateExist(plate) && userSlotManager.checkIfLicensePlateIsFromTheUser(userName, plate)) {
                
                JOptionPane.showMessageDialog(null, "Ha salido de PARKING LS correctamente.");
                checkPlateInParkingView.dispose();
                UserMenuView userMenuView = new UserMenuView();
                new UserMenuController(userMenuView);
                userMenuView.setVisible(true);
            } else {
                checkPlateInParkingView.setErrorMessage("La matrícula no existe.");
            }
        }

    }

