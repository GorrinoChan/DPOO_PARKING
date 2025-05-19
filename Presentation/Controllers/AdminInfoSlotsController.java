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
        String[] columns = {"Numero", "Planta", "Tipo Vehículo", "Nombre", "Correo", "Contraseña", "Reservas", "Vehículos"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        try {
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            List<String> infoOfSlot = adminSlotManager.allSlotsAndReservationInformationForTable();
            String specificInfoOfSlot = infoOfSlot.get(selectedRow);
            String[] partes = specificInfoOfSlot.split("/");
            String numberOfSlot = partes[1];
            String floorOfSlot = partes[0];
            String licensePlateOfVehicleInSlot = partes[2];
            String typeOfVehicle = partes[3];
            String occupationStateOfSlot = partes[4];
            String nameThatIsBeingSearched = partes[5];
            String email = "-";
            String password = "-";
            int numberOfReservations = 0;
            int numberOfVehicles = 0;
            UserAccountManager accountManager = new UserAccountManager();
            if (!nameThatIsBeingSearched.equals("FREE")) {
                List<Account> accounts = accountManager.getAllAccountsInDb();
                for (Account account : accounts) {
                    if (account.getNameOfTheAccount().equalsIgnoreCase(nameThatIsBeingSearched)) {
                        email = account.getEmailOfTheAccount();
                        password = account.getPassword();
                        numberOfReservations = account.getNumberOfReservations();
                        break;
                    }
                }
                List<Vehicle> vehiculos = accountManager.getAllUserVehicles(nameThatIsBeingSearched);
                numberOfVehicles = vehiculos.size();
            } else {
                nameThatIsBeingSearched = "-";
            }
            Object[] row = {
                    numberOfSlot,
                    floorOfSlot,
                    typeOfVehicle,
                    nameThatIsBeingSearched,
                    email,
                    password,
                    numberOfReservations,
                    numberOfVehicles,
            };
            model.addRow(row);
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
        List<String> infoOfSlots = adminSlotManager.allSlotsAndReservationInformationForTable();
        String specificInfoOfSlot = infoOfSlots.get(slot);
        String[] parts = specificInfoOfSlot.split("/");
        String nameThatIsBeingSearch = parts[5];
        String number = parts[1];
        String licensePlateOfVehicle = parts[2];
        int numberInInt = Integer.parseInt(number);
        if (!licensePlateOfVehicle.equals("FREE")) {
            userSlotManager.deleteAReservation(numberInInt);
            UserAccountManager accountManager = new UserAccountManager();
            accountManager.reduceInOneTheNumberOfReservationsOfUserAccount(nameThatIsBeingSearch);
            accountManager.augmentInOneTheNumberOfCancellationsOfUserAccount(nameThatIsBeingSearch);
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