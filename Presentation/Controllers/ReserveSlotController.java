package Presentation.Controllers;

import Business.Entities.Slot;
import Business.Managers.UserAccountManager;
import Business.Managers.UserSlotManager;
import Presentation.Views.ReserveSlotView;
import Presentation.Views.SlotControlView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.List;

public class ReserveSlotController {
    private ReserveSlotView reserveSlotView;
    private UserMenuView userMenuView;

    public ReserveSlotController(ReserveSlotView reserveSlotView, UserMenuView userMenuView) {
        this.reserveSlotView = reserveSlotView;
        this.userMenuView = userMenuView;
        reserveSlotView.getReturnButton().addActionListener(e -> returnToPrevious());
        reserveSlotView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        reserveSlotView.getConfirmButton().addActionListener(e -> confirmReservation());
        reserveSlotView.getRefreshButton().addActionListener(e -> updateTable(reserveSlotView.getTypeVehicleTextField()));
    }

    private void openUserProfileView() {
        reserveSlotView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToPrevious() {
        reserveSlotView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView, userMenuView);
        slotControlView.setVisible(true);
    }

    private void updateTable(String vehicleType) {
        UserSlotManager userSlotManager = new UserSlotManager();
        List<Slot> availableSlots = null;
        DefaultTableModel model = new DefaultTableModel(new String[]{"Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);;
        if (vehicleType.isEmpty()) {
            reserveSlotView.setErrorMessage("Escribe el tipo de vehiculo.");
        } else {
            reserveSlotView.setErrorMessage("");
            try {
                availableSlots = userSlotManager.readAllSlot();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            for (Slot slot : availableSlots) {
                if (!slot.isOccupation() && !slot.isReservation() && slot.getTypeOfPlace().equals(vehicleType)) {
                    model.addRow(new Object[]{slot.getTypeOfPlace(), slot.getNumber(), slot.getFloor()});
                }
            }
            reserveSlotView.updateSlotsAvailableTable(model);
        }

    }

    private void confirmReservation() {
        String plate = reserveSlotView.getPlateTextField();
        String type = reserveSlotView.getTypeVehicleTextField();
        JTable table = reserveSlotView.getSlotsAvailableTable();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Tipo de Vehículo", "Planta", "Número de Plaza"}, 0);;
        int selectedRow = 0;
        int selectedSlot = 0;
        String floor = null;
        UserSlotManager userSlotManager = new UserSlotManager();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();

        reserveSlotView.setErrorMessage("");
        if (plate.isEmpty() || type.isEmpty()) {
            reserveSlotView.setErrorMessage("Todos los campos son obligatorios.");
        } else if ((!userSlotManager.checkIfLicensePlateIsFromTheUser(userName, plate))) {
            reserveSlotView.setErrorMessage("La matrícula no es correcta.");
        } else if (!userSlotManager.checkTypeOfVehicle(plate, type)) {
            reserveSlotView.setErrorMessage("El tipo de vehículo no coincide con el asignado a la matrícula.");
        } else if (userSlotManager.checkIfCarIsInReservedSlot(plate)) {
            reserveSlotView.setErrorMessage("Este vehículo ya tiene una reserva.");
        } else {
            if (model.getRowCount() == 0) {
                reserveSlotView.setErrorMessage("No hay plazas disponibles en estos instantes.");
            }
            selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                reserveSlotView.setErrorMessage("Seleccione una plaza de la tabla.");
            } else {
                selectedSlot = Integer.parseInt(model.getValueAt(selectedRow, 2).toString());
                floor = model.getValueAt(selectedRow, 1).toString();

                 if (!userSlotManager.reserveAParkingSlotAndVehicleTypeCorrect(plate, selectedSlot)) {
                    reserveSlotView.setErrorMessage("Error al reservar una plaza.");
                } else {
                    JOptionPane.showMessageDialog(null, "Plaza " + selectedSlot + " de la planta " + floor + " reservada correctamente.");
                    reserveSlotView.dispose();
                    userMenuView.setVisible(true);
                }
            }
        }
    }
}
