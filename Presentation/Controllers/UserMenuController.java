package Presentation.Controllers;
import Business.Managers.InitializationManager;
import Business.Managers.UserAccountManager;
import Business.TrafficSimulator;
import Presentation.Views.*;

import javax.swing.*;
import java.io.FileNotFoundException;

public class UserMenuController {
    private UserMenuView userMenuView;
    private TrafficSimulator simulator;
    private boolean running = false;
    private Thread simulatorThread;

    public UserMenuController(UserMenuView userMenuView) {
        this.userMenuView = userMenuView;

        userMenuView.getEnterButton().addActionListener(e -> openEnterParkingView());
        userMenuView.getSlotControlButton().addActionListener(e -> openSlotControlView());
        userMenuView.getSlotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        userMenuView.getGraphButton().addActionListener(e -> openGraphView());
        userMenuView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        userMenuView.getPlayPauseButton().addActionListener(e -> playPauseTraficSim());

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
                simulator.resumInteger();
            }

            userMenuView.getPlayPauseButton().setText("Stop");

        } else {
            if (simulator != null) {
                simulator.stopInteger();
            }

            userMenuView.getPlayPauseButton().setText("Play");
        }
    }

    private void openUserProfileView() {
        userMenuView.setVisible(false);
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void openEnterParkingView() {
        userMenuView.setVisible(false);
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView, userMenuView);
        enterParkingView.setVisible(true);
    }

    private void openSlotControlView() {
        InitializationManager initializationManager = new InitializationManager();
        UserAccountManager userAccountManager = new UserAccountManager();
        String userName = userAccountManager.getUserName();
        int reservationCanceled = initializationManager.getNumberOfReservationOfUserThatHaveBeenCanceledByAdmin(userName);

        if (reservationCanceled > 0){
            JOptionPane.showMessageDialog(null, "Hola " + userName + ", se te han cancelado " + reservationCanceled + " reservas.");
        }
        userMenuView.setVisible(false);
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView, userMenuView);
        slotControlView.setVisible(true);
    }

    private void openSlotAvaliableView() {
        userMenuView.setVisible(false);
        SlotAvaliableView slotAvaliableView = new SlotAvaliableView();
        new SlotAvaliableController(slotAvaliableView, userMenuView);
        slotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        userMenuView.setVisible(false);
        GraphView graphView = new GraphView();
        new GraphController(graphView, userMenuView);
        graphView.setVisible(true);
    }
}