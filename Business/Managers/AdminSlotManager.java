package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Reservation;
import Business.Entities.Slot;
import Business.Entities.Vehicle;
import Persistence.*;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminSlotManager   {

    private SlotDAO slotDAO;
    private VehicleDao vehicleDao;
    private ReservationDao reservationDao;

    public AdminSlotManager() {
        this.slotDAO = new SlotDAO();
        this.vehicleDao = new VehicleDao();
        this.reservationDao = new ReservationDao();
    }

    public boolean deleteParkingSlot(int slotNumber) {
        boolean wasDeleted = false;
        try {
            this.slotDAO.deleteSpecificSlot(String.valueOf(slotNumber));
            wasDeleted = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wasDeleted;
    }
    public boolean createNewParkingSlot(int floor, int slotNumber, String typeOfPlace) {
        boolean slotCreated = false;
        int occupation = 0;
        int reservation = 0;
        try {
            this.slotDAO.insertNewSlotInDb(floor, slotNumber, occupation, reservation, typeOfPlace);
            slotCreated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotCreated;
    }

    public boolean parkingSlotAlreadyExists(int slotNumber) {
        boolean exists = false;
        try {
            List<Slot> result = this.slotDAO.readSpecificSlotOfDb("slotNumber", String.valueOf(slotNumber));
            if (!result.isEmpty()) {
                exists = true;
            }
        } catch (SQLException | FileNotFoundException e) {
            exists = false;
        }
        return exists;
    }

    public List<Reservation> getAllReservationThatHaveBeenDone (){
        List<Reservation> allReservationWithOutTheCanceled;
        try{
            allReservationWithOutTheCanceled = this.reservationDao.readAllReservationContentInDb();
            if(!allReservationWithOutTheCanceled.isEmpty()){
                for(Reservation reservation : allReservationWithOutTheCanceled){
                    if(reservation.isCancelled()){
                        allReservationWithOutTheCanceled.remove(reservation);
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return allReservationWithOutTheCanceled;
    }
    public boolean updateParkingSlot(int existingSlotNumber, int newFloor, int newSlotNumber, String newTypeOfPlace) {
        boolean slotUpdated = false;
        try {
            this.slotDAO.updateSlotInDb(existingSlotNumber, newFloor, newSlotNumber, newTypeOfPlace);
            slotUpdated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return slotUpdated;
    }

        public List<String> allSlotsAndReservationInformationForTable (){
            //Te devuelve la info como Lista de String donde cada String es SlotNumber/FloorNumber/Vehicle/tipoDeVehiculo/Ocupado/UserName
            //Si la reserva esta cancelada (isCanceled = 1) no lo mostrara
            List<String> allInfoOfReservationAndSlot = new ArrayList<>();
            String info;
            try{
                //Leemos la base de datos para las plazas libres
                List<Slot> slotInDb = this.slotDAO.readAllSlotsContentInDb();
                //Bucle para llenar toda la info de las slot (las libres)
                if(!slotInDb.isEmpty()){
                    for(Slot slot :slotInDb){
                        System.out.println("UN LOOP");
                        info = String.valueOf(slot.getNumber()).concat("/").concat(String.valueOf(slot.getFloor())).concat("/").concat("FREE").concat("/").concat(slot.getTypeOfPlace()).concat("/").concat("FREE").concat("/").concat("FREE");
                        allInfoOfReservationAndSlot.add(info);
                    }
                }

            } catch (SQLException e) {
                System.out.println("No funciona Slot");
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                System.out.println("No funciona Slot");
                throw new RuntimeException(e);
            }
            try{
                //Leemos la Base de datoss para las plazas reservada
                List<Reservation> reservationsInDb = this.reservationDao.readAllReservationContentInDb();
                //Bucle para llenar toda la info de las reservas (slots reservada)
                if(!reservationsInDb.isEmpty()){
                    for(Reservation reservation : reservationsInDb) {
                        if (!reservation.isCancelled()) {
                            info = String.valueOf(reservation.getFloor()).concat("/").concat(String.valueOf(reservation.getNumber())).concat("/").concat(reservation.getLicencePlate()).concat("/").concat(reservation.getTypeOfPlace()).concat("/").concat(String.valueOf(reservation.getOccupation())).concat("/").concat(reservation.getUserName());
                            allInfoOfReservationAndSlot.add(info);
                        }
                    }
                }
            } catch (SQLException e) {
                System.out.println("No funciona Reservation");
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                System.out.println("No funciona Reservation");
                throw new RuntimeException(e);
            }
            return allInfoOfReservationAndSlot;
        }


        public boolean deleteReservedSlot(int slotNumber){
            boolean correct = false;
            try{
                this.reservationDao.deleteSpecificReservation(String.valueOf(slotNumber));
                correct = true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        return correct;
        }

        public boolean parkingReservedSlotAlreadyExist(String slotNumber){
            List<Reservation> reservations;
            boolean correct = false;
            try{
                reservations = this.reservationDao.readSpecificReservationOfDb("slotNumber", slotNumber);
                if(!reservations.isEmpty()){
                    correct = true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }            return correct;
        }
}
