package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Configuration;
import Business.Entities.Reservation;
import Persistence.AccountDao;
import Persistence.ReservationDao;
import Persistence.SqlConfigurationDao;
import Persistence.SqlDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InitializationManager {

    Configuration configuration;
    SqlConfigurationDao sqlConfigurationDao;
    SqlDao sqlDao;
    AccountDao  accountDao;
    ReservationDao reservationDao;


    public InitializationManager() {
        this.accountDao = new AccountDao();
        this.reservationDao = new ReservationDao();
    }

    public void prepareReadJson(){
        try{
            this.sqlConfigurationDao = new SqlConfigurationDao();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void readJsonForConfigDb() throws FileNotFoundException {
        this.configuration = this.sqlConfigurationDao.readJson();
    }

    public void tryToConnectToDb() throws FileNotFoundException, SQLException {
        this.sqlDao = new SqlDao(this.sqlConfigurationDao);
        this.sqlDao.connect();
    }

    public void tryToDisconectDB(){
        this.sqlDao.disconnect();
    }

    public ArrayList<Boolean> logIn (String emailOrUserName, String password){
        //Array primer Boleano si UseroMail correcto, segundo si el pasword es correcto, ultimo si es admin
        ArrayList<Boolean> arrayWithAllInfoInLogInAnalysis = new ArrayList<>();
        int itIsEmail = emailOrUserName.indexOf("@");
        List<Account> account;
        try{
            if(itIsEmail == -1){
                account = this.accountDao.readSpecificAccountOfDb("nameOfUserAccount", emailOrUserName);
            }else{
                account = this.accountDao.readSpecificAccountOfDb("emailOfUserAccount", emailOrUserName);
            }
            if(account.isEmpty()){
                arrayWithAllInfoInLogInAnalysis.add(false);
                arrayWithAllInfoInLogInAnalysis.add(false);
                arrayWithAllInfoInLogInAnalysis.add(false);
            }else{
                arrayWithAllInfoInLogInAnalysis.add(true);
                if(account.get(0).getPassword().equals(password)){
                    arrayWithAllInfoInLogInAnalysis.add(true);
                    if(account.get(0).getNameOfTheAccount().equals("Admin")){
                        arrayWithAllInfoInLogInAnalysis.add(true);
                    }else{
                        arrayWithAllInfoInLogInAnalysis.add(false);
                    }

                }else{
                    arrayWithAllInfoInLogInAnalysis.add(false);
                    arrayWithAllInfoInLogInAnalysis.add(false);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return arrayWithAllInfoInLogInAnalysis;
    }

    public int getNumberOfReservationOfUserThatHaveBeenCanceledByAdmin(String userName){
        List<Account> account;
        int numberOfCancellations = 0;
        try{
            account = accountDao.readSpecificAccountOfDb("nameOfUserAccount",userName);
            numberOfCancellations = account.get(0).getSlotCancelations();
            System.out.println(numberOfCancellations);
            SqlDao.getInstance().updateIntAndBolean("account", "slotCancelations", "0",userName,"nameOfUserAccount");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return numberOfCancellations;
    }

}
