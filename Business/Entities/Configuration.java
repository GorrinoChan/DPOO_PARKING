package Business.Entities;
/**
 * La classe Configuration representa la configuració de la connexió a la base de dades.
 * Conté informació com el host, el nom de la base de dades, el nom d'usuari, la contrasenya,
 * el port i el temps associat a un vehicle.
 */
public class Configuration {
    private String host;
    private String datebaseName;
    private String username;
    private String password;
    private int port;
    private double timeVehicle;
    /**
     * Constructor per crear una instància de Configuration.
     *
     * @param host            l'adreça host de la base de dades
     * @param datebaseName    el nom de la base de dades
     * @param username        el nom d'usuari per connectar-se a la base de dades
     * @param password        la contrasenya per connectar-se a la base de dades
     * @param port            el port utilitzat per la connexió a la base de dades
     * @param timeVehicle     el temps associat a un vehicle
     */
    public Configuration(String host, String datebaseName, String username, String password, int port, double timeVehicle){
        this.host = host;
        this.datebaseName = datebaseName;
        this.username = username;
        this.password = password;
        this.port = port;
        this.timeVehicle = timeVehicle;
    }
    /**
     * Retorna l'host de la base de dades.
     * @return l'host de la base de dades
     */
    public String getHost(){return this.host;}
    /**
     * Retorna el nom d'usuari per connectar-se a la base de dades.
     * @return el nom d'usuari
     */
    public String getUsername() {return this.username;}
    /**
     * Retorna la contrasenya per connectar-se a la base de dades.
     * @return la contrasenya
     */
    public String getPassword() {return this.password;}

    /**
     * Retorna el port utilitzat per la connexió a la base de dades.
     * @return el port
     */
    public int getPort() {return this.port;}
    /**
     * Retorna el temps associat a un vehicle.
     * @return el temps associat a un vehicle
     */
    public double getTimeVehicle() {return this.timeVehicle;}
    /**
     * Retorna el nom de la base de dades.
     * @return el nom de la base de dades
     */
    public String getDatabaseName() {return this.datebaseName;}
}

