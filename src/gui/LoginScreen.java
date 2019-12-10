package gui;

import java.io.IOException;
import businessLogic.ValueObject;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import database.MySQLqueries;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;
import java.net.URL;

public class LoginScreen extends Application {
	
	private Stage window;
	private Scene scene1;
	static ValueObject user = new ValueObject();
	
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
		Label passwordLabel = new Label("Password:"); //creates a password label
		
		//create fields for username and password
		TextField usernameInput = new TextField(); //creates text field
		PasswordField passwordInput = new PasswordField(); //creates a hidden password text field
		usernameInput.setPromptText("username"); //creates hint for user to type username in text field
		passwordInput.setPromptText("password"); //creates a hint for users to enter their password in the field
		
		
		
		//create buttons
		Button loginButton = new Button("Login"); //creates login button
		Button forgotPasswordButton = new Button("Forgot Password"); //creates a forgot password button
		Button registerButton = new Button("New User?"); //creates a register button
		
		//assign location in grid
		GridPane.setConstraints(usernameLabel, 0, 0); //places the username label on the grid
		GridPane.setConstraints(usernameInput, 1, 0); //places the text field on the grid
		GridPane.setConstraints(passwordLabel, 0, 1); //places the password label on the grid
		GridPane.setConstraints(passwordInput, 1, 1); //aligns the password box on the grid
		GridPane.setConstraints(loginButton, 2, 0); //aligns it on the grid
		GridPane.setConstraints(forgotPasswordButton, 0, 10); //aligns the forgot password button on the grid
		GridPane.setConstraints(registerButton, 0, 12); //aligns the register button on the grid

		//create action for buttons
		loginButton.setOnAction(e -> {
			//pass value entered by user for username and password and assign it to a variable
			boolean login = MySQLqueries.checkLogin(usernameInput.getText(), passwordInput.getText());
			//if true, this window should close because MySQLqueries.checkLogin will open a new window
			if (login) {
				//retrieveInfo method is run and should return an ValueObject object. 
				//A constructor for ValueObject(ValueObject vo is in ValueObject class
				user = new ValueObject(MySQLqueries.retrieveInfo(usernameInput.getText()));
				System.out.println("First namme for login is " + user.getFirstName());
				//create new instance of MainMenu. Needs to be created after userinfo is retrieved for the right view to show
				Main mainPg = new Main();
				mainPg.start(new Stage());
				window.close();
				
			}
		});
		registerButton.setOnAction(e -> {
			RegistrationScreen register = new RegistrationScreen();
			register.start(new Stage());
			window.close();
		});
		forgotPasswordButton.setOnAction(e -> {
			ForgotPassword forgot = new ForgotPassword();
			forgot.start(new Stage());
			window.close();
		});
		

		pane.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, 
				loginButton, forgotPasswordButton, registerButton);
		
		//create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);
		window.setTitle("Welcome to the ALDANA Bus Reservation System");
		window.setScene(scene1);
		window.show();
		
	}
	
	private void closeProgram() {
		boolean confirm = ConfirmBox.display("Close Program?", "Are you sure you want to close?");
		
		if (confirm) {
			
			window.close();
		} 
	}

	
	
}
