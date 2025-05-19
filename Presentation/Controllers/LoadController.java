package Presentation.Controllers;

import Presentation.Views.*;
import Business.Managers.InitializationManager;

import java.io.FileNotFoundException;
import java.sql.SQLException;

public class LoadController {
    private LoadView loadView;

    public LoadController(LoadView loadView) {
        this.loadView = loadView;

        InitializationManager initializationManager = new InitializationManager();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                if (verificarInicializacion(new InitializationManager())){
                    loadView.dispose();
                    LogInView logInView = new LogInView();
                    new LogInController(logInView);
                    logInView.setVisible(true);
                }

            } catch (Exception e) {
                loadView.setErrorMessage("Error: " + e.getMessage());
            }
        }).start();

    }

    private boolean verificarInicializacion(InitializationManager initializationManager) {
        boolean verificacion = true;
        try {
            initializationManager.prepareReadJson();
        } catch (RuntimeException e) {
            loadView.setErrorMessage("Archivo JSON no encontrado: " + e.getMessage());
            verificacion = false;

        }

        try {
            initializationManager.readJsonForConfigDb();
        } catch (FileNotFoundException e) {
            loadView.setErrorMessage("Error al leer configuración de la base de datos");
            verificacion = false;

        }

        try {
            initializationManager.tryToConnectToDb();
        } catch (FileNotFoundException e) {
            loadView.setErrorMessage("No se ha encotrado el fichero de la base de datos");
            verificacion = false;

        } catch (SQLException e) {
            loadView.setErrorMessage("La contraseña de la base de datos no es correcta");
            verificacion = false;
        }
        return verificacion;
    }

}
