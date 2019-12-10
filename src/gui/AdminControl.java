package gui;
import businessLogic.ValueObject;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.Dialog;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class AdminControl extends Application{
    private Label sceneTitle = new Label("Add");
    private Label busID = new Label("Bus ID");
    private Label origin = new Label("origin");
    private Label destination = new Label("destination");
    private Label departureDate = new Label("Departure Date");
    private Label departureTime = new Label("Departure Time");
    private Label arriveDate = new Label("Arrival Date");
    private Label arriveTime = new Label("Arrival Time");
    private Label currentCapacity = new Label("CurrentSeat");
    private Label maxCapacity = new Label("MaxSeat");
    private  Label txtAddBus = new Label("Add a Bus:");

    // Bottom Row
    private  Label txtBusID = new Label("Bus ID");
    private  Label txtOrigin = new Label("origin");
    private Label txtDestination = new Label("destination");
    private Label txtDepartDate = new Label("Departure Date");
    private Label txtArrivalDate = new Label("Arrival Date");
    private Label txtDepartTime = new Label("Departure Time");
    private Label txtArriveTime = new Label("Arrival Time");
    private Label txtCurrentCapacity = new Label("Current Capacity");
    private Label txtMaxCapacity = new Label("Max Capacity");

    private Button btnAddBus = new Button("Add Bus");
    private Button btnDeleteBus = new Button("Delete Bus");
    private Button btnUpdateBus = new Button("Update Bus");
    private Button btnBack = new Button("Main Menu");

    private TextField busIDBOX = new TextField();
    private TextField originBOX = new TextField();
    private TextField destinationBOX = new TextField();
    private TextField departDateBOX = new TextField("0000-00-00");
    private TextField arrivalDateBOX = new TextField("0000-00-00");
    private TextField fromTime = new TextField("00:00:00");
    private TextField toTime = new TextField("00:00:00");
    private TextField fldCurrentCapacity = new TextField();
    private TextField fldMaxCapacity = new TextField();

    private static boolean checkDateF(String busDate) {
        return busDate.matches("^(20)\\d\\dd([-])(0[1-9]|1{012})([-])(0[1-9]|[12][0-9]|3[01])$");
    }


    @Override
    public void start(Stage primaryStage) throws ClassNotFoundException, SQLException {

        HBox hBoxTitle = new HBox(15);
        hBoxTitle.getChildren().addAll(sceneTitle);
        hBoxTitle.setAlignment(Pos.CENTER);

        GridPane grid = new GridPane();
        grid.add(busID, 0, 1);
        grid.add(origin, 1, 1);
        grid.add(destination, 2, 1);
        grid.add(departureDate, 3, 1);
        grid.add(arriveDate, 4, 1);
        grid.add(departureTime, 5, 1);
        grid.add(arriveTime, 6, 1);
        grid.add(currentCapacity, 7, 1);
        grid.add(maxCapacity, 8, 1);

        ValueObject vo = new ValueObject();
        //bustable needed
        TextArea textArea = new TextArea();


        //adding flight

        textArea.setMinWidth(475);
        grid.setAlignment(Pos.CENTER);
        grid.add(textArea, 0, 2, 9, 1);
        grid.add(txtAddBus, 0, 3);
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        GridPane gridBtm = new GridPane();
        gridBtm.setHgap(10);
        gridBtm.setVgap(10);
        gridBtm.setPadding(new Insets(25, 25, 25, 25));

        gridBtm.add(txtAddBus, 0, 0);
        gridBtm.add(txtBusID, 0, 1);
        gridBtm.add(busIDBOX, 1, 1, 3, 1);
        gridBtm.add(txtOrigin, 0, 2);
        gridBtm.add(originBOX, 1, 2);
        gridBtm.add(txtDestination, 2, 2);
        gridBtm.add(destinationBOX, 3, 2);
        gridBtm.add(txtDepartDate, 0, 3);
        gridBtm.add(departDateBOX, 1, 3);
        gridBtm.add(txtArrivalDate, 2, 3);
        gridBtm.add(arrivalDateBOX, 3, 3);
        gridBtm.add(txtDepartTime, 0, 4);
        gridBtm.add(fromTime, 1, 4);
        gridBtm.add(txtArriveTime, 2, 4);
        gridBtm.add(toTime, 3, 4);
        gridBtm.add(txtMaxCapacity, 0, 5);
        gridBtm.add(fldMaxCapacity, 1, 5);
        gridBtm.add(txtCurrentCapacity, 2, 5);
        gridBtm.add(fldCurrentCapacity, 3, 5);
        gridBtm.add(btnAddBus, 1, 6, 3, 1);
        btnAddBus.setMinWidth(500);

        gridBtm.add(btnBack, 1, 8, 2, 1);
        btnBack.setMinWidth(150);
        gridBtm.add(btnUpdateBus, 2, 8);
        btnUpdateBus.setMinWidth(150);
        gridBtm.add(btnDeleteBus, 3, 8);
        btnDeleteBus.setMinWidth(150);

        gridBtm.setAlignment(Pos.CENTER);
        gridBtm.setPadding(new Insets(25, 25, 25, 25));
        gridBtm.setHgap(10);
        gridBtm.setVgap(10);

        VBox vbox = new VBox(20);
        Scene sceneText = new Scene(vbox, 700, 700);
        vbox.getChildren().addAll(hBoxTitle, grid, gridBtm);
        vbox.setAlignment(Pos.CENTER);
        primaryStage.setTitle("Add/Update/Delete Bus Rides"); // set title
        primaryStage.setScene(sceneText);
        primaryStage.show();

        //add bus action
        //btnA//ddBus.setOnAction(new EventHandler<ActionEvent>() {


        ValueObject vc = new ValueObject();
       /* if (checkDateF(departDateBOX.getText()) != true
                && checkDateF(arrivalDateBOX.getText()) != true) {
            Label actionTarget = new Label(
                    "INVALID DATE. Enter as YYYY/MM/DD (ex. 2018/12/09, 2018/07/27, etc)");
            grid.add(actionTarget, 1, 7, 3, 1);
//time check

        } else {

            try {
                int fNum = Integer.parseInt(busIDBOX.getText());
                int cCap = Integer.parseInt(fldCurrentCapacity.getText());
                int mCap = Integer.parseInt(fldMaxCapacity.getText());
                vo.addBus(fNum, originBOX.getText(), destinationBOX.getText(), departDateBOX.getText(),
                        arrivalDateBOX.getText(), fromTime.getText(), toTime.getText(), cCap, mCap);
                System.out.println("Flight Successfully Added");
                AdminControl add = new AdminControl();

                add.start(primaryStage);
            } catch (SQLException | ClassNotFoundException n) {
                System.out.println(n);
            }
        }*/


        btnUpdateBus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    AdminControl update = new AdminControl();
                    update.start(primaryStage);
                } catch (SQLException | ClassNotFoundException n) {
                    System.out.println(n);
                }
            }
        });

        btnDeleteBus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    AdminControl delete = new AdminControl();
                    delete.start(primaryStage);
                } catch (SQLException | ClassNotFoundException n) {
                    System.out.println(n);
                }
            }
        });


    }
    public static void main(String[]args){
        Application.launch(args);
    }




}