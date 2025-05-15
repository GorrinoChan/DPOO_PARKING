package Presentation.Controllers;

import Presentation.Views.GraphView;
import Presentation.Views.UserMenuView;
import Presentation.Views.UserProfileView;
public class GraphController {
    private GraphView graphView;
    private UserMenuView userMenuView;

    public GraphController(GraphView graphView, UserMenuView userMenuView) {
        this.graphView = graphView;
        this.userMenuView = userMenuView;

        graphView.getReturnButton().addActionListener(e -> returnToMenu());
        graphView.getUserProfileButton().addActionListener(e -> openUserProfileView());
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