package gui;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.print.DocFlavor.URL;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
import javafx.scene.Parent;
import javafx.fxml.*;


public class SplashScreen extends Application{
	private Stage window;
		
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage primaryStage) {

	Parent root = null;
	try {
		root = FXMLLoader.load(getClass().getResource("BusSplash.fxml"));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	Scene scene = new Scene(root);
	primaryStage.setTitle("ok");
    primaryStage.setScene(scene);
    primaryStage.show();
	
	}
	
}
