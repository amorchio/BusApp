package gui;


import java.sql.SQLException;

import businessLogic.ValueObject;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;

public class Admin_page extends Application  {
    Button buttonBook = new Button("Book a Bus Ride");(
    Button buttonCancel = new Button("Cancel a Bus Ride");
    Button buttonSearch = new Button("Search Bus Rides");
    Button buttonMyRides = new Button("View my Rides");
    Button buttonAddRides = new Button ("Add/ Edit/ Delete Flight");

    Text textAdmin = new Text("Admin Tools;");

    Button buttonLogout = new Button ("Log Out");

    @Override

    public void start(Stage primaryStage){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(30,30,30,30));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        //Bus Ride Buttons

        gridPane.add(buttonMyRides, 0, 1);
        gridPane.add(buttonSearch, 0,2);
        gridPane.add(buttonBook, 0,3);
        gridPane.add(textAdmin,0,6);
        gridPane.add(buttonAddRides,0,7);

        gridPane.add(buttonLogout,0,11);

        //Width for buttons
        buttonMyRides.setMinWidth(200);
        buttonSearch.setMinWidth(200);
        buttonBook.setMinWidth(200);
        buttonCancel.setMinWidth(200);



        //button events
    buttonMyRides.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try{
                viewMyRides viewRides = new viewMyRides();
                viewRides.start(primaryStage);
            }catch(SQLException|ClassNotFoundException exception){
                System.out.println(exception);
            }

        }
    });

    buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            try{
                Search search = new Search();
                search.start(primaryStage);
            }catch(SQLException| ClassNotFoundException exception){
                System.out.println(exception);
            }

        }
    });

    //Logout

        buttonLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ValueObject valueObject = new ValueObject();
                try{

                }
            }
        });












    }



}


