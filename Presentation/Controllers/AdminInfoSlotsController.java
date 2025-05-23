package Presentation.Controllers;

import Business.Entities.Vehicle;
import Business.Managers.AdminSlotManager;
import Business.Managers.UserSlotManager;
import Business.Managers.UserAccountManager;
import Presentation.Views.AdminProfileView;
import Presentation.Views.AdminSlotAvaliableView;
import Presentation.Views.AdminInfoSlots;
import Presentation.Views.AdminMenuView;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import Business.Entities.Account;

/**
 * Controlador que se encarga de mostrar los detalles de una plaza seleccionada desde la vista de administrador.
 * <p>
 * También permite al administrador cancelar la reserva asociada a esa plaza y ver el perfil del usuario, si existe.
 */
public class AdminInfoSlotsController {
    private AdminInfoSlots adminInfoSlots;
    private AdminMenuView adminMenuView;

    /**
     * Constructor que configura los botones de la vista y carga los detalles de la plaza seleccionada.
     *
     * @param adminInfoSlots Vista con la información de las plazas.
     * @param adminMenuView Vista del menú del administrador.
     */
    public AdminInfoSlotsController(AdminInfoSlots adminInfoSlots, AdminMenuView adminMenuView) {
        this.adminInfoSlots = adminInfoSlots;
        this.adminMenuView = adminMenuView;
        adminInfoSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminInfoSlots.getCancelButton().addActionListener(e -> openCancelButton());
        adminInfoSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
        int selectedRow = adminInfoSlots.getSelectedRow();
        cargarDetallesPlaza(selectedRow);
    }

    /**
     * Carga todos los detalles de la plaza seleccionada y los muestra en una tabla.
     * Se incluye información del usuario si la plaza está ocupada.
     *
     * @param selectedRow Fila seleccionada que representa la plaza a mostrar.
     */
    private void cargarDetallesPlaza(int selectedRow) {
        String[] columnas = {"Numero", "Planta", "Tipo Vehículo", "Nombre", "Correo", "Contraseña", "Reservas", "Vehículos"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);
        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();
            String detallesPlaza = infoPlazas.get(selectedRow);
            String[] partes = detallesPlaza.split("/");

            String numero = partes[1];
            String planta = partes[0];
            String matricula = partes[2];
            String tipoVehiculo = partes[3];
            String ocupacionStr = partes[4];
            String nombrebuscado = partes[5];

            String email = "-";
            String password = "-";
            int numeroReservas = 0;
            int cantidadVehiculos = 0;

            UserAccountManager accountManager = new UserAccountManager();
            if (!nombrebuscado.equals("FREE")) {
                List<Account> cuentas = accountManager.getAllAccountsInDb();
                for (Account cuenta : cuentas) {
                    if (cuenta.getNameOfTheAccount().equalsIgnoreCase(nombrebuscado)) {
                        email = cuenta.getEmailOfTheAccount();
                        password = cuenta.getPassword();
                        numeroReservas = cuenta.getNumberOfReservations();
                        break;
                    }
                }
                List<Vehicle> vehiculos = accountManager.getAllUserVehicles(nombrebuscado);
                cantidadVehiculos = vehiculos.size();
            } else {
                nombrebuscado = "-";
            }

            Object[] fila = {
                    numero,
                    planta,
                    tipoVehiculo,
                    nombrebuscado,
                    email,
                    password,
                    numeroReservas,
                    cantidadVehiculos,
            };

            model.addRow(fila);
            JTable tabla = new JTable(model);
            this.adminInfoSlots.setSlotTable(tabla);
            JScrollPane scrollPane = new JScrollPane(tabla);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(200, 0, 0, 0));
            adminInfoSlots.getContentPane().add(scrollPane);
            adminInfoSlots.revalidate();
            adminInfoSlots.repaint();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(adminInfoSlots, "Error al cargar la información de la plaza: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Muestra la vista del perfil del administrador.
     */
    private void openUserProfileView() {
        adminInfoSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Cancela la reserva de la plaza seleccionada si está ocupada.
     * También actualiza los datos del usuario en base a la cancelación.
     */
    private void openCancelButton() {
        UserSlotManager userSlotManager = new UserSlotManager();
        int slot = adminInfoSlots.getSelectedRow();
        AdminSlotManager adminSlotManager = new AdminSlotManager();
        List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();
        String detallesPlaza = infoPlazas.get(slot);
        String[] partes = detallesPlaza.split("/");
        String nombrebuscado = partes[5];
        String number = partes[1];
        String matricula = partes[2];
        int numero = Integer.parseInt(number);

        if (!matricula.equals("FREE")) {
            userSlotManager.deleteAReservation(numero);
            UserAccountManager accountManager = new UserAccountManager();
            accountManager.reduceInOneTheNumberOfReservationsOfUserAccount(nombrebuscado);
            accountManager.augmentInOneTheNumberOfCancellationsOfUserAccount(nombrebuscado);
            adminInfoSlots.dispose();
            AdminSlotAvaliableView adminSlotAvaliableView = new AdminSlotAvaliableView();
            new AdminSlotAvaliableController(adminSlotAvaliableView, adminMenuView);
            adminSlotAvaliableView.setVisible(true);
        }
    }

    /**
     * Vuelve a la vista de plazas disponibles.
     */
    private void returnToMenu() {
        adminInfoSlots.dispose();
        AdminSlotAvaliableView adminSlotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminSlotAvaliableView, adminMenuView);
        adminSlotAvaliableView.setVisible(true);
    }
}