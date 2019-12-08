package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Admin_Page extends Application{

    @Override
    public void start(Stage welcome){
        welcome.setTitle("Welcome Admin");

        Label busId = new Label(" Bus Id:");
        TextField busIdText = new TextField();
        //buttons
        Button addABusRide= new Button("Add a Bus Ride");
        Button removeBusRide = new Button("Remove a Bus Ride");
        Button updateBusRide = new Button ("Update a Bus Ride");
        Button searchBusRide = new Button("Search a Bus Ride");
        Button logout = new Button ("Log Out");

        //grid
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7);
        gridPane.setHgap(7);
        gridPane.setPadding(new Insets(15,15,15,15));

        gridPane.add(addABusRide,0,0);
        gridPane.add(removeBusRide,0,1);
        gridPane.add(updateBusRide,0,2);
        gridPane.add(logout,1,4);


        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: LIGHTGREY;");

        //SCENE

        Scene scene = new Scene(gridPane, 500,200);
        welcome.setTitle("Admin");
        welcome.setScene(scene);
        welcome.show();

        //BusUpdateAction

        updateBusRide.setOnAction(e->{

            updateBusRide update = new updateBusRide();
            update.start(welcome);
        };




    }

}

