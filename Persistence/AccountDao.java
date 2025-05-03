package Persistence;

import Business.Entities.Account;
import Business.Entities.Vehicle;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountDao {
    public AccountDao() {
    }

    public void deleteSpecificAccount(String nameOfTheAccount) throws FileNotFoundException {
        SqlDao.getInstance().deleteObject("account", "nameOfUserAccount", nameOfTheAccount);
    }

    private List<Account> transformSqlTableAccountToObjectAccount(ResultSet sqlInfoOfDB) throws FileNotFoundException, SQLException {
        String nameOfTheAccount;
        String emailOfTheAccount;
        String password;
        int numberOfReservations;
        int slotCancelations;
        int adminSql;
        boolean admin;
        List<Account> allSqlAccountTransformToObjectAccount = new ArrayList<>();

        try {
            while (sqlInfoOfDB.next()){
                nameOfTheAccount = sqlInfoOfDB.getString("nameOfUserAccount");
                emailOfTheAccount = sqlInfoOfDB.getString("emailOfUserAccount");
                password = sqlInfoOfDB.getString("passwordOfUserAccount");
                numberOfReservations = sqlInfoOfDB.getInt("slotReservations");
                slotCancelations = sqlInfoOfDB.getInt("slotCancelations");
                adminSql =  sqlInfoOfDB.getInt("itIsAdmin");
                if(adminSql == 0){
                    admin = false;
                }else{
                    admin = true;
                }

                Account accountTransformed = new Account(nameOfTheAccount, emailOfTheAccount, password, numberOfReservations, slotCancelations, admin);
                allSqlAccountTransformToObjectAccount.add(accountTransformed);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSqlAccountTransformToObjectAccount;
    }

    public List<Account> readSpecificAccountOfDb(String column, String reference) throws FileNotFoundException, SQLException {
        List<Account> specificSqlAccount;
        ResultSet sqlInfoOfDB;

        sqlInfoOfDB = SqlDao.getInstance().readSpecific("account", column, reference);
        specificSqlAccount = transformSqlTableAccountToObjectAccount(sqlInfoOfDB);

        return specificSqlAccount;
    }

    public List<Account> readAllAccountContentInDb() throws SQLException, FileNotFoundException {
        List<Account> allSqlAccountsInDb;
        ResultSet sqlInfoOfDB;

        sqlInfoOfDB = SqlDao.getInstance().readAllTable("account");
        allSqlAccountsInDb = transformSqlTableAccountToObjectAccount(sqlInfoOfDB);

        return allSqlAccountsInDb;
    }

    public void insertNewAccountInDb (String nameOfTheAccount, String emailOfTheAccount, String password) {
        PreparedStatement orderToDb = null;
        String codeLineInDb = "INSERT INTO " + "account" + " (nameOfUserAccount,emailOfUserAccount, passwordOfUserAccount, slotReservations, slotCancelations, itIsAdmin) VALUES (?, ?, ?, 0, 0, 0)";
        try {
            orderToDb = SqlDao.getInstance().getConnection().prepareStatement(codeLineInDb);
            orderToDb.setString(1, nameOfTheAccount);
            orderToDb.setString(2, emailOfTheAccount);
            orderToDb.setString(3, password);
            orderToDb.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        if(orderToDb == null){

        }else{
            try {
                orderToDb.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
