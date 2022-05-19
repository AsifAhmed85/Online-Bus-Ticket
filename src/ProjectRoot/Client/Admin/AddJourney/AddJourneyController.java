package ProjectRoot.Client.Admin.AddJourney;

import ProjectRoot.Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.NetworkUtil;

import java.io.IOException;

public class AddJourneyController {

    private NetworkUtil nc;
    private Main main;

    @FXML
    private TextField name;

    @FXML
    private TextField start;

    @FXML
    private TextField end;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> time;


    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void clearAction(ActionEvent event) {
        name.setText("");
        start.setText("");
        end.setText("");
        date.getEditor().setText("");
        time.setValue("");
    }

    public void addAction(ActionEvent event) {
        String busName = name.getText();
        String starting = start.getText();
        String destination = end.getText();
        String day = date.getEditor().getText();
        String t = time.getValue();
        if(busName.equals("")|| main.isThere(busName, '-')|| main.isThere(busName, ','))
        {
            main.showAlert("Bus name required","Put correct Bus name");
        }
        else if (starting.equals("") || main.isThere(starting, '-')|| main.isThere(busName, ','))
        {
            main.showAlert("Starting Location required","Put correct Starting Location");
        }
        else if (destination.equals("") || main.isThere(destination, '-')|| main.isThere(busName, ','))
        {
            main.showAlert("Destination required","Put Correct Destination");
        }
        else if (day.equals("") || main.isThere(day, '-')|| main.isThere(busName, ','))
        {
            main.showAlert("Fixed date required","Put Fixed date");
        }
        else if (t == null || t.equals(""))
        {
            main.showAlert("Fixed time required","Put Fixed time");
        }
        else {
            System.out.println("Add:"+busName+","+starting+","+destination+","+day+","+t);
            nc.write("Add:"+busName+","+starting+","+destination+","+day+","+t);
            String s = (String)nc.read();
            if(s.equalsIgnoreCase("added")) {
                try {
                    main.showAdminHomePage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else
            {
                main.showAlert("Already added journey","This journey is already added");
            }
        }
    }

    public void timeAction(MouseEvent mouseEvent) {
        ObservableList<String> options = FXCollections.observableArrayList("9.00am", "11.00am", "2.00pm", "8.00pm", "10.00pm");
        time.setItems(options);
        time.setVisibleRowCount(4);
    }

    public void backToAdminPage(ActionEvent event) {
        try {
            main.showAdminHomePage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
