package Persistence;

import Business.Entities.Account;
import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Entities.Vehicle;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDao {


    public void deleteSpecificReservation(String slotNumber) throws FileNotFoundException {
        SqlDao.getInstance().deleteObject("reservation", "slotNumber", slotNumber);
    }

    private List<Reservation> transformSqlTableReservationToObjectReservation(ResultSet sqlInfoOfDB) throws FileNotFoundException, SQLException {
        int number;
        int floor;
        boolean occupation;
        boolean reservation;
        boolean canceled;
        int reservationInDb;
        int occupationInDb;
        int canceledInDB;
        String date;
        String licensePlate;
        String userName;
        String typeOfPlace;
        List<Reservation> allSqlReservationTransformToObjectReservation = new ArrayList<>();

        try {
            while (sqlInfoOfDB.next()){
                floor = sqlInfoOfDB.getInt("floorNumber");
                number = sqlInfoOfDB.getInt("slotNumber");
                reservationInDb = sqlInfoOfDB.getInt("reservationStatus");
                date = sqlInfoOfDB.getString("date");
                licensePlate = sqlInfoOfDB.getString("licencePlate");
                userName = sqlInfoOfDB.getString("userName");
                if(reservationInDb == 0){
                    reservation = false;
                }else{
                    reservation = true;
                }
                occupationInDb = sqlInfoOfDB.getInt("ocupationStatus");
                if(occupationInDb == 0){
                    occupation = false;
                }else{
                    occupation = true;
                }
                canceledInDB = sqlInfoOfDB.getInt("canceledStatus");
                if(canceledInDB == 0){
                    canceled = false;
                }else{
                    canceled = true;
                }
                typeOfPlace = sqlInfoOfDB.getString("typeOfPlace");
                Reservation reservationTransformed = new Reservation(number, floor, occupation, reservation, typeOfPlace, date, canceled, licensePlate, userName );
                allSqlReservationTransformToObjectReservation.add(reservationTransformed);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSqlReservationTransformToObjectReservation;
    }

    public List<Reservation> readSpecificReservationOfDb (String column, String reference) throws SQLException, FileNotFoundException {
        List<Reservation> specificSqlReservation;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readSpecific("reservation", column, reference);
        specificSqlReservation = transformSqlTableReservationToObjectReservation(sqlInfoOfDB);
        return specificSqlReservation;
    }

    public List<Reservation> readAllReservationContentInDb() throws SQLException, FileNotFoundException {
        List<Reservation> allSqlReservationsInDb;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readAllTable("account");
        allSqlReservationsInDb = transformSqlTableReservationToObjectReservation(sqlInfoOfDB);
        return allSqlReservationsInDb;
    }

    public void insertNewVehicleInDb (String licencePlate, String date, String userName, int slotNumber, int floorNumber, int canceledStatus, int reservationStatus, int occupationStatus, String typeOfPlace) throws SQLException {
        PreparedStatement orderToDb = null;
        String codeLineInDb = "INSERT INTO " + "reservation" + " (licencePlate,date,userName,slotNumber,floorNumber,canceledStatus,reservationStatus,ocupationStatus, typeOfPlace) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        orderToDb = SqlDao.getInstance().getConnection().prepareStatement(codeLineInDb);
        orderToDb.setString(1, licencePlate);
        orderToDb.setString(2, date);
        orderToDb.setString(3, userName);
        orderToDb.setInt(4, slotNumber);
        orderToDb.setInt(5, floorNumber);
        orderToDb.setInt(6, canceledStatus);
        orderToDb.setInt(7, reservationStatus);
        orderToDb.setInt(8, occupationStatus);
        orderToDb.setString(9, typeOfPlace);
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
