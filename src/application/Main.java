package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;


public class Main extends Application {
	Stage window;
	Scene scene1, scene2, scene3;
	
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		//created two objects, a label and a button
		window.setOnCloseRequest(e -> {
			e.consume();//tells the program to use the answer from the method closeProgram()
			closeProgram();
		});
		
	
		window.setScene(scene1);
		window.setTitle("Login Screen");
		window.show();
	}
	
	private void closeProgram() {
		boolean confirm = ConfirmBox.display("Close Program?", "Are you sure you want to close?");
		
		if (confirm) {
			System.out.println("File is saved!");
			window.close();
		} else {
			window.setScene(scene1);
		}
	}
	

}
