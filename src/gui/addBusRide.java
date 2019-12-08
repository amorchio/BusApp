package gui;
import database.MySQLqueries;
import businessLogic.ValueObject;
import java.sql.Connection;
import java.sql.DriverManager;
import gui.Admin_page;

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
        Text busRideId = new Text("Bus Ride Id");
        TextField busRideIDText = new TextField();
    }

}
