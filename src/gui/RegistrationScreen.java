package gui;

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

public class RegistrationScreen extends Application {
	
	private Stage window;
	private Scene scene1;

	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		window.setOnCloseRequest(e -> {
			e.consume(); // consume tells java that we will handle the close request from here by running closeProgram()
			closeProgram();
		});
		
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
		Label adminCode = new Label("Admin code:");

		//create text boxes for user entries
		TextField first_nameInput = new TextField();
		TextField last_nameInput = new TextField();
		PasswordField ssnInput = new PasswordField();
		TextField addressInput = new TextField();
		TextField cityInput = new TextField();
		TextField stateInput = new TextField();
		TextField zipInput = new TextField();
		TextField emailInput = new TextField();
		TextField usernameInput = new TextField();
		PasswordField passwordInput = new PasswordField();
		TextField secQInput = new TextField();
		TextField secQAInput = new TextField();
		TextField adminCodeInput = new TextField();
		
		//create helper message for ssn and zip
		ssnInput.setPromptText("No dashes, e.g. 999999999");
		zipInput.setPromptText("5 digit code");
		passwordInput.setPromptText("must be alpha-numeric");
		
		//create buttons
		Button registerButton = new Button("Register");
		Button returnButton = new Button("Return to Login Page");
		
		//create actions for button
		registerButton.setOnAction(e -> {
				if(MySQLqueries.newUser(last_nameInput.getText(), first_nameInput.getText(), 
					Integer.parseInt(ssnInput.getText()), addressInput.getText(), 
					cityInput.getText(), stateInput.getText(), Integer.parseInt(zipInput.getText()),
					emailInput.getText(), usernameInput.getText(), passwordInput.getText(),
					secQInput.getText(), secQAInput.getText(), adminCodeInput.getText())) {
				
				LoginScreen login = new LoginScreen();
				login.start(new Stage());
				window.close();
			}
			
		});
		
		returnButton.setOnAction(e -> {
			if (ConfirmBox.display("Warning", "Are you sure you want to leave this page?")) {
				LoginScreen back = new LoginScreen();
				back.start(new Stage());
				window.close();
			}
		});
		
		// create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11)); // sets an 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(8); // sets horizontal spacing
		pane.setVgap(8); // sets vertical spacing
		
		//align elements on screen
		GridPane.setConstraints(firstName, 0, 0);
		GridPane.setConstraints(first_nameInput, 1, 0);
		GridPane.setConstraints(lastName, 0, 1);
		GridPane.setConstraints(last_nameInput, 1, 1);
		GridPane.setConstraints(ssn, 0, 2);
		GridPane.setConstraints(ssnInput, 1, 2);
		GridPane.setConstraints(address, 0, 3);
		GridPane.setConstraints(addressInput, 1, 3);
		GridPane.setConstraints(city, 0, 4);
		GridPane.setConstraints(cityInput, 1, 4);
		GridPane.setConstraints(state, 0, 5);
		GridPane.setConstraints(stateInput, 1, 5);
		GridPane.setConstraints(zip, 0, 6);
		GridPane.setConstraints(zipInput, 1, 6);
		GridPane.setConstraints(email, 0, 7);
		GridPane.setConstraints(emailInput, 1, 7);
		GridPane.setConstraints(username, 0, 8);
		GridPane.setConstraints(usernameInput, 1, 8);
		GridPane.setConstraints(password, 0, 9);
		GridPane.setConstraints(passwordInput, 1, 9);
		GridPane.setConstraints(secQ, 0, 10);
		GridPane.setConstraints(secQInput, 1, 10);
		GridPane.setConstraints(secQAnswer, 0, 11);
		GridPane.setConstraints(secQAInput, 1, 11);
		GridPane.setConstraints(adminCode, 0, 12);
		GridPane.setConstraints(adminCodeInput, 1, 12);
		GridPane.setConstraints(registerButton, 0, 13);
		GridPane.setConstraints(returnButton, 0, 17); 
		
		//Place nodes in the pane
			pane.getChildren().addAll(firstName, first_nameInput, lastName, last_nameInput, ssn, ssnInput,
					address, addressInput, city, cityInput, state, stateInput, zip, zipInput, email, emailInput,
					username, usernameInput, password, passwordInput, secQ, secQInput,
					secQAnswer, secQAInput,	adminCode, adminCodeInput, registerButton, returnButton);

		
		// create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);
		window.setTitle("ALDANA Bus Reservation System: New User Registration");
		window.setScene(scene1);
		window.show();
		
	}

	private boolean isInt(TextField input, int low, int high) {
		
		try {
			
			//convert user input to int
			int number = Integer.parseInt(input.getText());
			
			//check if int is a positive number less than 9 digits (to account for leading 0's)
			if (number > low && number <= high) {

				return true;
			}
			
			return false;
			
		} catch (NumberFormatException ex) {
			AlertBox.display("Error", "Error: " + input + " is not a number");
			return false;
		}
		
	}
	
	public static boolean handle(String input, String fieldName) {
		
		if (fieldName == "emailInput") {
			return fieldName.matches("\\S*@\\S*\\.[aA-zZ0-9\\-]{2,}");
		} else {
		
		return false;
		}
		
	}
	
	private void closeProgram() {
		boolean confirm = ConfirmBox.display("Close Program?", "Are you sure you want to close?");
		
		if (confirm) {
			window.close();
		} 
	}

	
	
	
}
