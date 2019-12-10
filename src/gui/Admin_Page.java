package gui;

import businessLogic.ValueObject;

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
public class Admin_Page extends Application{

    @Override
    public void start(Stage welcome){
        welcome.setTitle("Welcome Admin");

        Label busId = new Label(" Bus Id:");
        TextField busIdText = new TextField();
        //buttons
        Button addABusRide= new Button("Add a Bus Ride");
        Button deleteBusRide = new Button("Delete a Bus Ride");
        Button updateBusRide = new Button ("Update a Bus Ride");

        Button logout = new Button ("Log Out");

        //grid
        GridPane gridPane = new GridPane();
        gridPane.setVgap(7);
        gridPane.setHgap(7);
        gridPane.setPadding(new Insets(15,15,15,15));

        gridPane.add(addABusRide,0,0);
        gridPane.add(deleteBusRide,0,1);
        gridPane.add(updateBusRide,0,2);
        gridPane.add(logout,1,4);


        gridPane.setAlignment(Pos.CENTER);
        gridPane.setStyle("-fx-background-color: LIGHTGREY;");



        addABusRide.setMinWidth(200);

        logout.setMinWidth(200);

        //SCENE

        Scene scene = new Scene(gridPane, 500,200);
        welcome.setTitle("Admin");
        welcome.setScene(scene);
        welcome.show();


        //Admin Class Tools Button
        addABusRide.setOnAction(e -> {
            try{
                AdminControl add = new AdminControl();
                add.start(welcome);
            }catch(SQLException | ClassNotFoundException l) {
                System.out.println(l);
            }
        });
        deleteBusRide.setOnAction(e -> {
            try {
                AdminControl delete = new AdminControl();
                delete.start(welcome);
            } catch (SQLException | ClassNotFoundException n) {
                System.out.println(n);
            }
        });

        //BusUpdateAction

        updateBusRide.setOnAction(f->{
           AdminControl update = new AdminControl();
            update.start(welcome);
        });



    }
    public static void main(String[]args){
        Application.launch(args);
    }
}