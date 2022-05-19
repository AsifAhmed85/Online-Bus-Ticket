package ProjectRoot.Client.Register;

import ProjectRoot.Client.Main;
import javafx.event.ActionEvent;
import util.NetworkUtil;

import java.io.IOException;

public class SuccessfulRegisterController {
    private NetworkUtil nc;
    private Main main;

    public void confirmAction(ActionEvent event) {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(Main main, NetworkUtil nc) {
        this.main = main;
        this.nc = nc;
    }
}
