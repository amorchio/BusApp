package gui;
	
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;

public class Main extends Application {
	Stage window;
	Scene scene1;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		//The buttons 
		Button bookBus = new Button("Book a Bus");
		Button changeRes = new Button("Change Bus Reservation");
		Button profilePg = new Button ("Profile Page");
		Button logOut = new Button("Logout");
		Label welcome = new Label("What would you like to do?");
		
		//The actions
		bookBus.setOnAction(e -> {
			
			BusReservation busrespg = new BusReservation();
			busrespg.start(new Stage());

			window.hide();
			
			});

		changeRes.setOnAction(e -> {
		
			UsersReservedBuses busres = new UsersReservedBuses();
			busres.start(new Stage());
			window.hide();
		});
		
		profilePg.setOnAction(e -> {
			
			ProfilePage profpg = new ProfilePage();
			profpg.start(new Stage());
			window.hide();
			
		});
				
		logOut.setOnAction(e -> {
			
			LoginScreen login = new LoginScreen();
			login.start(new Stage());
			window.hide();
			
		});
		
		//Layout
		VBox menuLayout = new VBox(20);
		menuLayout.getChildren().addAll(welcome, bookBus, changeRes, profilePg, logOut);
		menuLayout.setAlignment(Pos.CENTER);
		scene1 = new Scene(menuLayout, 1024, 683);
		
		
		
		//Set the scene
		window.setScene(scene1);
		window.setTitle("Main Menu");
		window.show();
  }
}
