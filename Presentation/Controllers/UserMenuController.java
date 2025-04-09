package Presentation.Controllers;

import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
import Presentation.Views.EnterParkingView;
import Presentation.Views.GraphView;
import Presentation.Views.SlotAvaliableView;
import Presentation.Views.SlotControlView;

public class UserMenuController {
    private UserMenuView userMenuView;

    public UserMenuController(UserMenuView userMenuView) {
        this.userMenuView = userMenuView;

        userMenuView.getEnterButton().addActionListener(e -> openEnterParkingView());
        userMenuView.getSlotControlButton().addActionListener(e -> openSlotControlView());
        userMenuView.getSlotAvaliableButton().addActionListener(e -> openSlotAvaliableView());
        userMenuView.getGraphButton().addActionListener(e -> openGraphView());
        userMenuView.getUserProfileButton().addActionListener(e -> openUserProfileView());

    }

    private void openUserProfileView() {
        userMenuView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void openEnterParkingView() {
        userMenuView.dispose();
        EnterParkingView enterParkingView = new EnterParkingView();
        new EnterParkingController(enterParkingView);
        enterParkingView.setVisible(true);
    }

    private void openSlotControlView() {
        userMenuView.dispose();
        SlotControlView slotControlView = new SlotControlView();
        new SlotControlController(slotControlView);
        slotControlView.setVisible(true);
    }

    private void openSlotAvaliableView() {
        userMenuView.dispose();
        SlotAvaliableView slotAvaliableView = new SlotAvaliableView();
        new SlotAvaliableController(slotAvaliableView);
        slotAvaliableView.setVisible(true);
    }

    private void openGraphView() {
        userMenuView.dispose();
        GraphView graphView = new GraphView();
        new GraphController(graphView);
        graphView.setVisible(true);
    }
}