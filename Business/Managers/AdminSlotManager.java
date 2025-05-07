package Business.Managers;

import Business.Entities.Account;
import Business.Entities.Slot;
import Business.Entities.Vehicle;
import Persistence.AccountDao;
import Persistence.SqlDao;
import Persistence.VehicleDao;
import Persistence.SlotDAO;


import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminSlotManager   {

    SlotDAO slotDAO;
    VehicleDao vehicleDao;

    public AdminSlotManager() {
        this.slotDAO = new SlotDAO();
        this.vehicleDao = new VehicleDao();

    }

    public boolean deleteParkingSlot(int slotNumber) {
        boolean wasDeleted = false;

        try {
            SqlDao.getInstance().deleteObject("slot", "slotNumber", String.valueOf(slotNumber));
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
            List<Slot> result = slotDAO.readSpecificSlotOfDb("slotNumber", String.valueOf(slotNumber));
            if (!result.isEmpty()) {
                exists = true;
            }
        } catch (SQLException | FileNotFoundException e) {
            exists = false;
        }

        return exists;
    }
}
