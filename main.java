import Business.Entities.Account;
import Business.Entities.Configuration;
import Persistence.AccountDao;
import Persistence.SqlConfigurationDao;
import Persistence.SqlDao;
import Presentation.Controllers.*;
import Presentation.Views.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class main {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            StartView startView = new StartView();
            new StartController(startView);
            startView.setVisible(true);
        });

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
            sqlDAo.updateString("vehicle", "typeOfVehicle","Motorcycle", "Dani", "nameOfUserAccount");
            List<Account> specificSqlAccount = accountDao.readSpecificAccountOfDb("nameOfUserAccount", "Bernat");
            System.out.println(specificSqlAccount.get(0).getNameOfTheAccount());

        } catch (FileNotFoundException e) {
            System.out.println("Hay un problema");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
