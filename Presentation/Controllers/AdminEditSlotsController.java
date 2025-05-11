package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.AdminEditSlots;
import Presentation.Views.AdminManagement;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;

import javax.swing.*;

public class AdminEditSlotsController {
    private AdminEditSlots adminEditSlots;

    public AdminEditSlotsController(AdminEditSlots adminEditSlots) {
        this.adminEditSlots = adminEditSlots;
        adminEditSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminEditSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
        adminEditSlots.getEditButton().addActionListener(e -> EditSlot());


    }
    private void EditSlot() {
        String slotNumbersearchText = adminEditSlots.getnumber1();
        String slotNumberText = adminEditSlots.getnumber2();
        String floorNumberText = adminEditSlots.getfloat();
        String typeOfPlace = adminEditSlots.gettipe();

        if (slotNumberText.isEmpty() || slotNumbersearchText.isEmpty() || floorNumberText.isEmpty() || typeOfPlace == null || typeOfPlace.isEmpty()) {
            adminEditSlots.setErrorMessage("Todos los campos son obligatorios.");
            return;
        }

        try {
            int slotNumbersearch = Integer.parseInt(slotNumbersearchText.trim());
            int slotNumber = Integer.parseInt(slotNumberText.trim());
            int floorNumber = Integer.parseInt(floorNumberText.trim());

            AdminSlotManager slotManager = new AdminSlotManager();

            if (slotManager.parkingSlotAlreadyExists(slotNumbersearch)) {
                boolean Edit = slotManager.updateParkingSlot(slotNumbersearch, floorNumber, slotNumber, typeOfPlace);
                if (Edit) {
                    adminEditSlots.setErrorMessage("");
                    JOptionPane.showMessageDialog(adminEditSlots, "Plaza Editada correctamente.");
                    adminEditSlots.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement);
                    adminManagement.setVisible(true);
                } else {
                    adminEditSlots.setErrorMessage("Error al Editar la plaza.");
                }
            } else {
                adminEditSlots.setErrorMessage("No existe una plaza con ese número.");
            }
        }catch (NumberFormatException e) {
            adminEditSlots.setErrorMessage("Los campos numéricos deben contener números válidos.");
        }
    }


        private void openUserProfileView() {
        adminEditSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        adminEditSlots.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }
}