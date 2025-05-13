package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.*;

import javax.swing.*;


public class AdminCreateSlotController {
    private AdminCreateSlot adminCreateSlot;

    public AdminCreateSlotController(AdminCreateSlot adminCreateSlot) {
        this.adminCreateSlot = adminCreateSlot;
        adminCreateSlot.getReturnButton().addActionListener(e -> returnToMenu());
        adminCreateSlot.getCreateButton().addActionListener(e -> createSlot());
        adminCreateSlot.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }
    private void createSlot() {
        String slotNumberText = adminCreateSlot.getnumber();
        String floorNumberText = adminCreateSlot.getfloaat();
        String typeOfPlace = adminCreateSlot.gettipe();

        if (slotNumberText.isEmpty() || floorNumberText.isEmpty() || typeOfPlace == null || typeOfPlace.isEmpty()) {
            adminCreateSlot.setErrorMessage("Todos los campos son obligatorios.");
            return;
        }

        try {
            int slotNumber = Integer.parseInt(slotNumberText.trim());
            int floorNumber = Integer.parseInt(floorNumberText.trim());

            AdminSlotManager slotManager = new AdminSlotManager();

            if (slotManager.parkingSlotAlreadyExists(slotNumber)) {
                adminCreateSlot.setErrorMessage("Ya existe una plaza con ese número.");
            } else {
                boolean created = slotManager.createNewParkingSlot(floorNumber, slotNumber, typeOfPlace);
                if (created) {
                    adminCreateSlot.setErrorMessage("");
                    JOptionPane.showMessageDialog(adminCreateSlot, "Plaza creada correctamente.");
                    adminCreateSlot.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement);
                    adminManagement.setVisible(true);
                } else {
                    adminCreateSlot.setErrorMessage("Error al crear la plaza.");
                }
            }
        } catch (NumberFormatException e) {
            adminCreateSlot.setErrorMessage("Los campos numéricos deben contener números válidos.");
        }
    }

    private void openUserProfileView() {
        adminCreateSlot.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }


    private void returnToMenu() {
        adminCreateSlot.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }
}