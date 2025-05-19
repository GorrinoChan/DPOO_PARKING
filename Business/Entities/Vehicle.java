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
    public void setVehicleLicense(String vehicleLicense) {
        this.vehicleLicense = vehicleLicense;
    }
    public String getVehicleType() {
        return vehicleType;
    }
    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public Account getVehicleAccountAssociated() {
        return vehicleAccountAssociated;
    }
    public void setVehicleAccountAssociated(Account vehicleAccountAssociated) {
        this.vehicleAccountAssociated = vehicleAccountAssociated;
    }



}
