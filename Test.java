import java.util.Scanner;

import Business.Managers.UserSlotManager;
import Business.Managers.UserAccountManager;
import Business.Managers.InitializationManager;
import Business.Managers.AdminSlotManager;

import Persistence.AccountDao;
import Persistence.ReservationDao;
import Persistence.SlotDAO;
import Persistence.SqlDao;
import Persistence.SqlConfigurationDao;
import Persistence.VehicleDao;

public class Test {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = 0;
        while (t != 5) {}
        System.out.println("Select the test:");
        System.out.println("    Test 1: UserSlotManager");
        System.out.println("    Test 2: UserAccountManager");
        System.out.println("    Test 3: InitializationManager");
        System.out.println("    Test 4: AdminSlotManager(Not finished so no test)");
        System.out.println("    5) Exit\n");
        System.out.println("Enter a num: ");
        t = sc.nextInt();
        int cont = 0;
        switch(t){
            case 1:
                UserSlotManager usm = new UserSlotManager();

                break;
            case 2:
                UserAccountManager uam = new UserAccountManager("Bernat");
                boolean f1 = uam.accountUsernameExistByMail("b@example.com");
                if(f1 == true){
                    System.out.println("Function accountUsernameExistByMail works correctly\n");
                }
                else{
                    System.out.println("Function accountUsernameExistByMail does not work correctly\n");
                }
                System.out.println("Continue? (Yes = 1, No = 0):     ");
                cont = sc.nextInt();
                if(cont == 1) {
                    boolean f2 = uam.addAVehicleToUserAccount("12345ABC", "Bernat", "Car");
                    if (f2 == true) {
                        System.out.println("Function addAVehicleToUserAccount works 'correctly'\n");
                    } else {
                        System.out.println("Function addAVehicleToUserAccount does not work correctly\n");
                    }
                    System.out.println("Continue? (Yes = 1, No = 0):     ");
                    cont = sc.nextInt();
                    if(cont == 1) {
                        boolean f3 = uam.accountUsernameExistByUsername("b@example.com");
                        if (f3 == true) {
                            System.out.println("Function accountUsernameExistByUsername works correctly\n");
                        } else {
                            System.out.println("Function accountUsernameExistByUsername does not work correctly\n");
                        }
                        System.out.println("Continue? (Yes = 1, No = 0):     ");
                        cont = sc.nextInt();
                        if(cont == 1) {
                            boolean f4 = uam.augmentInOneTheNumberOfReservationsOfUserAccount("Bernat");
                            if (f4 == true) {
                                System.out.println("Function augmentInOneTheNumberOfReservationsOfUserAccount works correctly\n");
                            } else {
                                System.out.println("Function augmentInOneTheNumberOfReservationsOfUserAccount does not work correctly\n");
                            }
                            System.out.println("Continue? (Yes = 1, No = 0):     ");
                            cont = sc.nextInt();
                            if(cont == 1) {
                                uam.createNewAccount("David", "d@example.com", "12345");
                                boolean f5 = uam.accountUsernameExistByUsername("David");
                                if (f5 == true) {
                                    System.out.println("Function createNewAccount works correctly\n");
                                } else {
                                    System.out.println("Function createNewAccount does not work correctly\n");
                                }
                                System.out.println("Continue? (Yes = 1, No = 0):     ");
                                cont = sc.nextInt();
                                if(cont == 1) {
                                    uam.deleteExistingAccount("David");
                                    if (uam.accountUsernameExistByUsername("David") == true) {
                                        System.out.println("Function deleteExistingAccount does not works correctly\n");
                                    } else {
                                        System.out.println("Function deleteExistingAccount correctly\n");
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case 3:
                InitializationManager imm = new InitializationManager();

                break;
            case 4:
                System.out.println("Error: Manager not finished\n   Try again");
                break;
            default:
                t = 5;
        }
    }
}
