package gui;

import java.util.ArrayList;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.image.*;

//Scene that shows the buses that the user has already reserved
public class UsersReservedBuses extends Application {
	Stage window;
	Scene scene1;
	
	//create a pane and set its properties
	GridPane pane = new GridPane();
	
	//gets userdata
	static ValueObject user = LoginScreen.user;

	
	public static void main(String[] args) {
		//Generate the PNR
	//	String PNR = user.generatePNR();
		launch(args);

	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;

		ArrayList<ValueObject> userBookings = MySQLqueries.getUserPNR(user.getUsername());

		Label reservationLabel = new Label("Select a reservation:");

		Button mainMenuButton = new Button("Main Menu");
		mainMenuButton.setOnAction(e -> {
			Main back = new Main();
			back.start(new Stage());
			window.close();
		});

		ComboBox<String> pnrList = new ComboBox<>();
		pnrList.setPromptText(user.getFirstName() + "'s Reservations");
		pnrList.getItems().addAll(getBookingPNRs(userBookings));
		
		Button displayButton = new Button("Display Reservation");
		
		pane.setPadding(new Insets(11)); //set 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(5);//set horizontal gap
		pane.setVgap(5);//set vertical gap
		

		GridPane.setConstraints(reservationLabel, 5, 2);
		GridPane.setConstraints(pnrList, 6, 2);
		GridPane.setConstraints(mainMenuButton, 5, 12);
		GridPane.setConstraints(displayButton, 8, 2);

		pane.getChildren().addAll(reservationLabel, pnrList, displayButton, mainMenuButton);

		// create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);

		// Set the scene
		window.setScene(scene1);
		window.setTitle("User's Reserved Buses");
		
		displayButton.setOnAction(e -> {
			ValueObject selectedVO = new ValueObject();
			
			for (int i = 0; i < userBookings.size(); i++) {
				if (userBookings.get(i).getPnr().equals(pnrList.getValue())) {
					selectedVO = userBookings.get(i);
				}
			}
			
			// clear previous pane to display secQ and allow user to enter an answer
			pane.getChildren().clear();
			pnrList.getSelectionModel().clearSelection();
			
			// create labels to display reservation information
			Label pnrLabel = new Label("PNR:");
			Label pnrDisplay = new Label(selectedVO.getPnr());
			Label busLabel = new Label("Bus Number:");
			Label busDisplay = new Label(selectedVO.getBusID() + "");
			Label originLabel = new Label("Origin:");
			Label originDisplay = new Label(selectedVO.getOrigin());
			Label destinationLabel = new Label("Destination:");
			Label destinationDisplay = new Label(selectedVO.getDestination());
			Label dateLabel = new Label("Departure Date:");
			Label dateDisplay = new Label(selectedVO.getBusDate());

			// delete reservation button
			Button deleteButton = new Button("Cancel this Reservation");
			deleteButton.setOnAction(i -> {
				// calls delete reservation method which will delete reservation
				MySQLqueries.deleteReservation(pnrList.getValue());
			});
			
			GridPane.setConstraints(deleteButton, 6, 12);
			GridPane.setConstraints(pnrLabel, 5, 5);
			GridPane.setConstraints(pnrDisplay, 6, 5);
			GridPane.setConstraints(busLabel, 5, 6);
			GridPane.setConstraints(busDisplay, 6, 6);
			GridPane.setConstraints(originLabel, 5, 7);
			GridPane.setConstraints(originDisplay, 6, 7);
			GridPane.setConstraints(destinationLabel, 5, 8);
			GridPane.setConstraints(destinationDisplay, 6, 8);
			GridPane.setConstraints(dateLabel, 5, 9);
			GridPane.setConstraints(dateDisplay, 6, 9);

			pane.getChildren().addAll(reservationLabel, pnrList, displayButton, pnrLabel, pnrDisplay, busLabel, busDisplay,
					originLabel, originDisplay, destinationLabel, destinationDisplay, dateLabel, dateDisplay,
					deleteButton, mainMenuButton);
			


			// Set the scene
			window.setScene(scene1);

		});

		window.show();
			
	}
	
	//method to extract pnr from user bookings 
	public static ArrayList<String> getBookingPNRs(ArrayList<ValueObject> vo) {
		
		ArrayList<String> list = new ArrayList<>();
		
		for (int i = 0; i < vo.size(); i++) {
			list.add(vo.get(i).getPnr());
		}
		
		return list;
	}

}
