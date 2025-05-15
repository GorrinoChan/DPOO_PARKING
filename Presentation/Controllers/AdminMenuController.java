package Presentation.Controllers;
import Business.TrafficSimulator;
import Presentation.Views.*;
import java.io.FileNotFoundException;


public class AdminMenuController {
    private AdminMenuView adminMenuView;
    private TrafficSimulator simulator;
    private boolean running = false;
    private Thread simulatorThread;

    public AdminMenuController(AdminMenuView adminMenuView) {
        this.adminMenuView = adminMenuView;

        adminMenuView.getSlots().addActionListener(e -> openManagementView());
        adminMenuView.getslotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        adminMenuView.getgraphButton().addActionListener(e -> openGraphView());
        adminMenuView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        adminMenuView.getPlayPauseButton().addActionListener(e -> playPauseTraficSim());


    }

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
                simulator.resumInteger(); // ya existe, solo la resumimos
            }

            adminMenuView.getPlayPauseButton().setText("Stop");

        } else {
            if (simulator != null) {
                simulator.stopInteger(); // detenemos sin eliminar
            }

            adminMenuView.getPlayPauseButton().setText("Play");
        }
    }

    private void openUserProfileView() {
        adminMenuView.setVisible(false);
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    private void openManagementView() {
        adminMenuView.setVisible(false);
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement, adminMenuView);
        adminManagement.setVisible(true);
    }

    private void openSlotAvaliableView() {
        adminMenuView.setVisible(false);
        AdminSlotAvaliableView adminslotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminslotAvaliableView, adminMenuView);
        adminslotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        adminMenuView.setVisible(false);
        AdminGraphView admingraphView = new AdminGraphView();
        new AdminGraphController(admingraphView,adminMenuView );
        admingraphView.setVisible(true);
    }
}