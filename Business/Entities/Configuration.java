package Business.Entities;

public class Configuration {
    private String dataBaseHost;
    private String dataBaseName;
    private String dataBaseUsername;
    private String dataBasePassword;
    private int dataBasePort;
    private double timeVehicle;

    public Configuration(String dataBaseHost, String dataBaseName, String dataBaseUsername, String dataBasePassword, int dataBasePort, double timeVehicle) {
        this.dataBaseHost = dataBaseHost;
        this.dataBaseName = dataBaseName;
        this.dataBaseUsername = dataBaseUsername;
        this.dataBasePassword = dataBasePassword;
        this.dataBasePort = dataBasePort;
        this.timeVehicle = timeVehicle;
    }

    public String getDataBaseHost() {
        return dataBaseHost;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public String getDataBaseUsername() {
        return dataBaseUsername;
    }

    public String getDataBasePassword() {
        return dataBasePassword;
    }

    public int getDataBasePort() {
        return dataBasePort;
    }

    public double getTimeVehicle() {
        return timeVehicle;
    }
}

