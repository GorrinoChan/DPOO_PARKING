package Presentation.Controllers;

import Presentation.Views.AdminGraphView;
import Presentation.Views.AdminMenuView;


public class AdminGraphController {
    private AdminGraphView admingraphView;

    public AdminGraphController(AdminGraphView admingraphView) {
        this.admingraphView = admingraphView;
        admingraphView.getReturnButton().addActionListener(e -> returnToMenu());
    }

    private void returnToMenu() {
        admingraphView.dispose();
        AdminMenuView adminMenuView = new AdminMenuView();
        new AdminMenuController(adminMenuView);
        adminMenuView.setVisible(true);
    }
}
