package Business.Managers;

import Business.Entities.Configuration;
import Persistence.SqlConfigurationDao;
import Persistence.SqlDao;

import java.io.FileNotFoundException;

public class InitializationManager {

    Configuration configuration;
    SqlConfigurationDao sqlConfigurationDao;
    SqlDao sqlDao;

    public InitializationManager() {
    }

    public void prepareReadJson(){
        try{
            this.sqlConfigurationDao = new SqlConfigurationDao();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void readJsonForConfigDb() throws FileNotFoundException {
        this.configuration = this.sqlConfigurationDao.readJson();
    }

    public void tryToConnectToDb() throws FileNotFoundException {
        this.sqlDao = new SqlDao(this.sqlConfigurationDao);
        this.sqlDao.connect();
    }

    public void tryToDisconectDB(){
        this.sqlDao.disconnect();

    }


}
