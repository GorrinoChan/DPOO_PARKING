package Persistence;

import java.io.FileNotFoundException;
import java.sql.*;

public class SqlDao {
    private static SqlDao instance = null;
    private final String username;
    private final String password;
    private final String url;
    private Connection connection;
    private final String iniciUrl = "jdbc:mysql://";

    public SqlDao(SqlConfigurationDao configurationDao) throws FileNotFoundException {
        this.username = configurationDao.readJson().getDataBaseUsername();
        System.out.println(this.username);
        this.password = configurationDao.readJson().getDataBasePassword();
        System.out.println(this.password);
        this.url = iniciUrl + configurationDao.readJson().getDataBaseHost() + ":" + configurationDao.readJson().getDataBasePort() + "/" + configurationDao.readJson().getDataBaseName();
        System.out.println(this.url);
    }


    public static SqlDao getInstance() {
        if (instance == null){
            try {
                instance = new SqlDao(new SqlConfigurationDao());
                instance.connect();
            } catch (Exception e) {
                //HAY QUE MANDAR EXCEPCIÓN
            }
        }
        return instance;
    }

    public void connect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        }  catch (SQLException e) {
            throw new SQLException();

        } catch (ClassNotFoundException e) {

            System.out.println(e);
        }
    }

    public void disconnect() {
        try {
            this.connection.close();
        }  catch (SQLException e) {
            //HAY QUE MANDAR EXCEPCIÓN
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public void deleteObject(String nameTable, String column, String atribute) {
        Statement statement;
        try {
            statement = getInstance().getConnection().createStatement();
            String consulta = "DELETE FROM " + nameTable + " WHERE " + column + " = '" + atribute + "';";
            statement.executeUpdate(consulta);

        } catch (SQLException e) {
            //HAY QUE MANDAR EXCEPCIÓN
        }
    }

    public ResultSet readSpecific(String nameTable, String column, String atribute) {
        String query;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;

        query = "SELECT * FROM " + nameTable + " WHERE " + column + " = ?";

        try {
            preparedStatement = getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, atribute);
            resultSet = preparedStatement.executeQuery();
        }  catch (SQLException e) {
            //HAY QUE MANDAR EXCEPCIÓN
        }

        return resultSet;
    }

    public ResultSet readAllTable(String nameTable) {
        String query;
        PreparedStatement preparedStatement;
        ResultSet resultSet = null;
        query = "SELECT * FROM " + nameTable;
        try {
            preparedStatement = getInstance().getConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        }  catch (SQLException e) {
            //HAY QUE MANDAR EXCEPCIÓN

        }

        return resultSet;
    }


    public void updateString(String object, String informationToChange, String change, String reference, String typeOfReference) {
        String consult = "UPDATE ".concat(object).concat(" SET ").concat(informationToChange).concat("=").concat("'").concat(change).concat("'").concat(" WHERE ").concat(" ").concat(typeOfReference).concat("=").concat("'").concat(reference).concat("'").concat(";");
        Statement statement;
        try {
            statement = getInstance().getConnection().createStatement();
            statement.executeUpdate(consult);

        }  catch (SQLException e) {
            System.out.println("No funciona");
        }
    }

    public void updateIntAndBolean(String object, String informationToChange, String change, String reference, String typeOfReference) throws SQLException {

        //Si quieres hacer un update de un Boolean tiene que pasarle el true y false en minusculas, y si es un int tienes que pasar el valor int en formato string
        String consult = "UPDATE ".concat(object).concat(" SET ").concat(informationToChange).concat("=").concat(change).concat(" WHERE ").concat(" ").concat(typeOfReference).concat("=").concat("'").concat(reference).concat("'").concat(";");
        Statement statement;
        try {
            statement = getInstance().getConnection().createStatement();
            statement.executeUpdate(consult);
        }  catch (SQLException e) {
            throw new SQLException();
        }
    }
}
