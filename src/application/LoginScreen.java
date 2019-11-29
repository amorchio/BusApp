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
	
	Stage window;
	Scene scene1, scene2;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		window.setOnCloseRequest(e -> {
			e.consume(); // consume tells java that we will handle the close request from here by running closeProgram()
			closeProgram();
		});
		
		
		//create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11)); // sets an 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(5); //sets horizontal spacing
		pane.setVgap(5); //sets vertical spacing
		
		//create username label
		Label usernameLabel = new Label("Username:"); //creates a username label
		pane.setConstraints(usernameLabel, 0, 0); //places the username label on the grid
		
		//create username input
		TextField usernameInput = new TextField(); //creates text field
		usernameInput.setPromptText("username"); //creates hint for user to type username in text field
		GridPane.setConstraints(usernameInput, 1, 0); //places the text field on the grid
		
		//create password label
		Label passwordLabel = new Label("Password:"); //creates a password label
		GridPane.setConstraints(passwordLabel, 0, 1); //places the password label on the grid
		
		//create password input
		PasswordField passwordInput = new PasswordField(); //creates a hidden password text field
		passwordInput.setPromptText("password"); //creates a hint for users to enter their password in the field
		GridPane.setConstraints(passwordInput, 1, 1); //aligns the password box on the grid
		
		
		// create login
		Button loginButton = new Button("Login"); //creates login button
		GridPane.setConstraints(loginButton, 2, 0); //aligns it on the grid
		
		//create forgot password button
		Button forgotPasswordButton = new Button("Forgot Password"); //creates a forgot password button
		GridPane.setConstraints(forgotPasswordButton, 0, 10); //aligns the forgot password button on the grid
		
		//create register button
		Button registerButton = new Button("New User?"); //creates a register button
		GridPane.setConstraints(registerButton, 0, 12); //aligns the register button on the grid
		
		pane.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, 
				forgotPasswordButton, loginButton, registerButton);
		
		//create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);
		window.setTitle("Welcome to the ALDANA Bus Reservation System");
		window.setScene(scene1);
		window.show();
		
		
	}
	
	private void closeProgram() {
		boolean confirm = ConfirmBox.display("Close Program?", "Are you sure you want to close?");
		
		if (confirm) {
			System.out.println("File is saved!");
			window.close();
		} 
	}

	
}
