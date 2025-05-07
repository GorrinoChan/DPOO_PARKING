import Business.Entities.Account;
import Business.Entities.Configuration;
import Business.Entities.Vehicle;
import Business.Managers.AdminSlotManager;
import Business.Managers.InitializationManager;
import Business.Managers.UserAccountManager;
import Business.Managers.UserSlotManager;
import Persistence.AccountDao;
import Persistence.SlotDAO;
import Persistence.SqlConfigurationDao;
import Persistence.SqlDao;
import Presentation.Controllers.*;
import Presentation.Views.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/************************
 Comentario de Bernat
 Dejare comentado parte del main para el testeo si quereis algo con el main quitad los comentarios

* *****************/

public class main {
    public static void main(String[] args) {

        /**********
        Scanner sc = new Scanner(System.in);
        int t = 0;
        while (t != 5) {
            System.out.println("Select the test:");
            System.out.println("    Test 1: UserSlotManager");
            System.out.println("    Test 2: UserAccountManager");
            System.out.println("    Test 3: InitializationManager");
            System.out.println("    Test 4: AdminSlotManager(Not finished so no test)");
            System.out.println("    5) Exit\n");
            System.out.println("Enter a num: ");
            t = sc.nextInt();
            int cont = 0;


            /**********
            switch (t) {
                case 1:
                    UserSlotManager usm = new UserSlotManager();

                    break;
                case 2:
                    /*
                    * Funciones comprobadas: Crear cuenta, eliminar cuenta añadir vehiculo a cuenta, obtener todas las cuentas
                    * aumentar num de reservaciones de la cuenta(esta no crea una reservacion, solo edita un parametro de la tabla)
                    * aumentar num de cancelaciones de la cuenta(esta no crea una reservacion, solo edita un parametro de la tabla)
                    * obtener todos los vehiculos de una cuenta, comprobar contraseña,
                    *
                    * Funciones a arreglar: accountUsernameExistByMail, accountUsernameExistByUsername
                    * Notas para algunas funciones:
                    * Para la funcion: augmentInOneTheNumberOfReservationsOfUserAccount, se tiene que revisar que el usuario existe, si no peta el codigo
                    * */

            /**********
                    UserAccountManager uam = new UserAccountManager("Berna");

                    boolean f1 = uam.augmentInOneTheNumberOfCancellationsOfUserAccount("Bernat");
                    if(f1){
                        System.out.println("Function augmentInOneTheNumberOfCancellationsOfUserAccount works correctly");
                    }
                    else {
                        System.out.println("Function augmentInOneTheNumberOfCancellationsOfUserAccount doesn't works correctly");
                    }

                    List<Account> acs = uam.getAllAccountsInDb();
                    int i = 0;
                    while(i < acs.size()){
                        int admin = 0;
                        if(acs.get(i).isAdmin()){
                            admin = 1;
                        }
                        System.out.println(i + ")\n" +
                                "    Name: " + acs.get(i).getNameOfTheAccount() + "\n" +
                                "    Email: " + acs.get(i).getEmailOfTheAccount() + "\n" +
                                "    Password: " + acs.get(i).getPassword() + "\n" +
                                "    Num of reservations: " + acs.get(i).getNumberOfReservations() + "\n" +
                                "    Num of cancellations: " + acs.get(i).getSlotCancelations() + "\n" +
                                "    Is it admin?(0 for no 1 for yes): " + admin);
                        i++;
                    }

                    List<Vehicle> vs = uam.getAllUserVehicles("Bernat");
                    i = 0;
                    while(i < vs.size()){
                        System.out.println(i + ")\n" +
                                "    Name of associated account: " + vs.get(i).getVehicleAccountAssociated().getNameOfTheAccount() + "\n" +
                                "    Licence plate: " + vs.get(i).getVehicleLicense() + "\n" +
                                "    Vehicle type: " + vs.get(i).getVehicleType() + "\n"
                                );
                        i++;
                    }

                case 3:
                    InitializationManager im = new InitializationManager();

                    break;
                case 4:
                    System.out.println("Error: Manager not finished\n   Try again");
                    break;
                default:
                    t = 5;
            }

        }
        ******/

        /**********
        SwingUtilities.invokeLater(() -> {
            StartView startView = new StartView();
            new StartController(startView);
            startView.setVisible(true);
        });

         ******/

        System.out.println("Hola");
        SqlConfigurationDao sqlconf = null;
        AccountDao accountDao = new AccountDao();
        try {
            sqlconf = new SqlConfigurationDao();
        } catch (FileNotFoundException e) {
            System.out.println("Hay un problema");
        }
        try{
            Configuration configuration = sqlconf.readJson();

            System.out.println(configuration.getDatebaseName() + "\n");
            System.out.println(configuration.getDatabasePassword() + "\n");
            System.out.println(configuration.getDatabaseHost() + "\n");
            System.out.println(configuration.getDatabaseUsername() + "\n");
            System.out.println(configuration.getDatabasePort() + "\n");
            System.out.println(configuration.getTimeVehicle() + "\n");

            SqlDao sqlDAo = new SqlDao(sqlconf);
            sqlDAo.connect();
            SlotDAO slotDao = new SlotDAO();
            AdminSlotManager adminSlotManager = new AdminSlotManager();
            if(adminSlotManager.parkingSlotAlreadyExists(7)){
                System.out.println("Funciona");
            }else{
                System.out.println("NO Funciona");
            }


        } catch (FileNotFoundException e) {
            System.out.println("Hay un problema");

        }

    }
}
