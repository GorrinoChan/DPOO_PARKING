package Persistence;

import Business.Entities.Account;
import Business.Entities.Reservation;
import Business.Entities.Vehicle;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {

    private SlotDAO slotDao;

    public ReservationDao(SlotDAO slotDao) {
        this.slotDao = slotDao;
    }

    public void deleteSpecificReservation(String slotNumber) throws FileNotFoundException {
        SqlDao.getInstance().deleteObject("reservation", "slotNumber", slotNumber);
    }

    private List<Reservation> transformSqlTableReservationToObjectReservation(ResultSet sqlInfoOfDB) throws FileNotFoundException, SQLException {
        int number;
        int floor;
        boolean occupation;
        boolean reservation;
        String typeOfPlace;
        String date;
        boolean cancelled;
        String licencePlate;
        String userName;
        List<Reservation> allSqlReservationTransformToObjectReservation = new ArrayList<>();

        while(sqlInfoOfDB.next()){
            vehicleLicense = sqlInfoOfDB.getString("licencePlate");
            vehicleType = sqlInfoOfDB.getString("typeOfVehicle");
            vehicleAccountAssociated = sqlInfoOfDB.getString("nameOfUserAccount");
            List <Account> accountLinkedVehicle = accountDao.readSpecificAccountOfDb("nameOfUserAccount", vehicleAccountAssociated);
            accountObjectVehicleAssociated = accountLinkedVehicle.get(0);
            Vehicle vehicleTransformedToObject = new Vehicle(vehicleLicense, vehicleType, accountObjectVehicleAssociated);
            allSqlVehiclesTransformToObjectVehicle.add(vehicleTransformedToObject);
        }
        return allSqlReservationTransformToObjectReservation;
    }



    public List<Reservation> readSpecificReservationOfDb(String column, String reference) throws FileNotFoundException, SQLException {
        List<Reservation> specificSqlReservation;
        ResultSet sqlInfoOfDB;

        sqlInfoOfDB = SqlDao.getInstance().readSpecific("reservation", column, reference);
        specificSqlReservation = transformSqlTableAccountToObjectAccount(sqlInfoOfDB);
        return specificSqlReservation;
    }

    public List<Reservation> readAllReservationContentInDb() throws SQLException, FileNotFoundException {
        List<Reservation> allSqlReservationsInDb;
        ResultSet sqlInfoOfDB;

        sqlInfoOfDB = SqlDao.getInstance().readAllTable("reservation");
        allSqlReservationsInDb = transformSqlTableAccountToObjectAccount(sqlInfoOfDB);

        return allSqlReservationsInDb;
    }
}
