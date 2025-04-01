package Persistence;

import Business.Entities.Configuration;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SqlConfigurationDao {
    private static final String path = "files/config.json";

    private static Gson gson;


    void ConfigurationDao() {
        if (!new File(path).exists()) {
            //HAY QUE LANZAR UNA EXCEPCIÓN

        }else {
            gson = new GsonBuilder().setPrettyPrinting().create();
        }
    }

    public Configuration readJson() {
        try {
            return gson.fromJson(gson.newJsonReader(new FileReader(path)), new TypeToken<Configuration>(){}.getType());
        } catch (FileNotFoundException e) {
            //HAY QUE PILLAR LA EXCEPCION DE LECTURA
        }
        return null;
    }



}
