package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Entities.Vehicle;
import Persistence.ReservationDao;
import Persistence.SlotDAO;
import Persistence.SqlDao;
import Persistence.VehicleDao;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserSlotManager {

    ReservationDao reservedParkingSlotsDao;
    VehicleDao  vehicleDao;
    SlotDAO slotDao;
    String userName;


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
            SqlDao.getInstance().updateIntAndBolean("reservation", "ocupationStatus", "0", "licencePlate", licensePlate);
            correct = true;
        }catch(SQLException e){
            correct = false;
        }
        return correct;
    }

        public String assignVehicleToFirstAvailableSLot(String userName, String licensePlate, String vehicleType) {
            String info = "00";

            System.out.println(userName);
            System.out.println("\n");
            System.out.println(licensePlate);
            System.out.println("\n");
            System.out.println(vehicleType);

            List<Slot> allAvailableSlotsWithSameVehicleType = null;
            try {
                allAvailableSlotsWithSameVehicleType = this.slotDao.readSpecificSlotOfDb("typeOfPlace", vehicleType);

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            if (!allAvailableSlotsWithSameVehicleType.isEmpty()) {
                Slot possibleOption = allAvailableSlotsWithSameVehicleType.get(0);

                int floorNumber = possibleOption.getFloor();
                int slotNumber = possibleOption.getNumber();
                int occupationStatus = 1;
                if (!possibleOption.isOccupation()) {
                    occupationStatus = 0;
                }
                int reservedStatus = 1;
                if (!possibleOption.isReservation()) {
                    occupationStatus = 0;
                }
                LocalDateTime date = LocalDateTime.now();
                System.out.println(possibleOption.getNumber());
                try {
                    this.reservedParkingSlotsDao.insertNewReservationInDb(licensePlate, String.valueOf(date), userName, slotNumber, floorNumber, 0, reservedStatus, occupationStatus, vehicleType);
                    info = String.valueOf(floorNumber).concat("/").concat(String.valueOf(slotNumber));
                    System.out.println("Creada la reserva");
                    try{
                        this.slotDao.deleteSpecificSlot(String.valueOf(slotNumber));
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        return info;
    }


    public List<Slot> readAllSlot () throws SQLException {
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

    public List<Reservation> readUserReservationByUserName (String userName){
        List<Reservation> userReservations;
        try{
            userReservations = this.reservedParkingSlotsDao.readSpecificReservationOfDb("userName", userName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userReservations;
    }

    public boolean reserveAParkingSlotAndVehicleTypeCorrect (String licensePlate, int slotCode){
        boolean actionCorrect = false;
        Slot slotFound;
        int reservation = 0;
        int occupation = 0;

        try{
            List<Vehicle> vehicleOfUser = this.vehicleDao.readSpecificVehicleOfDb("licencePlate", licensePlate);
            List<Slot> slotThatIsGoingToBeReserved = this.slotDao.readSpecificSlotOfDb("slotNumber", String.valueOf(slotCode));
            slotFound = slotThatIsGoingToBeReserved.get(0);
                if(slotFound.isReservation()){
                    reservation = 1;
                }
                if (slotFound.isOccupation()){
                    occupation = 1;
                }
            Account accountOfUser = vehicleOfUser.get(0).getVehicleAccountAssociated();
            LocalDateTime date = LocalDateTime.now();
            this.reservedParkingSlotsDao.insertNewReservationInDb(licensePlate, String.valueOf(date), accountOfUser.getNameOfTheAccount(), slotCode, slotFound.getFloor(), 0, reservation, occupation, vehicleOfUser.get(0).getVehicleType());
            actionCorrect = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return actionCorrect;
    }

    public boolean checkIfVehicleIsCorrectForSlot(String licensePlate, int slotCode){
        boolean actionCorrect = false;
        try{
            List<Vehicle> vehicleOfUser = this.vehicleDao.readSpecificVehicleOfDb("licencePlate", licensePlate);
            List<Slot> slotThatIsGoingToCheck= this.slotDao.readSpecificSlotOfDb("typeOfPlace", vehicleOfUser.get(0).getVehicleType());
            if(!slotThatIsGoingToCheck.isEmpty()){
                actionCorrect = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return actionCorrect;
    }

    public List<Reservation> getAllReservedSlotsThatHaveBeenCanceled(String userName){
        List<Reservation> allReservedSlotsThatHaveBeenCanceled = null;
        try{
            List<Reservation> slotThatIsGoingToCheck= this.reservedParkingSlotsDao.readSpecificReservationOfDb("userName", userName);
            for(Reservation reservation: slotThatIsGoingToCheck){
                if(reservation.isCancelled()){
                    assert allReservedSlotsThatHaveBeenCanceled != null;
                    allReservedSlotsThatHaveBeenCanceled.add(reservation);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allReservedSlotsThatHaveBeenCanceled;
    }

    public boolean deleteAReservation (int slotCode){
        boolean actionCorrect = false;
        Reservation reservationFound;
        int occupation = 0;
        int reserved = 0;
        try{
            List<Reservation> reservationToCancelInDB = this.reservedParkingSlotsDao.readSpecificReservationOfDb("slotNumber", String.valueOf(slotCode));
            reservationFound = reservationToCancelInDB.get(0);
            this.reservedParkingSlotsDao.deleteSpecificReservation(String.valueOf(slotCode));
            if(reservationFound.isOccupation()){
                occupation = 1;
            }
            if(reservationFound.isReservation()){
                reserved = 1;
            }
            this.slotDao.insertNewSlotInDb(reservationFound.getFloor(), reservationFound.getNumber(), occupation, reserved, reservationFound.getTypeOfPlace());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return actionCorrect;
    }
    public boolean isSlotOccupied(int slotNumber) {
        try {
            List<Slot> result = this.slotDao.readSpecificSlotOfDb("slotNumber", String.valueOf(slotNumber));
            if (!result.isEmpty()) {
                return result.get(0).isOccupation();
            }
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean  licensePlateExist(String licensePlate){
        boolean correct = false;

        try{

            List<Vehicle> vehicle = this.vehicleDao.readSpecificVehicleOfDb("licencePlate", licensePlate);
            if(!vehicle.isEmpty()){
                correct = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return correct;
    }

    public boolean checkIfLicensePlateIsFromTheUser (String userName, String licensePlate){

        boolean correct = false;
        try{
            List<Vehicle> vehiclesOfUser = this.vehicleDao.readSpecificVehicleOfDb("nameOfUserAccount", userName);
            if(!vehiclesOfUser.isEmpty()){
                for(Vehicle vehicle : vehiclesOfUser){
                    if (vehicle.getVehicleLicense().equals(licensePlate)) {
                        correct = true;
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return correct;
    }

    public void deleteSlot (String slotNumber){
           SqlDao.getInstance().deleteObject("slot", "slotNumber", slotNumber);
           System.out.println("Delete");
    }


    public boolean checkTypeOfVehicle (String licensePlate, String vehicleType){
        boolean correct = false;
        try{
            List<Vehicle> vehicles = this.vehicleDao.readSpecificVehicleOfDb("licencePlate", licensePlate);
            if (vehicles.get(0).getVehicleType().equals(vehicleType)){
                correct = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return correct;
    }

    public boolean checkIfCarIsInReservedSlot(String licencePlate){
        boolean correct = false;
        List<Reservation> reservations;
        try{
            reservations = this.reservedParkingSlotsDao.readSpecificReservationOfDb("licencePlate", licencePlate);
            if(!reservations.isEmpty()){
                correct = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return correct;
    }

    public boolean checkIfVehicleIsInSlot(String licensePlate){
        boolean correct = false;
        try {
            List<Reservation> reservations = this.reservedParkingSlotsDao.readSpecificReservationOfDb("licencePlate", licensePlate);
            if(reservations.get(0).isOccupation()){
                correct = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return correct;
    }

}
