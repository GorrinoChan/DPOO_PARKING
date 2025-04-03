package Business.Entities;

import java.util.ArrayList;

public class Account {

    private String nameOfTheAccount;
    private String emailOfTheAccount;
    private String password;
    private int numberOfReservations;
    private int slotCancelations;
    private boolean admin;
    private ArrayList<Vehicle> vehicles;

    public Account(String nameOfTheAccount, String emailOfTheAccount, String password, int numberOfReservations, int slotCancelations, boolean admin, ArrayList<Vehicle> vehicles) {
        this.nameOfTheAccount = nameOfTheAccount;
        this.emailOfTheAccount = emailOfTheAccount;
        this.password = password;
        this.numberOfReservations = numberOfReservations;
        this.slotCancelations = slotCancelations;
        this.admin = admin;
        this.vehicles = vehicles;
    }
    public String getNameOfTheAccount() {
        return nameOfTheAccount;
    }
    public void setNameOfTheAccount(String nameOfTheAccount) {
        this.nameOfTheAccount = nameOfTheAccount;
    }
    public String getEmailOfTheAccount() {
        return emailOfTheAccount;
    }
    public void setEmailOfTheAccount(String emailOfTheAccount) {
        this.emailOfTheAccount = emailOfTheAccount;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getNumberOfReservations() {
        return numberOfReservations;
    }
    public void setNumberOfReservations(int numberOfReservations) {
        this.numberOfReservations = numberOfReservations;
    }
    public int getSlotCancelations() {
        return slotCancelations;
    }
    public void setSlotCancelations(int slotCancelations) {
        this.slotCancelations = slotCancelations;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    public void setVehicles(ArrayList<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }


}
