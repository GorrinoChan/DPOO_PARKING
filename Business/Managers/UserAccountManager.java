package Business.Managers;

import Business.Entities.Account;
import Persistence.AccountDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class UserAccountManager {

    AccountDao accountDao;

    public UserAccountManager() {
        this.accountDao = new AccountDao();
    }

    public boolean accountUsernameExist(String userName){
        boolean exist = true;
        try{
            List<Account> specificSqlAccount = accountDao.readSpecificAccountOfDb("nameOfUserAccount", userName);
        } catch (SQLException | FileNotFoundException e) {
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

    public List<Account> getAllAccountsCanceled () {
        try {
            return accountDao.readAllAccountContentInDb();
        }catch (SQLException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


}
