package Presentation.Controllers;

import Presentation.Views.StartView;
import Presentation.Views.LogInView;

public class StartController {
    private StartView startView;

    public StartController(StartView startView) {
        this.startView = startView;
        startView.getStartButton().addActionListener(e -> openLogInView());
    }

    private void openLogInView() {
        startView.dispose();
        LogInView logInView = new LogInView();
        new LogInController(logInView);
        logInView.setVisible(true);
    }
}