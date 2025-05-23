package Business.Entities;

public class Configuration {
    private String databaseHost;
    private String databaseName;
    private String databaseUsername;
    private String databasePassword;
    private int databasePort;
    private double timeVehicle;

    public Configuration(String databaseHost, String datebaseName, String databaseUsername, String databasePassword, int databasePort, double timeVehicle) {
        this.databaseHost = databaseHost;
        this.databaseName = datebaseName;
        this.databaseUsername = databaseUsername;
        this.databasePassword = databasePassword;
        this.databasePort = databasePort;
        this.timeVehicle = timeVehicle;
    }

    public String getDatabaseHost() {
        return databaseHost;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    public String getDatabasePassword() {
        return databasePassword;
    }

    public int getDatabasePort() {
        return databasePort;
    }

    public double getTimeVehicle() {
        return timeVehicle;
    }
}

