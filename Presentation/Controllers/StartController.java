package Presentation.Controllers;

import Presentation.Views.*;

import java.io.FileNotFoundException;

public class StartController {
    private StartView startView;

    public StartController(StartView startView) {
        this.startView = startView;
        startView.getStartButton().addActionListener(e -> {
            try {
                openLogInView();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void openLogInView() throws FileNotFoundException {
        startView.dispose();
        LoadView loadView = new LoadView();
        new LoadController(loadView);
        loadView.setVisible(true);
    }
}