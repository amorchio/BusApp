package gui;

import businessLogic.ValueObject;
import database.MySQLqueries;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;

//Scene that shows the buses that the user has already reserved
public class UsersReservedBuses extends Application {
	Stage window;
	Scene scene1;
	
	//gets userdata
	static ValueObject user = new ValueObject(MySQLqueries.getUser());

	
	public static void main(String[] args) {
		//Generate the PNR
		String PNR = user.generatePNR();
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) {
		 window = primaryStage;
		 
		
		Button mainMenu = new Button("Main Menu");
		//delete reservation button
		Button button = new Button("Click to delete reservation");
		button.setOnAction(e -> {
			//hardcoded for testing PLEASE DELETE
			String pnr = "522OXA";
			//calls delete reservation method which will delete reservation
			MySQLqueries.deleteReservation(pnr);
		});
		
		mainMenu.setOnAction(e -> {
			Main back = new Main();
			back.start(new Stage());
			window.close();
		});
		
		//Layout
		VBox menuLayout = new VBox(20);
		menuLayout.getChildren().addAll(button, mainMenu);
		menuLayout.setAlignment(Pos.CENTER);
		scene1 = new Scene(menuLayout, 1024, 683);
				
				
				
		//Set the scene
		window.setScene(scene1);
		window.setTitle("User's Reserved Buses");
		window.show();
			
	}

}
