package ProjectRoot.Client.Admin.DeleteJourney;

import ProjectRoot.Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import util.NetworkUtil;

import java.io.IOException;

public class DeleteJourneyController {
    private Main main;
    private NetworkUtil nc;

    @FXML
    private ComboBox<String> start;

    @FXML
    private ComboBox<String> end;

    @FXML
    private ComboBox<String> date;

    @FXML
    private ComboBox<String> time;

    @FXML
    private ComboBox<String> bus;

    public void set(Main main, NetworkUtil nc) {
        this.nc = nc;
        this.main = main;
    }

    public void clearAction(ActionEvent event) {
        start.setValue("");
        end.setValue("");
        date.setValue("");
        time.setValue("");
        bus.setValue("");
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

    public void deleteAction(ActionEvent event) {
        if(start.getValue()==null)
        {
            main.showAlert("Starting location required","Put starting location");
        }
        else if(end.getValue()==null)
        {
            main.showAlert("End location required","Put End location");
        }
        else if(date.getValue()==null)
        {
            main.showAlert("Date required","Put Date");
        }
        else if(time.getValue()==null)
        {
            main.showAlert("Time required","Put Time");
        }
        else if(bus.getValue()==null)
        {
            main.showAlert("Bus name required","Put Bus name");
        }
        else
        {
            String st = start.getValue();
            String dst = end.getValue();
            String day = date.getValue();
            String t = time.getValue();
            String b = bus.getValue();
            nc.write("deleteJourney:"+st+","+dst+","+day+","+t+","+b);
            try {
                main.showAdminHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void getTime(MouseEvent mouseEvent) {
        if(date.getValue()==null)
        {
            main.showAlert("Date required","Put Date");
        }
        else
        {
            nc.write("getTime:"+start.getValue()+","+end.getValue()+","+date.getValue());
            String s[] =  ((String)nc.read()).split(",");
            ObservableList<String> options = FXCollections.observableArrayList(s);
            time.setItems(options);
            time.setVisibleRowCount(4);
        }
    }

    public void getBus(MouseEvent mouseEvent) {
        if(time.getValue()==null)
        {
            main.showAlert("Time required","Put Time");
        }
        else
        {
            nc.write("getBus:"+start.getValue()+","+end.getValue()+","+date.getValue()+","+time.getValue());
            String s[] =  ((String)nc.read()).split(",");
            ObservableList<String> options = FXCollections.observableArrayList(s);
            bus.setItems(options);
            bus.setVisibleRowCount(4);
        }
    }

    public void getStart(MouseEvent mouseEvent) {
        nc.write("getStart:admin");
        String s[] =  ((String)nc.read()).split(",");
        ObservableList<String> options = FXCollections.observableArrayList(s);
        start.setItems(options);
        start.setVisibleRowCount(4);
    }

    public void getDestination(MouseEvent mouseEvent) {
        if(start.getValue()==null)
        {
            main.showAlert("Starting location required","Put starting location");
        }
        else
        {
            nc.write("getDestination:"+start.getValue());
            String s[] =  ((String)nc.read()).split(",");
            ObservableList<String> options = FXCollections.observableArrayList(s);
            end.setItems(options);
            end.setVisibleRowCount(4);
        }
    }

    public void getDate(MouseEvent mouseEvent) {
        if(end.getValue()==null)
        {
            main.showAlert("End location required","Put End location");
        }
        else
        {
            nc.write("getDate:"+start.getValue()+","+end.getValue());
            String s[] =  ((String)nc.read()).split(",");
            ObservableList<String> options = FXCollections.observableArrayList(s);
            date.setItems(options);
            date.setVisibleRowCount(4);
        }
    }
}
