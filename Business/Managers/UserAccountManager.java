package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Vehicle;
import Persistence.AccountDao;
import Persistence.SqlDao;
import Persistence.VehicleDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserAccountManager {

    AccountDao accountDao;
    VehicleDao vehicleDao;
    private String userName;

    public UserAccountManager() {
        this.accountDao = new AccountDao();
        this.vehicleDao = new VehicleDao();

    }

    public void createNewAccount(String userName, String emailOfTheAccount, String password) {
        accountDao.insertNewAccountInDb(userName,emailOfTheAccount,password);
    }

    public List<Account> getAllAccountsInDb () {
        try {
            return accountDao.readAllAccountContentInDb();
        }catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifyLengthInPassword (String password){
        boolean length = false;
        if(password.length() >= 8){
            length = true;
        }
        return length;
    }

    private boolean verifyIfThereIsMayusInPassword (String password){
        boolean mayus = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                mayus = true;
            }
        }
        return mayus;
    }

    private boolean verifyIfThereIsMinusInPassword (String password){
        boolean minus = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) {
                minus = true;
            }
        }
        return minus;
    }

    private boolean verifyIfThereIsNumberInPassword (String password){
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

    public ArrayList<Boolean> signUp (String userName, String email, String password, String secondPassword ){
        ArrayList<Boolean> arrayWithAllInfoInSignUpAnalysis = new ArrayList<>();
        //Primer boolean si el usuario es correcto, password tiene más de 8 caracteres, si tiene almenos una mayuscula, si tiene almenos una minuscula, si tiene al menos un numero, mirar si las contraseñass coinciden, si el correo es correcto(minimo tiene un arroba)
        try{
            List<Account> accountThatAlreadyExist = this.accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
            if(accountThatAlreadyExist.isEmpty()){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            if(verifyLengthInPassword(password)){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            if(verifyIfThereIsMayusInPassword(password)){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            if(verifyIfThereIsMinusInPassword(password)){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            if(verifyIfThereIsNumberInPassword(password)){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            if(password.equals(secondPassword)){
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }
            int itIsEmail = email.indexOf("@");
            if(itIsEmail == -1) {
                arrayWithAllInfoInSignUpAnalysis.add(false);
            }else{
                arrayWithAllInfoInSignUpAnalysis.add(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return arrayWithAllInfoInSignUpAnalysis;
    }

    public boolean addAVehicleToUserAccount (String licensePlate, String userName, String typeOfVehicle){
        boolean correctAction = false;
        try{
            List<Vehicle> vehicleExist = this.vehicleDao.readSpecificVehicleOfDb("licencePlate",licensePlate);
            if(vehicleExist.isEmpty()){
               this.vehicleDao.insertNewVehicleInDb(licensePlate, typeOfVehicle, userName);
               correctAction = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return correctAction;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public void deleteUserName(){
        this.userName = "";
    }
    public String getUserName(){
        return this.userName;
    }


}
