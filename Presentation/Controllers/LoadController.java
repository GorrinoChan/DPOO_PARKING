package Presentation.Controllers;

import Presentation.Views.*;
import Business.Managers.InitializationManager;

import java.io.FileNotFoundException;

public class LoadController {
    private LoadView loadView;

    public LoadController(LoadView loadView) throws FileNotFoundException {
        this.loadView = loadView;

        InitializationManager initializationManager = new InitializationManager();
        new Thread(() -> {
            try {
                Thread.sleep(2000);
                verificarInicializacion(new InitializationManager());
                loadView.dispose();
                LogInView logInView = new LogInView();
                new LogInController(logInView);
                logInView.setVisible(true);
            } catch (Exception e) {
                loadView.setErrorMessage("Error: " + e.getMessage());
            }
        }).start();

    }

    private void verificarInicializacion(InitializationManager initializationManager) {
        try {
            initializationManager.prepareReadJson();
        } catch (RuntimeException e) {
            loadView.setErrorMessage("Archivo JSON no encontrado: " + e.getMessage());
        }

        try {
            initializationManager.readJsonForConfigDb();
        } catch (FileNotFoundException e) {
            loadView.setErrorMessage("Error al leer configuración de la base de datos: " + e.getMessage());

        }

        try {
            initializationManager.tryToConnectToDb();
        } catch (FileNotFoundException e) {
            loadView.setErrorMessage("No se pudo conectar a la base de datos: " + e.getMessage());

        }
    }

}
