package Presentation.Controllers;

import Business.Managers.AdminSlotManager;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;
import Presentation.Views.AdminSlotAvaliableView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class AdminSlotAvaliableController {
    private AdminSlotAvaliableView adminslotAvaliableView;
    private AdminMenuView adminMenuView;

    public AdminSlotAvaliableController(AdminSlotAvaliableView adminslotAvaliableView, AdminMenuView adminMenuView) {
        this.adminslotAvaliableView = adminslotAvaliableView;
        this.adminMenuView = adminMenuView;
        adminslotAvaliableView.getReturnButton().addActionListener(e -> returnToMenu());
        adminslotAvaliableView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        mostrarTablaParking();
    }

    private void mostrarTablaParking() {
        String[] columnas = {"Numero", "Planta", "Tipo Vehículo", "Ocupación", "Reserva", "Matrícula"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();

            for (String linea : infoPlazas) {
                String[] partes = linea.split("/");

                String numero = partes[1];
                String planta = partes[0];
                String matricula = partes[2];
                String tipoVehiculo = partes[3];
                String ocupacionStr = partes[4];
                String ocupacion;
                String reserva;

                if (matricula.equals("FREE")) {
                    ocupacion = "Libre";
                    reserva = "Disponible";
                    matricula = "";
                } else {
                    if (ocupacionStr.equals("true")) {
                        ocupacion = "Ocupado";
                        reserva = "Reservado";
                    } else {
                        ocupacion = "Libre";
                        reserva = "Reservado";
                    }
                }

                Object[] fila = {
                        numero,
                        planta,
                        tipoVehiculo,
                        ocupacion,
                        reserva,
                        matricula
                };
                model.addRow(fila);
            }

            JTable tabla = new JTable(model);
            this.adminslotAvaliableView.setSlotTable(tabla);
            JScrollPane scrollPane = new JScrollPane(tabla);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
            adminslotAvaliableView.getContentPane().add(scrollPane);
            adminslotAvaliableView.revalidate();
            adminslotAvaliableView.repaint();

            tabla.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (evt.getClickCount() == 1) {
                        int selectedRow = tabla.getSelectedRow();
                        adminslotAvaliableView.dispose();
                        AdminInfoSlots infoView = new AdminInfoSlots(selectedRow);
                        new AdminInfoSlotsController(infoView,adminMenuView);
                        infoView.setVisible(true);
                    }
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminslotAvaliableView, "Error al cargar la tabla: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void openUserProfileView() {
        adminslotAvaliableView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        adminslotAvaliableView.dispose();
        adminMenuView.setVisible(true);
    }
}