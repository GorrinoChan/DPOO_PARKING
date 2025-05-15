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
            System.out.println("Error 1");
            loadView.setErrorMessage("Archivo JSON no encontrado: " + e.getMessage());
            verificacion = false;

        }

        try {
            initializationManager.readJsonForConfigDb();
        } catch (FileNotFoundException e) {
            System.out.println("Error 2");
            loadView.setErrorMessage("Error al leer configuración de la base de datos: " + e.getMessage());
            verificacion = false;

        }

        try {
            initializationManager.tryToConnectToDb();
        } catch (FileNotFoundException e) {
            System.out.println("Error 3");
            loadView.setErrorMessage("No se pudo conectar a la base de datos: " + e.getMessage());
            verificacion = false;

        } catch (SQLException e) {
            System.out.println("Error 3");
            loadView.setErrorMessage("No se pudo conectar a la base de datos: " + e.getMessage());
            verificacion = false;
        }
        return verificacion;
    }

}
