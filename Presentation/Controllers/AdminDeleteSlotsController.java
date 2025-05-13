package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Managers.AdminSlotManager;
import Business.Managers.UserSlotManager;
import Business.Managers.UserAccountManager;
import Presentation.Views.AdminDeleteSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminManagement;
import Presentation.Views.AdminProfileView;

import javax.swing.*;
import java.util.List;

public class AdminDeleteSlotsController {
    private AdminDeleteSlots adminDeleteSlots;

    public AdminDeleteSlotsController(AdminDeleteSlots adminDeleteSlots) {
        this.adminDeleteSlots = adminDeleteSlots;
        adminDeleteSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminDeleteSlots.getDeleteButton().addActionListener(e -> handleDeleteSlot());
        adminDeleteSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    private void handleDeleteSlot() {
        UserSlotManager userSlotManager = new UserSlotManager();
        AdminSlotManager adminSlotManager = new AdminSlotManager();
        String slotNumbersearchText = adminDeleteSlots.getnumber();

        if (slotNumbersearchText.isEmpty()) {
            adminDeleteSlots.setErrorMessage("Todos los campos son obligatorios.");
            return;
        }
        try {
            int slotNumbersearch = Integer.parseInt(slotNumbersearchText.trim());
            String SlotNumbersearch = String.valueOf(slotNumbersearch);
            if (!adminSlotManager.parkingSlotAlreadyExists(slotNumbersearch) && !adminSlotManager.parkingReservedSlotAlreadyExist(SlotNumbersearch)) {
                JOptionPane.showMessageDialog(adminDeleteSlots, "La plaza no existe.");
                return;
            }
            List<Reservation> reservations = adminSlotManager.getAllReservationThatHaveBeenDone();
            Reservation targetReservation = null;
            for (Reservation r : reservations) {
                if (r.getNumber() == slotNumbersearch && !r.isCancelled()) {
                    targetReservation = r;
                    break;
                }
            }
            if (targetReservation == null) {
                boolean deleted = adminSlotManager.deleteParkingSlot(slotNumbersearch);
                if (deleted) {
                    JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                    adminDeleteSlots.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement);
                    adminManagement.setVisible(true);
                }
                else{
                    adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                }
            } else {
                String reassigned = userSlotManager.assignVehicleToFirstAvailableSLot(
                        targetReservation.getUserName(),
                        targetReservation.getLicencePlate(),
                        targetReservation.getTypeOfPlace()
                );
                if (!reassigned.equals("00")) {
                    boolean deleted = userSlotManager.deleteAReservation(slotNumbersearch);
                    if (deleted) {
                        JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                        adminDeleteSlots.dispose();
                        AdminManagement adminManagement = new AdminManagement();
                        new AdminManagementController(adminManagement);
                        adminManagement.setVisible(true);
                    }
                    else{
                        adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                    }
                } else {
                    boolean reservationDeleted = userSlotManager.deleteAReservation(slotNumbersearch);
                    List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();
                    String detallesPlaza = infoPlazas.get(slotNumbersearch);
                    String[] partes = detallesPlaza.split("/");
                    String nombrebuscado = partes[5];
                    UserAccountManager accountManager = new UserAccountManager(nombrebuscado);
                    accountManager.reduceInOneTheNumberOfReservationsOfUserAccount(nombrebuscado);
                    accountManager.augmentInOneTheNumberOfCancellationsOfUserAccount(nombrebuscado);


                    if (reservationDeleted) {
                        JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                        adminDeleteSlots.dispose();
                        AdminManagement adminManagement = new AdminManagement();
                        new AdminManagementController(adminManagement);
                        adminManagement.setVisible(true);
                    } else if (reservationDeleted) {
                        adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                    } else {
                        adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                    }
                }
            }

        } catch (NumberFormatException e) {
            adminDeleteSlots.setErrorMessage("Los campos numéricos deben contener números válidos.");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(adminDeleteSlots, "Error inesperado.");
        }
    }

    private void openUserProfileView() {
        adminDeleteSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        adminDeleteSlots.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }
}