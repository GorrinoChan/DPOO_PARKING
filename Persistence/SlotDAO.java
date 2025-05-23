package Persistence;

import Business.Entities.Account;
import Business.Entities.Slot;

import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SlotDAO {

    public SlotDAO() {
    }

    public void deleteSpecificSlot(String slotNumber)throws FileNotFoundException {
        SqlDao.getInstance().deleteObject("slot", "slotNumber", slotNumber);
    }

    private List<Slot> transformSqlTableSlotToObjectSlot(ResultSet sqlInfoOfDB) throws FileNotFoundException, SQLException {
        int number;
        int floor;
        boolean occupation;
        boolean reservation;
        int reservationInDb;
        int occupationInDb;
        String typeOfPlace;
        List<Slot> allSqlSlotTransformToObjectSlot = new ArrayList<>();

        try {
            while (sqlInfoOfDB.next()){
                floor = sqlInfoOfDB.getInt("floorNumber");
                number = sqlInfoOfDB.getInt("slotNumber");
                reservationInDb = sqlInfoOfDB.getInt("reservationStatus");
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
                typeOfPlace = sqlInfoOfDB.getString("typeOfPlace");
                Slot slotTransformed = new Slot(floor, number, reservation, occupation, typeOfPlace);
                allSqlSlotTransformToObjectSlot.add(slotTransformed);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allSqlSlotTransformToObjectSlot;
    }

    public List<Slot> readSpecificSlotOfDb(String column, String reference) throws FileNotFoundException, SQLException {
        List<Slot> specificSqlSlot;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readSpecific("slot", column, reference);
        specificSqlSlot = transformSqlTableSlotToObjectSlot(sqlInfoOfDB);
        return specificSqlSlot;
    }

    public List<Slot> readAllSlotsContentInDb() throws SQLException, FileNotFoundException {
        List<Slot> allSqlSlotsInDb;
        ResultSet sqlInfoOfDB;
        sqlInfoOfDB = SqlDao.getInstance().readAllTable("slot");
        allSqlSlotsInDb = transformSqlTableSlotToObjectSlot(sqlInfoOfDB);
        return allSqlSlotsInDb;
    }

    public void insertNewSlotInDb (int number, int floor, int occupation, int reservation, String typeOfPlace) throws SQLException {
        PreparedStatement orderToDb = null;
        String codeLineInDb = "INSERT INTO " + "slot" + " (floorNumber, slotNumber, reservationStatus, ocupationStatus, typeOfPlace) VALUES (?, ?, ?, ?, ?)";
        orderToDb = SqlDao.getInstance().getConnection().prepareStatement(codeLineInDb);
        orderToDb.setInt(1, number);
        orderToDb.setInt(2, floor);
        orderToDb.setInt(3, occupation);
        orderToDb.setInt(4, reservation);
        orderToDb.setString(5, typeOfPlace);
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
    public void updateSlotInDb(int existingSlotNumber, int newFloor, int newSlotNumber, String newTypeOfPlace) throws SQLException {
        String sql = "UPDATE slot SET floorNumber = ?, slotNumber = ?, typeOfPlace = ? WHERE slotNumber = ?";
        PreparedStatement stmt = SqlDao.getInstance().getConnection().prepareStatement(sql);
        stmt.setInt(1, newFloor);
        stmt.setInt(2, newSlotNumber);
        stmt.setString(3, newTypeOfPlace);
        stmt.setInt(4, existingSlotNumber);
        stmt.executeUpdate();
        stmt.close();
    }

}
