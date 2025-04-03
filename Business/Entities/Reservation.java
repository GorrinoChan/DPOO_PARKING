package Business.Entities;

public class Reservation extends Slot {
    private String date;
    private boolean cancelled;

    public Reservation(int number, int floor, boolean occupation, boolean reservation, String date, boolean cancelled) {
        super(number, floor, occupation, reservation);
        this.date = date;
        this.cancelled = cancelled;
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
    
}
