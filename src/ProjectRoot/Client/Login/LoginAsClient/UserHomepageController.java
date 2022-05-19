package ProjectRoot.Client.Login.LoginAsClient;

import ProjectRoot.Client.Main;
import ProjectRoot.Client.Networking.Read;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import util.NetworkUtil;

import java.io.IOException;

public class UserHomepageController {
    private Main main;
    private NetworkUtil nc;

    @FXML
    private ComboBox<String> from;

    @FXML
    private ComboBox<String> to;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> time;

    @FXML
    private ComboBox<String> Busname;

    public void LogoutAction(ActionEvent actionEvent) {
        try {
            main.showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void seeTickets(ActionEvent actionEvent) {
        try {
            nc.write("getBookedTicket:"+main.usename);
            String s = (String)nc.read();
            main.showTicketsPage(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void SearchAction(MouseEvent mouseEvent) {
        if(from.getValue()==null)
        {
            main.showAlert("Starting location required","Put starting location");
        }
        else if(to.getValue()==null)
        {
            main.showAlert("End location required","Put End location");
        }
        else if(date.getEditor().getText()==null)
        {
            main.showAlert("Date required","Put Date");
        }
        else if(time.getValue()==null)
        {
            main.showAlert("Time required","Put Time");
        }
        else if(Busname.getValue()==null)
        {
            main.showAlert("Bus name required","Put Bus name");
        }
        else
        {
            String st = from.getValue();
            String dst = to.getValue();
            String day = date.getEditor().getText();
            String t = time.getValue();
            String b = Busname.getValue();
            nc.write("SearchJourney:"+st+","+dst+","+day+","+t+","+b);
            String s = (String)nc.read();
            Read.startreading(s);
        }
    }

    public void getStart(MouseEvent mouseEvent) {
        nc.write("getStart:admin");
        String s[] =  ((String)nc.read()).split(",");
        ObservableList<String> options = FXCollections.observableArrayList(s);
        from.setItems(options);
        from.setVisibleRowCount(4);
    }

    public void getDestination(MouseEvent mouseEvent) {
        if(from.getValue()==null)
        {
            main.showAlert("Starting location required","Put starting location");
        }
        else
        {
            nc.write("getDestination:"+from.getValue());
            String s[] =  ((String)nc.read()).split(",");
            ObservableList<String> options = FXCollections.observableArrayList(s);
            to.setItems(options);
            to.setVisibleRowCount(4);
        }
    }

    public void getJourney(MouseEvent mouseEvent) {
        if(time.getValue()==null)
        {
            main.showAlert("Time required","Put Time");
        }
        else
        {
            nc.write("getBus:"+from.getValue()+","+to.getValue()+","+date.getEditor().getText()+","+time.getValue());
            String s[] =  ((String)nc.read()).split(",");
            if(!s[0].equals("")) {
                ObservableList<String> options = FXCollections.observableArrayList(s);
                Busname.setItems(options);
                Busname.setVisibleRowCount(4);
            }
            else
            {
                main.showAlert("No Journey","Put another journey");
            }
        }
    }

    public void getTime(MouseEvent mouseEvent) {
        ObservableList<String> options = FXCollections.observableArrayList("9.00am", "11.00am", "2.00pm", "8.00pm", "10.00pm");
        time.setItems(options);
        time.setVisibleRowCount(4);
    }
}
