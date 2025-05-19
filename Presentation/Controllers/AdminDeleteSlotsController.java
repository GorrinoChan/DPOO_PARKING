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

/**
 * Controlador encargado de gestionar la lógica cuando un administrador quiere eliminar una plaza de aparcamiento.
 * <p>
 * Se encarga de validar el número de plaza introducido, comprobar si está reservada y realizar
 * las operaciones correspondientes para eliminarla correctamente.
 */
public class AdminDeleteSlotsController {
    private AdminDeleteSlots adminDeleteSlots;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que inicializa la vista de eliminación de plazas y asigna las acciones a los botones.
     *
     * @param adminDeleteSlots Vista para eliminar plazas.
     * @param adminMenuView    Vista del menú principal del admin.
     */
    public AdminDeleteSlotsController(AdminDeleteSlots adminDeleteSlots, AdminMenuView adminMenuView) {
        this.adminDeleteSlots = adminDeleteSlots;
        this.adminMenuView = adminMenuView;
        adminDeleteSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminDeleteSlots.getDeleteButton().addActionListener(e -> handleDeleteSlot());
        adminDeleteSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    /**
     * Método que gestiona la eliminación de una plaza.
     * Comprueba si existe, si está reservada, y actúa según el caso: reasignar reserva o simplemente eliminar.
     */
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

            if (!adminSlotManager.parkingSlotAlreadyExists(slotNumbersearch) &&
                    !adminSlotManager.parkingReservedSlotAlreadyExist(SlotNumbersearch)) {
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
                // No hay reserva activa, se puede eliminar directamente
                boolean deleted = adminSlotManager.deleteParkingSlot(slotNumbersearch);
                if (deleted) {
                    JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                    adminDeleteSlots.dispose();
                    AdminManagement adminManagement = new AdminManagement();
                    new AdminManagementController(adminManagement, adminMenuView);
                    adminManagement.setVisible(true);
                } else {
                    adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                }
            } else {
                // Si hay una reserva activa
                String reassigned = userSlotManager.assignVehicleToFirstAvailableSLot(
                        targetReservation.getUserName(),
                        targetReservation.getLicencePlate(),
                        targetReservation.getTypeOfPlace()
                );

                if (!reassigned.equals("00")) {
                    // Se reasignó correctamente
                    String[] partes = reassigned.split("/");
                    String number = partes[0];
                    userSlotManager.deleteSlot(number);
                    boolean deleted = userSlotManager.deleteAReservation(slotNumbersearch);
                    adminSlotManager.deleteParkingSlot(slotNumbersearch);

                    if (!deleted) {
                        JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                        adminDeleteSlots.dispose();
                        AdminManagement adminManagement = new AdminManagement();
                        new AdminManagementController(adminManagement, adminMenuView);
                        adminManagement.setVisible(true);
                    } else {
                        adminDeleteSlots.setErrorMessage("Error al Eliminar la plaza.");
                    }
                } else {
                    // No se pudo reasignar la reserva
                    boolean reservationDeleted = userSlotManager.deleteAReservation(slotNumbersearch);
                    adminSlotManager.deleteParkingSlot(slotNumbersearch);

                    // Se actualiza la info del usuario asociado a la reserva
                    List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();
                    for (String linea : infoPlazas) {
                        String[] partes = linea.split("/");
                        String numero = partes[1];
                        int search = Integer.parseInt(numero);
                        if (search == slotNumbersearch) {
                            String nombrebuscado = partes[5];
                            UserAccountManager accountManager = new UserAccountManager();
                            accountManager.reduceInOneTheNumberOfReservationsOfUserAccount(nombrebuscado);
                            accountManager.augmentInOneTheNumberOfCancellationsOfUserAccount(nombrebuscado);
                            break;
                        }
                    }

                    if (!reservationDeleted) {
                        JOptionPane.showMessageDialog(adminDeleteSlots, "Plaza eliminada correctamente.");
                        adminDeleteSlots.dispose();
                        AdminManagement adminManagement = new AdminManagement();
                        new AdminManagementController(adminManagement, adminMenuView);
                        adminManagement.setVisible(true);
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

    /**
     * Abre la vista del perfil del administrador y cierra la actual.
     */
    private void openUserProfileView() {
        adminDeleteSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Método para volver al menú de administración desde esta vista.
     */
    private void returnToMenu() {
        adminDeleteSlots.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement, adminMenuView);
        adminManagement.setVisible(true);
    }
}