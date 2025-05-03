package Presentation.Controllers;

import Presentation.Views.*;

public class StartController {
    private StartView startView;

    public StartController(StartView startView) {
        this.startView = startView;
        startView.getStartButton().addActionListener(e -> openLogInView());
    }

    private void openLogInView() {
        startView.dispose();
        /*LoadView loadView = new LoadView();
        new LoadController(loadView);
        loadView.setVisible(true);*/
        LogInView logInView = new LogInView();
        new LogInController(logInView);
        logInView.setVisible(true);
    }
}