package gui;

//importing ValueObject
import businessLogic.ValueObject;
import database.MySQLqueries;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.image.*;

public class ProfilePage extends Application {
	Stage window;
	Scene scene1; 
	static ValueObject user = new ValueObject(MySQLqueries.getUser());
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		 window = primaryStage;
		 
		 String name = user.getFirstName();
		 
		//Contents + user.getFirstName(working on it))
		 Label welcome = new Label("Welcome " + name);
		
		//Layout
		HBox menuLayout = new HBox(20);
		menuLayout.getChildren().addAll(welcome);			
		menuLayout.setAlignment(Pos.CENTER);
		scene1 = new Scene(menuLayout, 1024, 683);
			
		//Set the scene
		window.setScene(scene1);
		window.setTitle("Profile Page");
		window.show();
		 
		 
		 
	}

}
