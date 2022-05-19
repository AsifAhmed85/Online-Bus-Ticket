package ProjectRoot.Client.Register;

import ProjectRoot.Client.Main;
import ProjectRoot.Client.Networking.Read;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import util.NetworkUtil;
import javafx.scene.input.MouseEvent;


import java.io.IOException;

public class registerGUIcontroller {
    private Main main;
    private NetworkUtil nc;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private DatePicker birthdate;

    @FXML
    private ComboBox<String> gender;

    @FXML
    private TextField email;

    @FXML
    private TextField mobile;



    public void backToLoginPage(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void clearAction(ActionEvent actionEvent) {
        username.setText("");
        password.setText("");
        email.setText("");
        mobile.setText("");
        birthdate.getEditor().setText("");
        gender.setValue("");
    }

    public void registerAction(ActionEvent actionEvent) {
        String userName = username.getText();
        String Password = password.getText();
        String mailID = email.getText();
        String no = mobile.getText();
        String date = birthdate.getEditor().getText();
        String gen = gender.getValue();
        if(userName.equals("") || main.isThere(userName, ' ') || main.isThere(userName, '_')|| main.isThere(userName, '-')|| main.isThere(userName, ','))
        {
            main.showAlert("Username required","Put correct Username");
        }
        else if (Password.equals("") || main.isThere(Password, ' '))
        {
            main.showAlert("Password required","Put correct Password");
        }
        else if (date.equals("") || main.isThere(date, ' ')|| main.isThere(date, '-')|| main.isThere(userName, ',')|| main.isThere(userName, '-'))
        {
            main.showAlert("Birth date required","Put correct Birth date");
        }
        else if (gen.equals("") || gen == null)
        {
            main.showAlert("Gender required","Put Gender");
        }
        else if (mailID.equals("")|| main.isThere(mailID, ' ' ))
        {
            main.showAlert("Email ID required","Put correct Email ID");
        }
        else if (no.equals("")|| main.isThere(no, ' ' ) )
        {
            main.showAlert("Mobile number required","Put correct Mobile number");
        }
        else {
            System.out.println("Register:"+userName+","+Password+","+date+","+gen+","+mailID+","+no);
            nc.write("Register:"+userName+","+Password+","+date+","+gen+","+mailID+","+no);
            String s;
            s = (String)nc.read();
            Read.startreading(s);
        }
    }

    public void genderAction(MouseEvent mouseEvent) {
        ObservableList<String> options = FXCollections.observableArrayList("Male", "Female");
        gender.setItems(options);
    }
}
