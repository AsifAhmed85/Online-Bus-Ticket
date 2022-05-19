package ProjectRoot.Client.Admin.DeleteUser;

import ProjectRoot.Client.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.NetworkUtil;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.*;

public class deleteUserController {

    private NetworkUtil nc;
    private Main main;

    @FXML
    private TextField username;

    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void deleteAction(ActionEvent event) {
        String s = username.getText();
        if (!s.equals("")) {
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setTitle("Delete");
            window.setWidth(300);

            Label label = new Label();
            label.setText("Are you sure to delete the user?");
            Button noButton = new Button("No");
            noButton.setOnAction(e -> window.close());
            Button confirmButton = new Button("Yes");
            confirmButton.setOnAction(e -> delete(window,s));

            VBox layout1 = new VBox(20);
            HBox layout2 = new HBox(50);
            layout2.getChildren().addAll(confirmButton, noButton);
            layout1.getChildren().addAll(label, layout2);
            layout1.setAlignment(Pos.CENTER);
            layout2.setAlignment(Pos.CENTER);

            Scene scene = new Scene(layout1);
            window.setScene(scene);
            window.showAndWait();
        }
        else
        {
            main.showAlert("Username Required","Enter Username");
        }
    }

    private void delete(Stage window, String s) {

        nc.write("deleteUser:"+s);
        String st = (String)nc.read();
        if(st.equalsIgnoreCase("successful"))
        {
            window.close();
            try {
                main.showAdminHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            main.showAlert("Wrong Username","There is person using this username");
            window.close();
        }
    }

    public void backAction(ActionEvent event) {
        try {
            main.showAdminHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
