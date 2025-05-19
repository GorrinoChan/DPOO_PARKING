package Presentation.Controllers;

import Business.TrafficSimulator;
import Presentation.Views.*;

import java.io.FileNotFoundException;

/**
 * Controlador principal del menú del administrador.
 * <p>
 * Gestiona la navegación a diferentes vistas administrativas y el control de la simulación de tráfico.
 */
public class AdminMenuController {
    private AdminMenuView adminMenuView;
    private TrafficSimulator simulator;
    private boolean running = false;
    private Thread simulatorThread;

    /**
     * Constructor que inicializa el controlador del menú y configura los listeners de botones.
     *
     * @param adminMenuView Vista principal del menú del administrador.
     */
    public AdminMenuController(AdminMenuView adminMenuView) {
        this.adminMenuView = adminMenuView;
        adminMenuView.getSlots().addActionListener(e -> openManagementView());
        adminMenuView.getslotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        adminMenuView.getgraphButton().addActionListener(e -> openGraphView());
        adminMenuView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        adminMenuView.getPlayPauseButton().addActionListener(e -> playPauseTraficSim());
    }

    /**
     * Inicia o pausa la simulación de tráfico.
     * <p>
     * Si no se ha creado el simulador, lo inicializa y comienza en un nuevo hilo.
     * Si ya está en ejecución, alterna entre pausa y reanudación.
     */
    private void playPauseTraficSim() {
        running = !running;
        if (running) {
            if (simulator == null || simulatorThread == null || !simulatorThread.isAlive()) {
                try {
                    simulator = new TrafficSimulator();
                    simulator.resumInteger();
                    simulatorThread = new Thread(simulator, "TrafficSimulator");
                    simulatorThread.start();
                } catch (FileNotFoundException ex) {
                    System.out.println("Error al iniciar simulación");
                }
            } else {
                simulator.resumInteger();
            }
            adminMenuView.getPlayPauseButton().setText("Stop");
        } else {
            if (simulator != null) {
                simulator.stopInteger();
            }
            adminMenuView.getPlayPauseButton().setText("Play");
        }
    }

    /**
     * Abre la vista del perfil del administrador.
     */
    private void openUserProfileView() {
        adminMenuView.setVisible(false);
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    /**
     * Abre la vista de gestión de plazas (crear, editar, eliminar).
     */
    private void openManagementView() {
        adminMenuView.setVisible(false);
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement, adminMenuView);
        adminManagement.setVisible(true);
    }

    /**
     * Abre la vista con las plazas disponibles.
     */
    private void openSlotAvaliableView() {
        adminMenuView.setVisible(false);
        AdminSlotAvaliableView adminslotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminslotAvaliableView, adminMenuView);
        adminslotAvaliableView.setVisible(true);
    }

    /**
     * Abre la vista con las gráficas de estadísticas del sistema.
     */
    private void openGraphView() {
        adminMenuView.setVisible(false);
        AdminGraphView admingraphView = new AdminGraphView();
        new AdminGraphController(admingraphView, adminMenuView);
        admingraphView.setVisible(true);
    }
}