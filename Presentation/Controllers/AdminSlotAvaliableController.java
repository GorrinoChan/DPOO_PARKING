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

        mostrarTablaParking();
    }

    private void mostrarTablaParking() {
        String[] columnas = {"Código", "Planta", "Tipo Vehículo", "Ocupación", "Reserva", "Matrícula"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();

            // Obtenemos la información combinada como Strings
            List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();

            for (String linea : infoPlazas) {
                // Dividimos la información separada por "/"
                String[] partes = linea.split("/");

                // Extraemos los datos
                String codigo = partes[0];               // Número de plaza
                String planta = partes[1];               // Número de planta
                String matricula = partes[2];            // "FREE" o matrícula
                String tipoVehiculo = partes[3];         // Tipo de plaza
                String ocupacionStr = partes[4];         // "true", "false" o "FREE"
                // partes[5] = userName, no lo usamos aquí

                // Determinar ocupación y reserva
                String ocupacion;
                String reserva;

                if (matricula.equals("FREE")) {
                    ocupacion = "Libre";
                    reserva = "Disponible";
                    matricula = ""; // no mostrar "FREE"
                } else {
                    // Hay una matrícula, por lo tanto está ocupado o reservado
                    if (ocupacionStr.equals("true")) {
                        ocupacion = "Ocupado";
                        reserva = "Reservado";
                    } else {
                        ocupacion = "Libre";
                        reserva = "Reservado";
                    }
                }

                // Añadir la fila a la tabla
                Object[] fila = {
                        codigo,
                        planta,
                        tipoVehiculo,
                        ocupacion,
                        reserva,
                        matricula
                };
                model.addRow(fila);
            }

            // Mostrar tabla
            JTable tabla = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(tabla);
            adminslotAvaliableView.getContentPane().add(scrollPane);
            adminslotAvaliableView.revalidate();
            adminslotAvaliableView.repaint();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminslotAvaliableView, "Error al cargar la tabla: " + e.getMessage());
            e.printStackTrace();
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