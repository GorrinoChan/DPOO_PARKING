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



}
