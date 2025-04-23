package Presentation.Controllers;
import Presentation.Views.*;

public class GraphController {
    private GraphView graphView;

    public GraphController(GraphView graphView) {
        this.graphView = graphView;
        graphView.getReturnButton().addActionListener(e -> returnToMenu());
        graphView.getUserProfileButton().addActionListener(e -> openUserProfileView());
    }

    private void openUserProfileView() {
        graphView.dispose();
        UserProfileView userProfileView = new UserProfileView();
        new UserProfileController(userProfileView);
        userProfileView.setVisible(true);
    }

    private void returnToMenu() {
        graphView.dispose();
        UserMenuView userMenuView = new UserMenuView();
        new UserMenuController(userMenuView);
        userMenuView.setVisible(true);
    }
}