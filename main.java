import Presentation.Controllers.*;
import Presentation.Views.*;
import javax.swing.*;

public class main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StartView startView = new StartView();
            new StartController(startView);
            startView.setVisible(true);
        });
    }
}
