package Business.Entities;

public class Account {

    private String nameOfTheAccount;
    private String emailOfTheAccount;
    private String password;
    private int numberOfReservations;
    private int slotCancellations;
    private boolean admin;


    public Account(String nameOfTheAccount, String emailOfTheAccount, String password, int numberOfReservations, int slotCancellations, boolean admin) {
        this.nameOfTheAccount = nameOfTheAccount;
        this.emailOfTheAccount = emailOfTheAccount;
        this.password = password;
        this.numberOfReservations = numberOfReservations;
        this.slotCancellations = slotCancellations;
        this.admin = admin;
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
    public int getSlotCancellations() {
        return slotCancellations;
    }
    public void setSlotCancellations(int slotCancellations) {
        this.slotCancellations = slotCancellations;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }



}
