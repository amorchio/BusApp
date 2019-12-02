package application;

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

public class ForgotPassword extends Application {

	private Stage window;
	
	@SuppressWarnings("unlikely-arg-type")
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		
		
		window.setOnCloseRequest(e -> {
			e.consume(); // consume tells java that we will handle the close request from here by running closeProgram()
			closeProgram();
		});
		
		//create TextField
		TextField usernameInput = new TextField();
		TextField secQAnswerInput = new TextField();
		
		
		//create labels
		Label usernameLabel = new Label("Username:");
		Label secQLabel = new Label("Security Question");
		Label secQAnswerLabel = new Label("Answer:");
		
		//create buttons
		Button secQButton = new Button("Get Security Question");
		Button loginScreenButton = new Button("Return to Login Page");
		Button submitSecQAnswer = new Button("Submit");
		
		//create grid pane to place label, textfield, and button objects
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11)); //set 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(5);//set horizontal gap
		pane.setVgap(5);//set vertical gap
		
		//assign location in grid
		GridPane.setConstraints(usernameLabel, 0, 1); // places the username label on the grid
		GridPane.setConstraints(usernameInput, 1, 1);
		GridPane.setConstraints(secQButton, 2, 1);
		GridPane.setConstraints(loginScreenButton, 0, 12);
		GridPane.setConstraints(secQLabel, 0, 1);
		GridPane.setConstraints(secQAnswerLabel, 0, 4);
		GridPane.setConstraints(secQAnswerInput, 1, 4);
		
		pane.getChildren().addAll(usernameLabel, usernameInput, secQButton, loginScreenButton);
		Scene scene = new Scene(pane, 1024, 683);
		window.setTitle("ALDANA Bus Reservation System: Forgot Password");
		window.setScene(scene);
		
		
		
		//assign action to buttons
		loginScreenButton.setOnAction(e -> {
			LoginScreen loginScreen = new LoginScreen();
			loginScreen.start(new Stage());
			window.close();
		});
		
		submitSecQAnswer.setOnAction(e -> MySQLqueries.getSecQAnswer(usernameInput.getText(),
				secQAnswerInput.getText()));
		
		//display the secQ after entering username
		secQButton.setOnAction(e -> {
			
			//clear previous pane to display secQ and allow user to enter an answer
			pane.getChildren().clear();
			
			//create a label with the security question
			Label secQ = new Label(MySQLqueries.getSecQ(usernameInput.getText()));
			
			//place result of mySQL on pane
			GridPane.setConstraints(secQ, 0, 3);
			
			//if username not found, only display message "username not found"
			if (secQ.equals("Username not found")) {
				pane.getChildren().addAll(secQLabel, secQ, loginScreenButton);
				window.setScene(scene);
			} else {
				
				//place button on the GridPane
				GridPane.setConstraints(submitSecQAnswer, 2, 4);
				
				//display secQAnswer
				pane.getChildren().addAll(secQLabel, secQ, secQAnswerLabel, secQAnswerInput,
						submitSecQAnswer,loginScreenButton);
				window.setScene(scene);
			}
			
		});		
		
		window.show();
		
	}
	
	private void closeProgram() {
		boolean confirm = ConfirmBox.display("Close Program?", "Are you sure you want to close?");
		
		if (confirm) {
			window.close();
		} 
	}

	
	
}
