package ProjectRoot.Client.Booking;

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
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class TicketBookingController implements Initializable{
    private Main main;
    private NetworkUtil nc;
    private List<String> booked = new ArrayList<>();
    private List<String> youbooked = new ArrayList<>();
    private String busname;
    private String start;
    private String destination;
    private String day;
    private String t;

    @FXML
    private Button A1;

    @FXML
    private Button A2;

    @FXML
    private Button A3;

    @FXML
    private Button A4;

    @FXML
    private Button B1;

    @FXML
    private Button B2;

    @FXML
    private Button B3;

    @FXML
    private Button B4;

    @FXML
    private Button C1;

    @FXML
    private Button C2;

    @FXML
    private Button C3;

    @FXML
    private Button C4;

    @FXML
    private Button D1;

    @FXML
    private Button D2;

    @FXML
    private Button D3;

    @FXML
    private Button D4;

    @FXML
    private Button E1;

    @FXML
    private Button E2;

    @FXML
    private Button E3;

    @FXML
    private Button E4;

    @FXML
    private Button F1;

    @FXML
    private Button F2;

    @FXML
    private Button F3;

    @FXML
    private Button F4;

    @FXML
    private Button G1;

    @FXML
    private Button G2;

    @FXML
    private Button G3;

    @FXML
    private Button G4;


    @FXML private TableView<journey> userbooked;
    @FXML private TableColumn<journey,String> name;
    @FXML private TableColumn<journey,String> date;
    @FXML private TableColumn<journey,String> time;
    @FXML private TableColumn<journey,String> seat;

    private final ObservableList<journey> bookedticket = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        time.setCellValueFactory(new PropertyValueFactory<>("time"));
        seat.setCellValueFactory(new PropertyValueFactory<>("seat"));
        userbooked.setItems(bookedticket);
        userbooked.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    }

    public void bookallAction(ActionEvent event) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Book");
        window.setWidth(350);

        Label label = new Label();
        label.setText("Do you really want to book?");
        Button confirmButton = new Button("Yes");
        confirmButton.setOnAction(e->book(window));
        Button noButton = new Button("No");
        noButton.setOnAction(e->window.close());

        VBox layout1 = new VBox(20);
        HBox layout2 = new HBox(50);
        layout2.getChildren().addAll(confirmButton,noButton );
        layout1.getChildren().addAll(label, layout2);
        layout1.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
    }

    private void book(Stage window) {
        if(youbooked.size()!=0)
        {
            String s = main.usename + "-" + busname + "-" + start + "-" + destination + "-" + day + "-" + t + "-";
            for (int i = 0; i < youbooked.size(); i++) {
                s += youbooked.get(i) + ",";
            }
            nc.write("Bookall:" + s);

            try {
                window.close();
                main.showUserHomePage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
        {
            main.showAlert("Selected seat required","Select seat");
        }
    }

    public void backAction(ActionEvent event) {
        try {
            main.showUserHomePage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAction(ActionEvent event) {
        ObservableList<journey> selectedRows, alljourney;
        alljourney = userbooked.getItems();

        selectedRows = userbooked.getSelectionModel().getSelectedItems();

        for(journey bus: selectedRows)
        {
            alljourney.remove(bus);
            youbooked.remove(bus.getSeat());
            booked.remove(bus.getSeat());
            setColor();
        }
    }

    public void bookAction(ActionEvent event) {
        String bt = ((Control)event.getSource()).getId();
        Button button = (Button) event.getSource();
        if(!booked.contains(bt))
        {
            booked.add(bt);
            youbooked.add(bt);
            userbooked.getItems().add(new journey(busname,day,t,bt));
            setred(button);
        }
    }

    private void setgreen(Button b)
    {
        b.setStyle("-fx-background-color: #17e44a");
    }

    private void setred(Button b)
    {
        b.setStyle("-fx-background-color: #f40d0d");
    }

    public void set(Main main, NetworkUtil nc,String str) {
        this.nc = nc;
        this.main = main;
        String[] s = str.split("-");
        start = s[0];
        destination = s[1];
        day = s[2];
        t = s[3];
        busname = s[4];
        String st[] = s[5].split(",");
        Collections.addAll(booked, st);
        setColor();
    }

    public void setColor() {
        if(booked.contains("A1"))setred(A1);
        else setgreen(A1);
        if(booked.contains("A2"))setred(A2);
        else setgreen(A2);
        if(booked.contains("A3"))setred(A3);
        else setgreen(A3);
        if(booked.contains("A4"))setred(A4);
        else setgreen(A4);
        if(booked.contains("B1"))setred(B1);
        else setgreen(B1);
        if(booked.contains("B2"))setred(B2);
        else setgreen(B2);
        if(booked.contains("B3"))setred(B3);
        else setgreen(B3);
        if(booked.contains("B4"))setred(B4);
        else setgreen(B4);
        if(booked.contains("C1"))setred(C1);
        else setgreen(C1);
        if(booked.contains("C2"))setred(C2);
        else setgreen(C2);
        if(booked.contains("C3"))setred(C3);
        else setgreen(C3);
        if(booked.contains("C4"))setred(C4);
        else setgreen(C4);
        if(booked.contains("D1"))setred(D1);
        else setgreen(D1);
        if(booked.contains("D2"))setred(D2);
        else setgreen(D2);
        if(booked.contains("D3"))setred(D3);
        else setgreen(D3);
        if(booked.contains("D4"))setred(D4);
        else setgreen(D4);
        if(booked.contains("E1"))setred(E1);
        else setgreen(E1);
        if(booked.contains("E2"))setred(E2);
        else setgreen(E2);
        if(booked.contains("E3"))setred(E3);
        else setgreen(E3);
        if(booked.contains("E4"))setred(E4);
        else setgreen(E4);
        if(booked.contains("F1"))setred(F1);
        else setgreen(F1);
        if(booked.contains("F2"))setred(F2);
        else setgreen(F2);
        if(booked.contains("F3"))setred(F3);
        else setgreen(F3);
        if(booked.contains("F4"))setred(F4);
        else setgreen(F4);
        if(booked.contains("G1"))setred(G1);
        else setgreen(G1);
        if(booked.contains("G2"))setred(G2);
        else setgreen(G2);
        if(booked.contains("G3"))setred(G3);
        else setgreen(G3);
        if(booked.contains("G4"))setred(G4);
        else setgreen(G4);
    }
}
