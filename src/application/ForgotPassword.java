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
	
	@Override
	public void start(Stage primaryStage) {
		
		window = primaryStage;
		
		window.setOnCloseRequest(e -> {
			e.consume(); // consume tells java that we will handle the close request from here by running closeProgram()
			closeProgram();
		});
		
		//create label
		Label usernameLabel = new Label("Username:");
		
		//create TextField
		TextField usernameInput = new TextField();
		
		//create buttons
		Button secQ = new Button("Get Security Question");
		
		//create grid pane to place label, textfield, and button objects
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11)); //set 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(8);//set horizontal gap
		pane.setVgap(8);//set vertical gap
		
		//assign location in grid
		GridPane.setConstraints(usernameLabel, 0, 1); // places the username label on the grid
		GridPane.setConstraints(usernameInput, 1, 1);
		GridPane.setConstraints(secQ, 1, 2);
		
		pane.getChildren().addAll(usernameLabel, usernameInput, secQ);
		Scene scene1 = new Scene(pane, 1024, 683);
		window.setTitle("ALDANA Bus Reservation System: Forgot Password");
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
