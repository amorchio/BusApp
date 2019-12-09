package gui;
	
import java.util.ArrayList;
import businessLogic.ValueObject;
import database.MySQLqueries;
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
		//repopulate user with updated user information from the database
		ValueObject user = new ValueObject(MySQLqueries.getUser());
		window = primaryStage;
		System.out.println("First name : " + user.getFirstName());
		System.out.println("Admin value: " + user.getAdmin());
		//The buttons 
		Button bookBus = new Button("Book a Bus");
		Button changeRes = new Button("Change Bus Reservation");
		Button profilePg = new Button ("Profile Page");
		Button admin = new Button ("Admin Options");
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
			
		admin.setOnAction(e -> {
			
			Admin_Page adminpg = new Admin_Page();
			adminpg.start(new Stage());
			window.hide();
			
		});
		
		//Layout
		VBox menuLayout = new VBox(20);
		
		if (user.getAdmin() == 1) {
			menuLayout.getChildren().addAll(welcome, bookBus, changeRes, profilePg, logOut, admin);
		}
		else {
			menuLayout.getChildren().addAll(welcome, bookBus, changeRes, profilePg, logOut);
		}
		menuLayout.setAlignment(Pos.CENTER);
		scene1 = new Scene(menuLayout, 1024, 683);
		
		
		
		//Set the scene
		window.setScene(scene1);
		window.setTitle("Main Menu");
		window.show();
  }
}
