package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MainMenu {
	
	Stage window;
	Scene scene1;
	
	
	public void start(Stage primaryStage) {
		
		
		window = primaryStage;
		Label label1 = new Label("Main Menu");
		Button button1 = new Button("Logout");
		
		//create new button to return to the login screen (logout)
		button1.setOnAction(e -> {
			LoginScreen logout = new LoginScreen();
			logout.start(new Stage());
			window.close();
		});
		
		
		window.setTitle("Main Menu");
		window.setMinWidth(250);
		
		VBox layout1 = new VBox(30);
		layout1.getChildren().addAll(label1, button1);
		scene1 = new Scene(layout1, 1024, 683);
		
		window.setTitle("ALDANA Bus Reservation System: Main Menu");
		window.setScene(scene1);
		window.show();
				
		
	}

}
