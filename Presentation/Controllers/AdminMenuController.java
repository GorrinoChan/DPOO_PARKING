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
            try {
                simulator = new TrafficSimulator();
                simulator.resumInteger();
                simulatorThread = new Thread(simulator, "TrafficSimulator");
                simulatorThread.start();
            } catch (FileNotFoundException ex) {
                System.out.println("Error al ejecutarse");
            }
            adminMenuView.getPlayPauseButton().setText("Stop");
        } else {
            simulator.stopInteger();
            adminMenuView.getPlayPauseButton().setText("Play");
        }
    }

    private void openUserProfileView() {
        adminMenuView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView);
        adminProfileView.setVisible(true);
    }

    private void openManagementView() {
        adminMenuView.dispose();
        AdminManagement adminManagement = new AdminManagement();
        new AdminManagementController(adminManagement);
        adminManagement.setVisible(true);
    }

    private void openSlotAvaliableView() {
        adminMenuView.dispose();
        AdminSlotAvaliableView adminslotAvaliableView = new AdminSlotAvaliableView();
        new AdminSlotAvaliableController(adminslotAvaliableView);
        adminslotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        adminMenuView.dispose();
        AdminGraphView admingraphView = new AdminGraphView();
        new AdminGraphController(admingraphView);
        admingraphView.setVisible(true);
    }
}
