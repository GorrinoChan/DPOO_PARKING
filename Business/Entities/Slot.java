package Business.Entities;

public class Slot {
    private int number;
    private int floor;
    private boolean occupation;
    private boolean reservation;

        public Slot(int number, int floor, boolean occupation, boolean reservation) {
        this.number = number;
        this.floor = floor;
        this.occupation = occupation;
        this.reservation = reservation;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public int getFloor() {
        return floor;
    }
    public void setFloor(int floor) {
        this.floor = floor;
    }
    public boolean isOccupation() {
        return occupation;
    }
    public void setOccupation(boolean occupation) {
        this.occupation = occupation;
    }
    public boolean isReservation() {
        return reservation;
    }
    public void setReservation(boolean reservation) {
        this.reservation = reservation;
    }

}
