package Business.Entities;

public class Reservation extends Slot {
    private String date;
    private boolean cancelled;
    private String licencePlate;
    private String userName;

    public Reservation(int number, int floor, boolean occupation, boolean reservation,  String typeOfPlace, String date, boolean cancelled, String licencePlate, String userName) {
        super(number, floor, occupation, reservation, typeOfPlace);
        this.date = date;
        this.cancelled = cancelled;
        this.licencePlate = licencePlate;
        this.userName = userName;

    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public boolean isCancelled() {
        return cancelled;
    }
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public String getLicencePlate() {
        return licencePlate;
    }
}
