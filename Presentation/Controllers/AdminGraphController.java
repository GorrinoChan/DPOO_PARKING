package Presentation.Controllers;

import Business.Managers.UserSlotManager;
import Presentation.Views.AdminGraphView;
import Presentation.Views.AdminMenuView;
import Presentation.Views.AdminProfileView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


public class AdminGraphController {
    private AdminGraphView admingraphView;
    private AdminMenuView adminMenuView;
    private UserSlotManager userSlot = new UserSlotManager();
    private ArrayList<Integer> bars = new ArrayList<>();
    private final static int MAX_NUM_BARS = 36;

    public AdminGraphController(AdminGraphView admingraphView, AdminMenuView adminMenuView) {
        this.admingraphView = admingraphView;
        this.adminMenuView = adminMenuView;
        admingraphView.getReturnButton().addActionListener(e -> returnToMenu());
        admingraphView.getUserProfileButton().addActionListener(e -> openUserProfileView());
        try {
            bars.add(userSlot.readAllReservation().size());
        }catch(SQLException e){
            bars.add(0);
        }
        admingraphView.updateData(new ArrayList<>(bars));
        admingraphView.refreshGraph();

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (bars.size() == MAX_NUM_BARS) {
                        for(int i = 0; i < (MAX_NUM_BARS - 1); i++) {
                            bars.remove(0);
                        }
                    }
                    int size = userSlot.readAllReservation().size();
                    bars.add(size);
                }catch(SQLException e1){
                    bars.add(0);
                }
                admingraphView.updateData(new ArrayList<>(bars));
                admingraphView.refreshGraph();
            }
        });
        timer.start();



    }

    private void openUserProfileView() {
        admingraphView.dispose();
        AdminProfileView adminProfileView = new AdminProfileView();
        new AdminProfileController(adminProfileView, adminMenuView);
        adminProfileView.setVisible(true);
    }

    private void returnToMenu() {
        admingraphView.dispose();
        adminMenuView.setVisible(true);

    }
}
