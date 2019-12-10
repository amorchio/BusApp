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
import javafx.util.Duration;
import javafx.animation.FadeTransition;


public class SplashScreen extends Application{
	private Stage window;
	Duration dur = new Duration(2000);
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
	
	FadeTransition ft = new FadeTransition(Duration.seconds(3), root);
	ft.setByValue(1.0);
	ft.setToValue(0.0);
	ft.setDelay(dur);
	ft.play();
	
	Scene scene = new Scene(root);
	primaryStage.setTitle("ok");
    primaryStage.setScene(scene);
    primaryStage.show();
	
    ft.setOnFinished(e -> {
        LoginScreen login = new LoginScreen();
        login.start(new Stage());
        primaryStage.close();
    });
	}
	
}
