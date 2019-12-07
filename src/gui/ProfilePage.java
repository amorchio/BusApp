package gui;

//importing ValueObject
import businessLogic.ValueObject;
import database.MySQLqueries;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.*;
import javafx.scene.text.*;

public class ProfilePage extends Application {
	Stage window;
	Scene scene1; 
	//getUser will retrieve the pulled userdata of whoever is logged in.
	static ValueObject user = new ValueObject(MySQLqueries.getUser());
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		 window = primaryStage;
		 window.setTitle("Profile Page");
		
		//Welcomes you using the name of whoever is logged in
		 Text welcome = new Text("Welcome " + user.getFirstName());

		//Creating GridPane layout and adding padding to the GridPane
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10));
		pane.setVgap(5);
		pane.setHgap(5);;
		
		//LAYOUT: labels
		Label userN = new Label("Username: ");
		GridPane.setConstraints(userN, 0, 0);
		Label fName = new Label("First Name: ");
		GridPane.setConstraints(fName, 0, 1);
		Label lName = new Label("Last Name: ");
		GridPane.setConstraints(lName, 0, 2);
		Label address = new Label("Address: ");
		GridPane.setConstraints(address, 0, 3);
		Label email = new Label("E-mail: ");
		GridPane.setConstraints(email, 0, 4);
		
		//Text that will be populated by user information grabbed from database
		Text userN2 = new Text(user.getUsername());
		GridPane.setConstraints(userN2, 1, 0);
		Text fName2 = new Text(user.getFirstName());
		GridPane.setConstraints(fName2, 1, 1);
		Text lName2 = new Text(user.getLastName());
		GridPane.setConstraints(lName2, 1, 2);
		Text address2 = new Text(user.getAddress() + 
				"\n" + user.getCity() + ", " + user.getState() + " " + user.getZip());
		GridPane.setConstraints(address2, 1, 3);
		Text email2 = new Text(user.getEmail());
		GridPane.setConstraints(email2, 1, 4);
		
		//Use getChildren to get labels and text into the Gridpane container
		pane.getChildren().addAll(userN, fName, lName, address, email, userN2, fName2, lName2, address2, email2);
		
		scene1 = new Scene(pane, 1024, 683);
		//pane.setAlignment(Pos.CENTER);
		
		//scene1 = new Scene(menuLayout, 1024, 683);
			
		//Set the scene
		window.setScene(scene1);
		window.show();
		 
		 
		 
	}

}
