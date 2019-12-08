package gui;
import database.MySQLqueries;
import businessLogic.ValueObject;
import java.sql.Connection;
import java.sql.DriverManager;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class addBusRide extends Application {
    @Override
    public void start(Stage addStage){
        //bus Information
        Text busRideId = new Text("Bus Ride Id");
        TextField busRideIDText = new TextField();

        Text busNumber = new Text(" Bus Number");
        TextField busNumberText = new TextField();

        Text busRideDate = new Text("Bus Ride Date:");
        TextField busRideDateText = new TextField();

        Text busTo = new Text("To:");
        TextField busToText= new TextField();

        Text busFrom = new Text("From:");
        TextField busFromText = new TextField();

        Text boardingTime = new Text("Boarding Time");
        TextField boardingTimeText = new TextField();

        Text departureTime = new Text("Departure Time");
        TextField departureTimeText = new TextField();

        Text arrivalTime = new Text("Arrival Time");
        TextField arrivalTimeText = new TextField();

        Text busRideDuration = new Text("Bus Ride Duration");
        TextField busRideDurationText = new TextField();

        Text DestinationStop = new Text("Destination Stop:");
        TextField DestinationStopText = new TextField();

        Text busCompany = new Text(" Bus Company:");
        TextField busCompanyText = new TextField();

        Text busCapacity = new Text("Bus Capacity");
        TextField busCapacityText = new TextField();

        Text price = new Text("Price:");
        TextField priceText = new TextField();


        //Buttons for adding

        Button confirmAdding = new Button("Confirm adding this Ride?");
        Button back = new Button("Back");

        //grid

        GridPane gridPane = new GridPane();
        gridPane.setMinSize(300,300);
        gridPane.setPadding(new Insets(15,15,15,15));
        gridPane.setVgap(7);
        gridPane.setHgap(7);
        gridPane.setAlignment(Pos.CENTER);





    }

}
