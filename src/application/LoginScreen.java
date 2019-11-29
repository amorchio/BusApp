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
import javafx.scene.image.*;
import javafx.scene.layout.BackgroundImage;

public class LoginScreen extends Application {
	
	Scene scene1, scene2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		
		//create Label variables
		Label username = new Label("Username:");
		Label password = new Label("Password:");
		
		//create button variables
		Button login = new Button("Login");
		Button forgotPassword = new Button("Forgot Password");
		Button register = new Button("New User?");
		
		//create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(11)); // sets an 11px border
		pane.setHgap(5); //sets horizontal spacing
		pane.setVgap(5); //sets vertical spacing
		
		//Place nodes in the pane
		pane.add(username, 0, 1); //puts username in column 0, row 1
		pane.add(new TextField(), 1, 1); //puts a text field in column 1, row 1
		pane.add(password, 0, 2);
		pane.add(new TextField(), 1, 2);
		pane.add(login, 3, 1);
		pane.add(forgotPassword, 0, 10);
		pane.add(register, 0, 12);
		
		
		//create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);
		primaryStage.setTitle("Welcome to the ALDANA Bus Reservation System");
		primaryStage.setScene(scene1);
		primaryStage.show();
		
	}
	
	
	

}
