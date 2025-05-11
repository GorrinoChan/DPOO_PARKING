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


public class AdminInfoSlotsController {
    private AdminInfoSlots adminInfoSlots;

    public AdminInfoSlotsController(AdminInfoSlots adminInfoSlots) {
        this.adminInfoSlots = adminInfoSlots;
        adminInfoSlots.getReturnButton().addActionListener(e -> returnToMenu());
        adminInfoSlots.getCancelButton().addActionListener(e -> openCancelButton());
        adminInfoSlots.getUserProfileButton().addActionListener(e -> openUserProfileView());
        int selectedRow = adminInfoSlots.getSelectedRow();
        cargarDetallesPlaza(selectedRow);
    }

    private void cargarDetallesPlaza(int selectedRow) {
        String[] columnas = {"Numero", "Planta", "Tipo Vehículo", "Nombre","Correo","Contrseña","Reservas","Vehiculos"};
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
            String email = "";
            String password = "";
            int numeroReservas = 0;
            int cantidadVehiculos=0;
            UserAccountManager accountManager = new UserAccountManager(nombrebuscado);
            if(nombrebuscado.equals("FREE")){
                nombrebuscado = "";
            } else{
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

    private void openUserProfileView() {
        adminInfoSlots.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void openCancelButton() {
        UserSlotManager userSlotManager = new UserSlotManager();
        int slot = adminInfoSlots.getSelectedRow();
        cargarDetallesPlaza(slot);
        userSlotManager.deleteAReservation(slot);
        AdminSlotManager adminSlotManager = new AdminSlotManager();
        List<String> infoPlazas = adminSlotManager.allSlotsAndReservationInformationForTable();
        String detallesPlaza = infoPlazas.get(slot);
        String[] partes = detallesPlaza.split("/");
        String nombrebuscado = partes[5];
        UserAccountManager accountManager = new UserAccountManager(nombrebuscado);
        accountManager.reduceInOneTheNumberOfReservationsOfUserAccount(nombrebuscado);
        accountManager.augmentInOneTheNumberOfCancellationsOfUserAccount(nombrebuscado);
        adminInfoSlots.dispose();
        AdminSlotAvaliableView adminSlotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminSlotAvaliableView);
        adminSlotAvaliableView.setVisible(true);
    }

    private void returnToMenu() {
        adminInfoSlots.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}