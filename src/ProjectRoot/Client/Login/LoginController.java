package ProjectRoot.Client.Login;


import ProjectRoot.Client.Main;
import ProjectRoot.Client.Networking.Read;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.NetworkUtil;


public class LoginController {

    private Main main;
    private NetworkUtil nc;

    @FXML
    private TextField userText;

    @FXML
    private PasswordField passwordText;

    @FXML
    private Button login;

    @FXML
    private Button reset;

    @FXML
    private Button adminLogin;


    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void loginAction(ActionEvent actionEvent) {
        String userName = userText.getText();
        String password = passwordText.getText();
        if(userName.equals(""))
        {
            main.showAlert("Username required","Put Username");
        }
        else if (password.equals("") )
        {
            main.showAlert("Password required","Put Password");
        }
        else {
            nc.write("LoginAsUser:"+userName+","+password);
            String s;
            s = (String)nc.read();
            Read.startreading(s+"_"+userName);
        }

    }

    public void resetAction(ActionEvent actionEvent) {
        userText.setText("");
        passwordText.setText("");
    }

    public void registerAction(ActionEvent actionEvent) {
        try {
            main.showRegisterPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void AdminLoginAction(ActionEvent actionEvent) {
            String userName = userText.getText();
            String password = passwordText.getText();
        if(userName.equals(""))
        {
            main.showAlert("Username required","Put Username");
        }
        else if (password.equals("") )
        {
            main.showAlert("Password required","Put Password");
        }else {
            nc.write("LoginAsAdmin:"+userName+","+password);
            String s;
            s = (String)nc.read();
            Read.startreading(s);
        }
    }

    public void exitAction(ActionEvent actionEvent) {
        main.closeProgram();
    }


}
