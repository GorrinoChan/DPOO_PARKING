package Business.Managers;

import Persistence.ReservationDao;
import Persistence.SlotDAO;
import Persistence.VehicleDao;

public class UserSlotManager {

    ReservationDao reservedParkingSlotsDao;
    VehicleDao  vehicleDao;
    SlotDAO slotDao;

    public UserSlotManager() {
        this.reservedParkingSlotsDao = new ReservationDao();
        this.vehicleDao = new VehicleDao();
        this.slotDao = new SlotDAO();
    }

}
