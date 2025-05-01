package Business.Managers;

import Business.Entities.Account;
import Persistence.AccountDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class UserAccountManager {

    AccountDao accountDao;
    String userName;

    public UserAccountManager(String userName) {
        this.accountDao = new AccountDao();
        this.userName = userName;
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

    public void deleteExistingAccount (String userName){
        try{
            accountDao.deleteSpecificAccount(userName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
