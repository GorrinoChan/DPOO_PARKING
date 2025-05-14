package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.*;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddVehicleController {
    private AddVehicleView addVehicleView;

    public AddVehicleController(AddVehicleView addVehicleView) {
        this.addVehicleView = addVehicleView;
        addVehicleView.getReturnButton().addActionListener(e -> returnToMenu());
        addVehicleView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        addVehicleView.getConfirmButton().addActionListener(e-> confirmVehicle());
    }

    private void openUserProfileView() {
        addVehicleView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        addVehicleView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView);
        slotControlView.setVisible(true);
    }
    private void confirmVehicle() {
        String plate = addVehicleView.getPlateTextField();
        String type = addVehicleView.getTypeVehicleTextField();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();

        addVehicleView.setErrorMessage("");
        if (userAccountManager.addAVehicleToUserAccount(plate, userName, type)){
            JOptionPane.showMessageDialog(null, "Vehículo Registrado Correctamente.");
            addVehicleView.dispose();
            UserMenuView userMenuView = new UserMenuView();
            new UserMenuController(userMenuView);
            userMenuView.setVisible(true);
        } else {
            addVehicleView.setErrorMessage("No se ha podido registrar el vehículo.");
        }

    }
}
