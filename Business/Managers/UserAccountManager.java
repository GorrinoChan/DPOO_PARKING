package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Vehicle;
import Persistence.AccountDao;
import Persistence.SqlDao;
import Persistence.VehicleDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class UserAccountManager {

    AccountDao accountDao;
    VehicleDao vehicleDao;

    public UserAccountManager(String userName) {
        this.accountDao = new AccountDao();
        this.vehicleDao = new VehicleDao();

    }

    public boolean accountUsernameExistByUsername(String userName) {
        boolean exist = true;
        List<Account> specificSqlAccount = null;
        try {
            specificSqlAccount = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
        } catch (SQLException | FileNotFoundException e) {
            exist = false;
        }
        if (specificSqlAccount == null){
            exist = false;
        }
            return exist;
    }

    public boolean accountUsernameExistByMail(String mailOfUser) {
        boolean exist = true;
        List<Account> specificSqlAccount = null;
        try {
            specificSqlAccount = accountDao.readSpecificAccountOfDb("emailOfUserAccount", mailOfUser);
        } catch (SQLException | FileNotFoundException e) {
            exist = false;
        }
        if (specificSqlAccount == null) {
            exist = false;
        }
        return exist;
    }

    public boolean passwordIsCorrect(String userName, String password){
        boolean correct;
        try{
            List<Account> specificSqlAccount = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            if (specificSqlAccount.get(0).getPassword().equals(password)){
                correct = true;
            }else{
                correct = false;
            }
        } catch (SQLException | FileNotFoundException e) {
            correct = false;
        }
        return correct;
    }

    public void createNewAccount(String userName, String emailOfTheAccount, String password) throws SQLException {
        accountDao.insertNewAccountInDb(userName,emailOfTheAccount,password);
    }

    public List<Account> getAllAccountsIn () {
        try {
            return accountDao.readAllAccountContentInDb();
        }catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean verifyLengthInPassword (String password){
        boolean length = false;
        if(password.length() >= 8){
            length = true;
        }
        return length;
    }

    public boolean verifyIfThereIsMayusInPassword (String password){
        boolean mayus = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                mayus = true;
            }
        }
        return mayus;
    }

    public boolean verifyIfThereIsMinusInPassword (String password){
        boolean minus = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                minus = true;
            }
        }
        return minus;
    }

    public boolean verifyIfThereIsNumberInPassword (String password){
        boolean numeric = false;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                numeric = true;
            }
        }
        return numeric;
    }

    public boolean deleteExistingAccount (String userName){
        boolean correctAction;
        try{
            accountDao.deleteSpecificAccount(userName);
            correctAction = true;
        } catch (FileNotFoundException e) {
            correctAction = false;
        }
        return correctAction;
    }

    public boolean augmentInOneTheNumberOfReservationsOfUserAccount(String userName){
        boolean actionCorrect;
        try{
            List<Account> account = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            int numberOfReservations = account.get(0).getNumberOfReservations();
            SqlDao.getInstance().updateIntAndBolean("account", "slotReservations", String.valueOf(numberOfReservations + 1), userName, "nameOfUserAccount");
            actionCorrect = true;
        } catch (SQLException e) {
            actionCorrect = false;
        } catch (FileNotFoundException e) {
            actionCorrect = false;
        }
        return actionCorrect;
    }

    public boolean reduceInOneTheNumberOfReservationsOfUserAccount(String userName){
        boolean actionCorrect;

        try{
            List<Account> account = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            int numberOfReservations = account.get(0).getNumberOfReservations();
            SqlDao.getInstance().updateIntAndBolean("account", "slotReservations", String.valueOf(numberOfReservations - 1), userName, "nameOfUserAccount");
            actionCorrect = true;
        } catch (SQLException e) {
            actionCorrect = false;
        } catch (FileNotFoundException e) {
            actionCorrect = false;
        }
        return actionCorrect;
    }

    public boolean augmentInOneTheNumberOfCancellationsOfUserAccount(String userName){
        boolean actionCorrect;

        try{
            List<Account> account = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            int numberOfCancellation = account.get(0).getSlotCancelations();
            SqlDao.getInstance().updateIntAndBolean("account", "slotCancelations", String.valueOf(numberOfCancellation + 1), userName, "nameOfUserAccount");
            actionCorrect = true;
        } catch (SQLException e) {
            actionCorrect = false;
        } catch (FileNotFoundException e) {
            actionCorrect = false;
        }
        return actionCorrect;
    }

    public boolean reduceInOneTheNumberOfCancellationsOfUserAccount(String userName){
        boolean actionCorrect;

        try{
            List<Account> account = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            int numberOfCancellation = account.get(0).getSlotCancelations();
            SqlDao.getInstance().updateIntAndBolean("account", "slotCancelations", String.valueOf(numberOfCancellation - 1), userName, "nameOfUserAccount");
            actionCorrect = true;
        } catch (SQLException e) {
            actionCorrect = false;
        } catch (FileNotFoundException e) {
            actionCorrect = false;
        }
        return actionCorrect;
    }

    public List<Vehicle> getAllUserVehicles(String userName){
        List<Vehicle> userVehicles;
        try{
            userVehicles = this.vehicleDao.readSpecificVehicleOfDb("nameOfUserAccount", userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userVehicles;
    }


}
