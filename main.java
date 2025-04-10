import Business.Entities.Configuration;
import Persistence.SqlConfigurationDao;

import java.io.FileNotFoundException;

public class main {
    public static void main(String[] args) {

        System.out.println("Hola");
        SqlConfigurationDao sqlconf = null;
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

        } catch (FileNotFoundException e) {
            System.out.println("Hay un problema");
        }


        


    }
}
