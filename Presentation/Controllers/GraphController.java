package Presentation.Controllers;

import Business.Managers.UserAccountManager;
import Presentation.Views.GraphView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
import Business.Managers.UserSlotManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class GraphController {
    private GraphView graphView;
    private UserMenuView userMenuView;
    private UserSlotManager userSlot = new UserSlotManager();
    private ArrayList<Integer> bars = new ArrayList<>();

    public GraphController(GraphView graphView, UserMenuView userMenuView) {
        this.graphView = graphView;
        this.userMenuView = userMenuView;

        graphView.getReturnButton().addActionListener(e -> returnToMenu());
        graphView.getUserProfileButton().addActionListener(e -> openUserProfileView());


        try {
            bars.add(userSlot.readAllSlot().size());
        }catch(SQLException e){
            bars.add(0);
        }
        graphView.updateData(new ArrayList<>(bars));
        graphView.refreshGraph();

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    if (bars.size() == 40) {
                        for(int i = 0; i < 40; i++) {
                            bars.remove(i);
                        }
                    }
                    int size = userSlot.readAllSlot().size();
                    bars.add(size);
                }catch(SQLException e1){
                    bars.add(0);
                }

                graphView.updateData(new ArrayList<>(bars));
                graphView.refreshGraph();
            }
        });
        timer.start();

    }

    private void openUserProfileView() {
        graphView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView, userMenuView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        graphView.dispose();
        userMenuView.setVisible(true);
    }
}