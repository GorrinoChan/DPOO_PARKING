package Persistence;

import Business.Entities.Account;
import Business.Entities.Vehicle;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {

    private AccountDao accountDao;

    public VehicleDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void deleteSpecificVehicle(String plate)throws FileNotFoundException {
        SqlDao.getInstance().deleteObject("vehicle", "licencePlate", plate);
    }

    private List<Vehicle> transformSqlTableVehicleToObjectVehicle(ResultSet sqlInfoOfDB) throws FileNotFoundException, SQLException {
        String vehicleLicense;
        String vehicleType;
        String vehicleAccountAssociated;
        Account accountObjectVehicleAssociated;
        List<Vehicle> allSqlVehiclesTransformToObjectVehicle = new ArrayList<>();

        while(sqlInfoOfDB.next()){
            vehicleLicense = sqlInfoOfDB.getString("licencePlate");
            vehicleType = sqlInfoOfDB.getString("typeOfVehicle");
            vehicleAccountAssociated = sqlInfoOfDB.getString("nameOfUserAccount");
            List <Account> accountLinkedVehicle = accountDao.readSpecificAccountOfDb("nameOfUserAccount", vehicleAccountAssociated);
            accountObjectVehicleAssociated = accountLinkedVehicle.get(0);
            Vehicle vehicleTransformedToObject = new Vehicle(vehicleLicense, vehicleType, accountObjectVehicleAssociated);
            allSqlVehiclesTransformToObjectVehicle.add(vehicleTransformedToObject);
        }
        return allSqlVehiclesTransformToObjectVehicle;
    }

    public List<Vehicle> readSpecificVehicleOfDb (String column, String reference) throws SQLException, FileNotFoundException {
        List<Vehicle> specificSqlVehicle;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readSpecific("vehicle", column, reference);
        specificSqlVehicle = transformSqlTableVehicleToObjectVehicle(sqlInfoOfDB);
        return specificSqlVehicle;
    }

    public List<Vehicle> readAllVehicleContentInDb() throws SQLException, FileNotFoundException {
        List<Vehicle> allSqlVehiclesInDb;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readAllTable("account");
        allSqlVehiclesInDb = transformSqlTableVehicleToObjectVehicle(sqlInfoOfDB);
        return allSqlVehiclesInDb;
    }

    public void insertNewVehicleInDb (String licencePlate, String typeOfVehicle, String nameOfUserAccount) throws SQLException {
        PreparedStatement orderToDb = null;
        String codeLineInDb = "INSERT INTO " + "vehicle" + " (licencePlate,typeOfVehicle, nameOfUserAccount) VALUES (?, ?, ?)";
        orderToDb = SqlDao.getInstance().getConnection().prepareStatement(codeLineInDb);
        orderToDb.setString(1, licencePlate);
        orderToDb.setString(2, typeOfVehicle);
        orderToDb.setString(3, nameOfUserAccount);
        orderToDb.executeUpdate();

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
