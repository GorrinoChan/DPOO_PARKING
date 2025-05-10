package Presentation.Controllers;

import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Managers.AdminSlotManager;
import Business.Managers.UserSlotManager;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;
import Presentation.Views.AdminSlotAvaliableView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminSlotAvaliableController {
    private AdminSlotAvaliableView adminslotAvaliableView;

    public AdminSlotAvaliableController(AdminSlotAvaliableView adminslotAvaliableView) {
        this.adminslotAvaliableView = adminslotAvaliableView;
        adminslotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        adminslotAvaliableView.getInfoButton().addActionListener(e -> opengetInfoButton());
        adminslotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());

        mostrarTablaParking(); // <<<<<<<<<<<<<<<<<<<< AÑADIDO
    }

    private void mostrarTablaParking() {
        String[] columnas = {"Código", "Planta", "Tipo Vehículo", "Ocupación", "Reserva", "Matrícula"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            UserSlotManager userSlotManager = new UserSlotManager();

            List<Slot> slots = userSlotManager.readAllSlot();
            List<Reservation> reservas = adminSlotManager.getAllReservationThatHaveBeenDone();

            for (Slot slot : slots) {
                String ocupacion = slot.isOccupation() ? "Ocupado" : "Libre";
                String reserva = slot.isReservation() ? "Reservado" : "Disponible";
                String matricula = "";

                if (slot.isOccupation() || slot.isReservation()) {
                    for (Reservation res : reservas) {
                        if (res.getNumber() == slot.getNumber() && res.getFloor() == slot.getFloor()) {
                            matricula = res.getLicencePlate(); // Asignar la matrícula de la reserva correspondiente
                            break;
                        }
                    }
                }

                Object[] fila = {
                        slot.getNumber(),
                        slot.getFloor(),
                        slot.getTypeOfPlace(),
                        ocupacion,
                        reserva,
                        matricula
                };
                model.addRow(fila);
            }

            JTable tabla = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tabla);
            adminslotAvaliableView.getContentPane().add(scrollPane);
            adminslotAvaliableView.revalidate();
            adminslotAvaliableView.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminslotAvaliableView, "Error al cargar la tabla: " + e.getMessage());
        }
    }

    private void openUserProfileView() {
        adminslotAvaliableView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void opengetInfoButton() {
        adminslotAvaliableView.dispose();
        AdminInfoSlots adminInfoSlots = new AdminInfoSlots();
        new AdminInfoSlotsController(adminInfoSlots);
        adminInfoSlots.setVisible(true);
    }

    private void returnToMenu() {
        adminslotAvaliableView.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}