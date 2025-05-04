package Business.Managers;

import Business.Entities.Slot;
import Persistence.SlotDAO;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

public class AdminSlotManager   {
    SlotDAO slotDAO;

    public AdminSlotManager(SlotDAO slotDAO) {
        this.slotDAO = this.slotDAO;
    }
    
    /*******************
    * Comentario sobre la funcion de Bernat
    *
    * Por el momento, como no se como Dani consedera el true or false de occupation y reservation,
    * Lo he puesto en 0 para false y 1 para true en caso de que no sea asi se ha de canviar
    * ***************/
    public void addParkingSlot(Slot slot) throws SQLException {
        int occupation = 0;
        int reservation = 0;
        if(slot.getOccupation() == true){
            occupation = 1;
        }
        if(slot.getReservation() == true){
            reservation = 1;
        }

        slotDAO.insertNewSlotInDb(slot.getNumber(), slot.getFloor(), occupation, reservation, slot.getTypeOfPlace());
    }

    public List<Slot> getAllParkingSlots() throws SQLException, FileNotFoundException {
        return slotDAO.readAllSlotsContentInDb();
    }

    public void deleteParkingSlot(Slot slot) throws FileNotFoundException {
        slotDAO.deleteSpecificSlot(slot.getNumber());
    }

}
