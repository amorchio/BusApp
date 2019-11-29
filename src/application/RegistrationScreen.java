package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.geometry.*;

public class RegistrationScreen extends Application {
	
	Stage window;
	Scene scene1;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		//create Label variables
		Label firstName = new Label("First Name:");
		Label lastName = new Label("Last Name:");
		Label ssn = new Label("SSN:");
		Label address = new Label("Address:");
		Label city = new Label("City:");
		Label state = new Label("State (ex. GA):");
		Label zip = new Label("Zip Code:");
		Label email = new Label("Email:");
		Label username = new Label("Username:");
		Label password = new Label("Password:");
		Label secQ = new Label("Security Question:");
		Label secQAnswer = new Label("Answer:");

		
		
		//create a pane and set its properties
		FlowPane pane = new FlowPane();
		pane.setPadding(new Insets(11, 12, 13, 14));
		pane.setHgap(5);
		pane.setVgap(5);
		
		//Place nodes in the pane
		pane.getChildren().addAll();
		
		
		
	}
	
	
	

}
