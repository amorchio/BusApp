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
import javafx.scene.layout.VBox;
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
		 welcome.setFont(Font.font(20));
		 GridPane.setConstraints(welcome, 0, 0);
	
		//Creating GridPane layout and adding padding to the GridPane
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(10));
		pane.setVgap(7);
		pane.setHgap(7);;
		
		//LAYOUT: labels
		Label userN = new Label("Username: ");
		GridPane.setConstraints(userN, 0, 1);
		Label fName = new Label("First Name: ");
		GridPane.setConstraints(fName, 0, 2);
		Label lName = new Label("Last Name: ");
		GridPane.setConstraints(lName, 0, 3);
		Label address = new Label("Address: ");
		GridPane.setConstraints(address, 0, 4);
		Label email = new Label("E-mail: ");
		GridPane.setConstraints(email, 0, 5);
		
		//Text that will be populated by user information grabbed from database
		Text userN2 = new Text(user.getUsername());
		GridPane.setConstraints(userN2, 1, 1);
		Text fName2 = new Text(user.getFirstName());
		GridPane.setConstraints(fName2, 1, 2);
		Text lName2 = new Text(user.getLastName());
		GridPane.setConstraints(lName2, 1, 3);
		Text address2 = new Text(user.getAddress() + 
				"\n" + user.getCity() + ", " + user.getState() + " " + user.getZip());
		GridPane.setConstraints(address2, 1, 4);
		Text email2 = new Text(user.getEmail());
		GridPane.setConstraints(email2, 1, 5);
		
		//Buttons
		Button mainButton = new Button("Return to Main Menu");
		Button mainButton2 = new Button("Edit Your Bus Reservations");
	
		HBox menuLayout = new HBox(20);
		menuLayout.getChildren().addAll(mainButton, mainButton2);
		menuLayout.setAlignment(Pos.CENTER);
		GridPane.setConstraints(menuLayout, 0, 8);

		//Assign button action
		mainButton.setOnAction(e -> {
			Main mainPg = new Main();
			mainPg.start(new Stage());
			window.hide();
		});
		mainButton2.setOnAction(e -> {
			UsersReservedBuses resBuses = new UsersReservedBuses();
			resBuses.start(new Stage());
			window.hide();
		});
		
		
		
		
		//Use getChildren to get labels and text into the Gridpane container
		pane.getChildren().addAll(welcome, userN, fName, lName, address, email, userN2, fName2, lName2, address2, email2, menuLayout);
		
		scene1 = new Scene(pane, 1024, 683);
		pane.setAlignment(Pos.CENTER);
			
		//Set the scene
		window.setScene(scene1);
		window.show();
		 
		 
		 
	}

}
