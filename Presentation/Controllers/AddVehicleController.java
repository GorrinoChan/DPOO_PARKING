package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddVehicleController {
    private AddVehicleView addVehicleView;
    private UserMenuView userMenuView;

    public AddVehicleController(AddVehicleView addVehicleView, UserMenuView userMenuView) {
        this.addVehicleView = addVehicleView;
        this.userMenuView = userMenuView;
        addVehicleView.getReturnButton().addActionListener(e -> returnToMenu());
        addVehicleView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        addVehicleView.getConfirmButton().addActionListener(e-> confirmVehicle());
    }

    private void openUserProfileView() {
        addVehicleView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        addVehicleView.dispose();
        userMenuView.setVisible(true);
    }

    private void confirmVehicle() {
        String plate = addVehicleView.getPlateTextField();
        String type = addVehicleView.getTypeVehicleTextField();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();
        if (type.equals("Car") || type.equals("Large Car") || type.equals("Motorcycle")) {
            addVehicleView.setErrorMessage("");
            if (userAccountManager.addAVehicleToUserAccount(plate, userName, type)){
                JOptionPane.showMessageDialog(null, "Vehículo Registrado Correctamente.");
                returnToMenu();
            } else {
                addVehicleView.setErrorMessage("No se ha podido registrar el vehículo.");
            }
        } else {
            addVehicleView.setErrorMessage("Tipo de vehículo no valido.");
        }
    }
}
