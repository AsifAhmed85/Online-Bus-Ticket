package ProjectRoot.Client.SeeTickets;

import ProjectRoot.Client.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.NetworkUtil;

import java.net.URL;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class showTicketsController implements Initializable{
    private Main main;
    private NetworkUtil nc;
    private List<String> removedbus = new ArrayList<>();

    @FXML private TableView<Ticket> table;
    @FXML private TableColumn<Ticket,String> name;
    @FXML private TableColumn<Ticket,String> start;
    @FXML private TableColumn<Ticket,String> end;
    @FXML private TableColumn<Ticket,String> date;
    @FXML private TableColumn<Ticket,String> time;
    @FXML private TableColumn<Ticket,String> seat;

    private final ObservableList<Ticket> bookedticket = FXCollections.observableArrayList();
    private String s;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        table.setItems(bookedticket);
    }


    public void deleteAction(ActionEvent event) {
        ObservableList<Ticket> selectedRows, alljourney;
        alljourney = table.getItems();

        selectedRows = table.getSelectionModel().getSelectedItems();

        for(Ticket bus: selectedRows)
        {
            alljourney.remove(bus);
            removedbus.add(bus.getName()+","+bus.getStart()+","+bus.getEnd()+","+bus.getDate()+","+bus.getTime()+","+bus.getSeat());
        }
        if(removedbus.size()==0)
        {
            main.showAlert("Selection Required","Select journey to delete");
        }
        else
        {
            confirm();
        }
    }

    private void confirm() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Delete");
        window.setWidth(300);

        Label label = new Label();
        label.setText("Are you sure to delete?");
        Button no = new Button("No");
        no.setOnAction(e->window.close());
        Button confirmButton = new Button("Yes");
        confirmButton.setOnAction(e->delete(window));

        VBox layout1 = new VBox(20);
        HBox layout2 = new HBox(50);
        layout2.getChildren().addAll(confirmButton,no);
        layout1.getChildren().addAll(label, layout2);
        layout1.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
    }

    private void delete(Stage window) {
        window.close();
        nc.write("deleteUserJourney:"+removedbus.get(0));
        removedbus.clear();
    }

    public void set(Main main, NetworkUtil nc, String s) {
        this.main = main;
        this.nc = nc;
        this.s = s;
        System.out.println(s);
        String[] st = s.split("-");
        int i = 0;
        while (i<st.length)
        {
            String[] seats = st[i+5].split(",");
            for(int j=0; j<seats.length; j++)
            {
                bookedticket.add(new Ticket(st[i + 0], st[i + 1], st[i + 2], st[i + 3], st[i + 4], seats[j]));
            }
            i+= 6;
        }
    }

    public void backAction(ActionEvent event) {
        try {
            main.showUserHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
