package gui;

import businessLogic.ValueObject;
import java.sql.SQLException;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import database.MySQLqueries;
import javafx.scene.control.Dialog;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

public class AdminControl extends Application {
	
	private Label sceneTitle = new Label("Current Buses");
	private Label txtAddBus = new Label("Add a Bus:");

	// Bottom Row
	private Label txtBusID = new Label("Bus ID");
	private Label txtOrigin = new Label("origin");
	private Label txtDestination = new Label("destination");
	private Label txtDepartDate = new Label("Departure Date");
	private Label txtDepartTime = new Label("Departure Time");
	private Label txtCurrentCapacity = new Label("Current Capacity");
	private Label txtMaxCapacity = new Label("Max Capacity");

	private Button btnAddBus = new Button("Add Bus");
	private Button btnDeleteBus = new Button("Delete Bus");
	private Button btnUpdateBus = new Button("Update Bus");
	private Button btnBack = new Button("Main Menu");

	private TextField busIDBOX = new TextField();
	private TextField originBOX = new TextField();
	private TextField destinationBOX = new TextField();
	private TextField departDateBOX = new TextField("0000-00-00");
	private TextField fromTime = new TextField("00:00:00");
	private TextField fldCurrentCapacity = new TextField();
	private TextField fldMaxCapacity = new TextField();

//checkDate method

	private static boolean checkBusId(String busId) {
		return busId.matches("., '^[0-9]{4}$");
	}

	private static boolean checkDateF(String departureDate) { // String arrivalDate) {
		return departureDate.matches("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");
	}

	private static boolean checkCities(String originCity, String destinationCity) {
		return originCity.matches("([A-Z]{1})([([a-z]+(?: [A-Za-z]+)*), ([A-Za-z]{2})") && destinationCity.matches("[A-Z]{3}");
	}

	private static boolean checkTime(String departTime) {// String arrivalTime){
		return departTime.matches("^([0-1][0-9]|[2][0-3]):([0-5][0-9]):([0-5][0-9])$");

	}

	// check origin and destination method

	@Override
	public void start(Stage primaryStage) {
		
		//create an observable list to display mysql search results
		ObservableList<ValueObject> searchResult = FXCollections.observableArrayList();

		//run query
		searchResult = MySQLqueries.getBusSearchResults();
		
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
					
		//create table
		TableView<ValueObject> resultTable = new TableView<>();
		resultTable.setItems(searchResult); //designate list to display
		resultTable.getColumns().addAll(busIDColumn, originColumn, destinationColumn, dateColumn, timeColumn, capacityColumn);

		HBox hBoxTitle = new HBox(15);
		hBoxTitle.getChildren().addAll(sceneTitle);
		hBoxTitle.setAlignment(Pos.CENTER);

		GridPane grid = new GridPane();

		ValueObject vo = new ValueObject();

		ArrayList<String> s1 = MySQLqueries.getOriginCities();



		grid.setAlignment(Pos.CENTER);
		grid.add(txtAddBus, 0, 3);
		grid.setHgap(15);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		GridPane gridBtm = new GridPane();
		gridBtm.setHgap(10);
		gridBtm.setVgap(10);
		gridBtm.setPadding(new Insets(25, 25, 25, 25));

		gridBtm.add(txtAddBus, 0, 0);
		gridBtm.add(txtBusID, 0, 1);
		gridBtm.add(busIDBOX, 1, 1, 3, 1);
		gridBtm.add(txtOrigin, 0, 2);
		gridBtm.add(originBOX, 1, 2);
		gridBtm.add(txtDestination, 2, 2);
		gridBtm.add(destinationBOX, 3, 2);
		gridBtm.add(txtDepartDate, 0, 3);
		gridBtm.add(departDateBOX, 1, 3);
		gridBtm.add(txtDepartTime, 2, 3);
		gridBtm.add(fromTime, 3, 3);
		gridBtm.add(txtMaxCapacity, 0, 4);
		gridBtm.add(fldMaxCapacity, 1, 4);
		gridBtm.add(btnAddBus, 1, 6, 3, 1);
		btnAddBus.setMinWidth(500);

		gridBtm.add(btnBack, 1, 8, 2, 1);
		btnBack.setMinWidth(150);
		gridBtm.add(btnUpdateBus, 2, 8);
		btnUpdateBus.setMinWidth(150);
		gridBtm.add(btnDeleteBus, 3, 8);
		btnDeleteBus.setMinWidth(150);

		gridBtm.setAlignment(Pos.CENTER);
		gridBtm.setPadding(new Insets(25, 25, 25, 25));
		gridBtm.setHgap(10);
		gridBtm.setVgap(10);

		VBox vbox = new VBox(20);
		Scene sceneText = new Scene(vbox, 1024, 683);
		vbox.getChildren().addAll(hBoxTitle, resultTable, grid, gridBtm);
		vbox.setAlignment(Pos.CENTER);
		primaryStage.setTitle("Add/Update/Delete Bus Rides"); // set title
		primaryStage.setScene(sceneText);
		primaryStage.show();

		// add bus action
		btnAddBus.setOnAction(e -> {

			// ValueObject vc = new ValueObject();
			if (checkBusId(busIDBOX.getText()) != true) {
				AlertBox.display("INVALID BUS NUMBER", "Enter four numbers (ex. 9999,0000)");

			} else if (checkCities(originBOX.getText(), destinationBOX.getText()) != true) {
				AlertBox.display("INVALID CITY", "Enter as City, ST code (ex. Atlanta, GA, etc)");

			} else if (checkDateF(departDateBOX.getText()) != true) {
				AlertBox.display("INVALID DATE", " Enter as YYYY-MM-DD (ex. 2018-12-09, 2018-07-27, etc)");

			} else if (checkTime(fromTime.getText()) != true) {
				AlertBox.display("INVALID TIME", " Enter as HH:MM:SS (ex. 12:56:00, 16:32:00, etc)");

			} else {

				int bNum = Integer.parseInt(busIDBOX.getText());
				int cCap = Integer.parseInt(fldCurrentCapacity.getText());
				int mCap = Integer.parseInt(fldMaxCapacity.getText());
				vo.addBus(bNum, originBOX.getText(), destinationBOX.getText(), departDateBOX.getText(),
						fromTime.getText(), cCap, mCap);
				AlertBox.display("Bus Ride", "Successfully Added");
				AdminControl add = new AdminControl();
				add.start(primaryStage);
			}
		});

		btnUpdateBus.setOnAction(e -> {
			try {
				AdminControl update = new AdminControl();
				update.start(primaryStage);
			} catch (Exception n) {
				n.printStackTrace();
				;
			}

		});		
		
		//set action for book button
		btnDeleteBus.setOnAction(i -> {
			
			int index = resultTable.getSelectionModel().selectedIndexProperty().get();
			
			ObservableList originalResult = MySQLqueries.getBusSearchResults();
			//pass selection result to the book bus method
			deleteThisBus(originalResult, index);
			
			primaryStage.close();

		});
	

/*	btnDeleteBus.setOnAction(e->{try{

	//int busRide = Integer.parseInt(busIDBOX.getText());vo.deleteBusRide(busRide);
	AdminControl delete = new AdminControl();delete.start(primaryStage);}catch(
	Exception n){n.printStackTrace();;}

	});
*/
		btnBack.setOnAction(e -> {
			Main back = new Main();
			back.start(new Stage());
			primaryStage.close();

		});
	
	}

	public static void main(String[]args){
        Application.launch(args);
    }
	
	public static void deleteThisBus(ObservableList<ValueObject> vo, int index) {
		
		//retrieve bus value from the observable list and add values to new value object
		ValueObject selBus = new ValueObject();
		selBus.setBusID(vo.get(index).getBusID());
		
		//send query to database
		MySQLqueries.deleteBus(selBus.getBusID());
		
		UsersReservedBuses booking = new UsersReservedBuses();
		booking.start(new Stage());
		
	}

}