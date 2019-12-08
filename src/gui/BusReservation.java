package gui;

import java.util.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;

import java.time.format.DateTimeFormatter;
import database.MySQLqueries;
import businessLogic.ValueObject;

public class BusReservation extends Application{
	Stage window, window2;
	Scene scene1, scene2;
	
	public static void main(String[] args) {		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {
		window = primaryStage;
		
		Label dateLabel = new Label("Departure Date: ");
		Label originLabel = new Label("Departure City: ");
		Label destinationLabel = new Label("Destination City: ");
		
		
		Button searchButton = new Button("Search");
		DatePicker departDate = new DatePicker();
		departDate.setEditable(false);
		
		ComboBox<String> origin = new ComboBox<>();
		origin.setPromptText("Origin City");
		origin.getItems().addAll(MySQLqueries.getOriginCities());
		
		ComboBox<String> destination = new ComboBox<>();
		destination.setPromptText("Destination City");
		
		origin.setOnAction(e -> {
			destination.getItems().clear();
			destination.getItems().addAll((MySQLqueries.getDestinationCities(origin.getValue())));
		});
		
		searchButton.setOnAction(e -> {
			
			//create an observable list to display mysql search results
			ObservableList<ValueObject> searchResult = FXCollections.observableArrayList();
			
			//convert date from DatePicker to text to run mysql query
			String date = departDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			
			//run query
			searchResult = MySQLqueries.getBusSearchResults(origin.getValue(), destination.getValue(), date);
			
			//labels to summarize search criteria
			Label dateLabel2 = new Label("Departure Date: ");
			Label dateSel = new Label(date);
			Label originLabel2 = new Label("Departure City: ");
			Label destinationLabel2 = new Label("Destination City: ");
			Label originSel = new Label(origin.getValue());
			Label destinationSel = new Label(destination.getValue());
			Button backButton = new Button("New Search");
			Button bookButton = new Button("Book Bus");
			
			//create a back button to search again
			backButton.setOnAction(i -> {
				BusReservation back = new BusReservation();
				back.start(new Stage());
				window.close();
			});
			
			//create TableView to display search results
			//create columns
			TableColumn<ValueObject, Integer> busIDColumn = new TableColumn<>("Bus Number"); //creates new column
			busIDColumn.setMinWidth(100); //sets column width
			busIDColumn.setCellValueFactory(new PropertyValueFactory<>("busID")); //assigns value to display inside table
			
			TableColumn<ValueObject, String> originColumn = new TableColumn<>("Origin City"); //creates new column
			originColumn.setMinWidth(200); //sets column width
			originColumn.setCellValueFactory(new PropertyValueFactory<>("origin")); //assigns value to display inside table
			
			TableColumn<ValueObject, String> destinationColumn = new TableColumn<>("Destination City"); //creates new column
			destinationColumn.setMinWidth(200); //sets column width
			destinationColumn.setCellValueFactory(new PropertyValueFactory<>("destination")); //assigns value to display inside table
			
			TableColumn<ValueObject, String> dateColumn = new TableColumn<>("Departure Date"); //creates new column
			dateColumn.setMinWidth(200); //sets column width
			dateColumn.setCellValueFactory(new PropertyValueFactory<>("busDate")); //assigns value to display inside table
			
			TableColumn<ValueObject, String> timeColumn = new TableColumn<>("Departure Time"); //creates new column
			timeColumn.setMinWidth(200); //sets column width
			timeColumn.setCellValueFactory(new PropertyValueFactory<>("departTime")); //assigns value to display inside table
			
			TableColumn<ValueObject, Integer> capacityColumn = new TableColumn<>("Remaining Seats"); //creates new column
			capacityColumn.setMinWidth(100); //sets column width
			capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity")); //assigns value to display inside table
			
			TableColumn<ValueObject, Button> bookColumn = new TableColumn<>("Select Bus"); //creates new column
			capacityColumn.setMinWidth(100); //sets column width
			
						
			//create table
			TableView<ValueObject> resultTable = new TableView<>();
			resultTable.setItems(searchResult); //designate list to display
			resultTable.getColumns().addAll(busIDColumn, originColumn, destinationColumn, dateColumn, timeColumn, capacityColumn, bookColumn);
			
			//set action for book button
			bookButton.setOnAction(i -> {
				
				int index = resultTable.getSelectionModel().selectedIndexProperty().get();
				
				ObservableList originalResult = MySQLqueries.getBusSearchResults(origin.getValue(), destination.getValue(), date);
				//pass selection result to the book bus method
				bookThisBus(originalResult, index);
				
				window.close();

			});
			
			//create a scene and place it in the stage/window
			HBox topMenu = new HBox(20);
			topMenu.getChildren().addAll(dateLabel2, dateSel, originLabel2, originSel, destinationLabel2,
					destinationSel, bookButton);
			VBox mainMenu = new VBox(20);
			mainMenu.getChildren().addAll(backButton, resultTable);
			
			//arrange the menus created using HBox and VBox
			BorderPane borderPane = new BorderPane();
			borderPane.setTop(topMenu);
			borderPane.setCenter(mainMenu);
			scene2 = new Scene(borderPane, 1024, 683);
			
			//Set the scene
			window.setTitle("ALDANA Bus Reservation System: Search Results");
			window.setScene(scene2);		
			window.show();
			
		});
		
		// create a pane and set its properties
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(11)); // sets an 11px border
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setHgap(10); // sets horizontal spacing
		pane.setVgap(10); //sets vertical spacing		
		
		//set on grid
		GridPane.setConstraints(dateLabel, 0, 2);
		GridPane.setConstraints(departDate, 1, 2);
		GridPane.setConstraints(originLabel, 0, 3);
		GridPane.setConstraints(origin, 1, 3);
		GridPane.setConstraints(destinationLabel, 0, 4);
		GridPane.setConstraints(destination, 1, 4);
		GridPane.setConstraints(searchButton, 0, 5);
		
		
		pane.getChildren().addAll(dateLabel, departDate, originLabel, origin, destinationLabel, destination, searchButton);
		
		//create a scene and place it in the stage/window
		scene1 = new Scene(pane, 1024, 683);
		
		//Set the scene
		window.setTitle("ALDANA Bus Reservation System: Begin Your Search");
		window.setScene(scene1);		
		window.show();
		
	}
	
	public static void bookThisBus(ObservableList<ValueObject> vo, int index) {
		
		ValueObject confirmedBus = new ValueObject(vo.get(index).getBusID(), "amorchio1");
		System.out.println(confirmedBus.getPnr());
		
		UsersReservedBuses booking = new UsersReservedBuses();
		booking.start(new Stage());
		
	}

}
