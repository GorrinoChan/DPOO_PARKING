package Business.Managers;

import Business.Entities.Slot;
import Business.Entities.Vehicle;
import Persistence.ReservationDao;
import Persistence.SlotDAO;
import Persistence.SqlDao;
import Persistence.VehicleDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class UserSlotManager {

    ReservationDao reservedParkingSlotsDao;
    VehicleDao  vehicleDao;
    SlotDAO slotDao;
    SqlDao sqlDao;

    public UserSlotManager() {
        this.reservedParkingSlotsDao = new ReservationDao();
        this.vehicleDao = new VehicleDao();
        this.slotDao = new SlotDAO();

    }

    public boolean markVehicleAsOccupyingSlot(String licensePlate){
        boolean correct = false;
        try {
            SqlDao.getInstance().updateIntAndBolean("vehicle", "ocupationStatus", "1", "licencePlate", licensePlate);
            correct = true;
        }catch(SQLException e){
            correct = false;
        }
        return correct;
    }

    public boolean markVehicleAsNotOccupyingSlot(String licensePlate){
        boolean correct = false;
        try {
            SqlDao.getInstance().updateIntAndBolean("vehicle", "ocupationStatus", "0", "licencePlate", licensePlate);
            correct = true;
        }catch(SQLException e){
            correct = false;
        }
        return correct;
    }

    public boolean assignVehicleToFirstAvailableSLot(String userName, String licensePlate, String vehicleType){
        boolean correct = false;
        try {
            List<Slot> allAvailableSlotsWithSameVehicleType = this.slotDao.readSpecificSlotOfDb("typeOfPlace", vehicleType);
            Slot possibleOption = allAvailableSlotsWithSameVehicleType.get(0);
            int floorNumber = possibleOption.getFloor();
            int slotNumber = possibleOption.getNumber();
            int occupationStatus = 1;
            if(!possibleOption.isOccupation()){
                occupationStatus = 0;
            }
            int reservedStatus = 1;
            if(!possibleOption.isReservation()){
                occupationStatus = 0;
            }
            LocalDateTime date = LocalDateTime.now();
            this.slotDao.deleteSpecificSlot(slotNumber);
            this.reservedParkingSlotsDao.insertNewReservationInDb(licensePlate, String.valueOf(date), userName, slotNumber,  floorNumber, 0, reservedStatus, occupationStatus, vehicleType);

        }catch (SQLException e){
            correct = false;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return correct;
    }


    public List<Slot> readAllFreeSlot () throws SQLException {
        List<Slot> allFreeSlotsInDB;
        try{
            allFreeSlotsInDB = this.slotDao.readAllSlotsContentInDb();
        }catch (SQLException e) {
            throw new SQLException(e);
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allFreeSlotsInDB;
    }





}
