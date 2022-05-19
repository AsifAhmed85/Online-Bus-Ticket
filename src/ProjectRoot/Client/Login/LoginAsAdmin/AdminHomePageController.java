package ProjectRoot.Client.Login.LoginAsAdmin;
import ProjectRoot.Client.Main;
import javafx.event.ActionEvent;
import util.NetworkUtil;

import java.io.IOException;


public class AdminHomePageController {
    private Main main;
    private NetworkUtil nc;


    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void logoutAction(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void removeUserAction(ActionEvent actionEvent) {
        try {
            main.showDeleteUserPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteJourneyAction(ActionEvent event) {
        try {
            main.showDeleteJourney();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addJourneyAction(ActionEvent event) {
        try {
            main.showAddJourney();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
