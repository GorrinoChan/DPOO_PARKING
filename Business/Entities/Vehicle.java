package Business.Entities;

public class Vehicle {
    private String vehicleLicense;
    private String vehicleType;
    private Account vehicleAccountAssociated;

    public Vehicle(String vehicleLicense, String vehicleType, Account vehicleAccountAssociated) {
        this.vehicleLicense = vehicleLicense;
        this.vehicleType = vehicleType;
        this.vehicleAccountAssociated = vehicleAccountAssociated;
    }
    public String getVehicleLicense() {
        return vehicleLicense;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public Account getVehicleAccountAssociated() {
        return vehicleAccountAssociated;
    }
}
