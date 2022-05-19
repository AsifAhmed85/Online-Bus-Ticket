package ProjectRoot.Client;

import ProjectRoot.Client.Admin.AddJourney.AddJourneyController;
import ProjectRoot.Client.Admin.DeleteJourney.DeleteJourneyController;
import ProjectRoot.Client.Admin.DeleteUser.deleteUserController;
import ProjectRoot.Client.Booking.TicketBookingController;
import ProjectRoot.Client.Login.LoginAsAdmin.AdminHomePageController;
import ProjectRoot.Client.Login.LoginAsClient.UserHomepageController;
import ProjectRoot.Client.Networking.Read;
import ProjectRoot.Client.Register.SuccessfulRegisterController;
import ProjectRoot.Client.SeeTickets.showTicketsController;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import util.NetworkUtil;
import ProjectRoot.Client.Register.registerGUIcontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.Socket;

public class Main extends Application {

    public Stage stage;
    public String usename = "";
    private NetworkUtil nc;
    private Socket user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        try {
            user = new Socket("127.0.0.1",22755);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nc = new NetworkUtil(user);
        System.out.println("Connected to server");
        stage = primaryStage;
        stage.setOnCloseRequest(event -> closeProgram());
        try {
            showLoginPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Read(nc,this);
    }

    public void showLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login/LoginPage.fxml"));
        Parent root = loader.load();
        ProjectRoot.Client.Login.LoginController controller = loader.getController();
        controller.set(this,nc);
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 600, 400));

        stage.show();
    }

    public void showUserHomePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login/LoginAsClient/UserHomePage.fxml"));
        Parent root = loader.load();

        UserHomepageController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Home");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showRegisterPage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Register/registerGUI.fxml"));
        Parent root = loader.load();

        registerGUIcontroller controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Register");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showAdminHomePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login/LoginAsAdmin/AdminHomePage.fxml"));
        Parent root = loader.load();

        AdminHomePageController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Admin");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showBookingPage(String s) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Booking/BusTicket.fxml"));
        Parent root = loader.load();

        TicketBookingController controller = loader.getController();
        controller.set(this,nc,s);

        stage.setTitle("Book");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showTicketsPage(String s) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SeeTickets/showTickets.fxml"));
        Parent root = loader.load();

        showTicketsController controller = loader.getController();
        controller.set(this,nc,s);

        stage.setTitle("Tickets");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showSuccessfulRegistration() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Register/successfulRegister.fxml"));
        Parent root = loader.load();

        SuccessfulRegisterController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Successful Registration");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showAddJourney() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Admin/AddJourney/AddJourney.fxml"));
        Parent root = loader.load();

        AddJourneyController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Add Journey");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showDeleteUserPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Admin/DeleteUser/deleteUser.fxml"));
        Parent root = loader.load();

        deleteUserController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Delete");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }

    public void showDeleteJourney() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Admin/DeleteJourney/deleteJourney.fxml"));
        Parent root = loader.load();

        DeleteJourneyController controller = loader.getController();
        controller.set(this,nc);

        stage.setTitle("Delete Journey");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }


    public void closeProgram() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Close");
        window.setWidth(300);

        Label label = new Label();
        label.setText("Are you sure to exit?");
        Button stayButton = new Button("No");
        stayButton.setOnAction(e->window.close());
        Button closeButton = new Button("Exit");
        closeButton.setOnAction(e->closeProgram(window));

        VBox layout1 = new VBox(20);
        HBox layout2 = new HBox(50);
        layout2.getChildren().addAll(closeButton,stayButton);
        layout1.getChildren().addAll(label, layout2);
        layout1.setAlignment(Pos.CENTER);
        layout2.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
    }

    private void closeProgram(Stage st) {
        nc.write("Exit:exit");
        nc.closeConnection();
        st.close();
        stage.close();
    }

    public void showAlert(String title, String msg)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public boolean isThere(String s,char a)
    {
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) == a)
                return true;
        }
        return false;
    }
}
