package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import gui.*;
import businessLogic.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import businessLogic.ValueObject;

public class MySQLqueries {

	//static ValueObject user = new ValueObject();
	static ValueObject bus = new ValueObject();
	static ValueObject reservation = new ValueObject();
	static ValueObject user = new ValueObject();
  
	//set a calendar because results from the jdbc were 1 day off
	static Calendar est = Calendar.getInstance(TimeZone.getTimeZone("EST")); 
  
	public static Connection initializeDB() {

		try {

			// 1. get a connection to the database
			Connection connection = DriverManager.getConnection("jdbc:mysql://35.196.76.98:3306/busbookingapp",
					"java-app",	"cis3270!");

			return connection;

		} catch (Exception ex) {

			System.out.println(ex);
		}

		return null;

	}

	public static boolean checkLogin(String username, String password) {

		//rewrite mysql query to use PreparedStatement

		Connection connection = initializeDB();

		try {

			//create search string to look up username
			String queryString = "SELECT username, password " +
					"FROM busbookingapp.user WHERE username = ?";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);

			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();

			//process the if statement if the mysql query returns a result
			if (rset.next()) {
				//check if password matches
				if (rset.getString("password").equals(password)) {

					return true;

				} else {
					//display error if password does not match username
					AlertBox.display("Login Error", "Username and/or password is incorrect. Please try again");
          
					return false;
				}

			} else {
				//display error if username is not found
				AlertBox.display("Login Error", "Username not found. Please register as a new user.");

				return false;
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}

		return false;

	}

	public static boolean newUser(String lastName, String firstName, int ssn, String address,
                                
			String city, String state, int zip, String email, String username, String password,
			String secQ, String secQAnswer, String adminCode) {
		
		ValueObject user = new ValueObject();

		try {

			Connection connection = initializeDB();

			//mysql insert statement
			String queryString = "INSERT INTO " +
					"busbookingapp.user (username, ssn, firstname, lastname, " +
					"address, city, state, zip, password, email, secQ, secQAnswer, isAdmin) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, ssn);
			preparedStatement.setString(3, firstName);
			preparedStatement.setString(4, lastName);
			preparedStatement.setString(5, address);
			preparedStatement.setString(6, city);
			preparedStatement.setString(7, state);
			preparedStatement.setInt(8, zip);
			preparedStatement.setString(9, password);
			preparedStatement.setString(10, email);
			preparedStatement.setString(11, secQ);
			preparedStatement.setString(12, secQAnswer);
      
			//check if user keyed correct admin code
			if (adminCode.equals("gatekeeper")) {
				preparedStatement.setBoolean(13, true);
			} else {
				preparedStatement.setBoolean(13, false);
			}
			
			//execute preparedStatement
			preparedStatement.execute();
			
			return true;

		} catch (Exception ex) {

			//display error alert box
			AlertBox.display("Exception", ex.toString());
			ex.printStackTrace();
		}
		return false;

	}

	public static String getSecQ(String username) {

		try {

			Connection connection = initializeDB();

			//create search string to look up username
			String queryString = "SELECT username, secQ " +
					"FROM busbookingapp.user WHERE username = ?";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);


			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();



			// process the if statement if the mysql query returns a result
			if (rset.next()) {
				// check if password matches
				return rset.getString("secQ");

			} else {

				return "Username not found";
			}

		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

		return "Username not found";
	}

	public static void getSecQAnswer(String username, String secQAnswer) {

		try {

			Connection connection = initializeDB();

			//create search string to look up username
			String queryString = "SELECT username, secQAnswer, password " +
					"FROM busbookingapp.user WHERE username = ?";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);


			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();



			// process the if statement if the mysql query returns a result
			if (rset.next()) {

				if (rset.getString("secQAnswer").equals(secQAnswer)) {

					// display password in pop up
					AlertBox.display("Display Password", "Your password is " + rset.getString("password"));

				} else {

					// display alert box
					AlertBox.display("Error", "Wrong answer! The answer is case-sensitive. Please try again.");
				}

			}

		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

	}

	public static ArrayList<String> getOriginCities() {

		//create string array to hold answer
		ArrayList<String> originCities = new ArrayList<>();

		//initialize connection to database
		Connection connection = initializeDB();

		try {

			//create string of search query
			String queryString = "SELECT DISTINCT origin " +
					"FROM busbookingapp.bus ORDER BY origin";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);

			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				originCities.add(rset.getString("origin"));
			}


		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

		return originCities;
	}

	public static ArrayList<String> getDestinationCities(String origin) {

		//create string array to hold answer
		ArrayList<String> originCities = new ArrayList<>();

		//initialize connection to database
		Connection connection = initializeDB();

		try {

			//create string of search query so that only cities with the selected origin are displayed
			String queryString = "SELECT DISTINCT destination " +
					"FROM busbookingapp.bus WHERE origin = ? ORDER BY destination";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, origin);

			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				originCities.add(rset.getString("destination"));
			}


		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}

		return originCities;
	}

//with approved login, retrieve user's information from database and return the VO object

	public static ValueObject retrieveInfo(String username) {
		try {

			Connection connection = initializeDB();


			//mysql statement. Just testing with first name and last name to get it working first
			String queryString = "SELECT * FROM user WHERE username = ?";

			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);

			//execute preparedStatement
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				//assign values from database to ValueObject user
				user.setFirstName(rset.getString("firstname"));
				user.setLastName(rset.getString("lastname"));
				user.setUsername(rset.getString("username"));
				user.setSsn(rset.getInt("ssn"));
				user.setAddress(rset.getString("address"));
				user.setCity(rset.getString("city"));
				user.setState(rset.getString("state"));
				user.setZip(rset.getInt("zip"));
				user.setEmail(rset.getString("email"));
				user.setAdmin(rset.getInt("isAdmin"));
				
			}
			return user;			

		} catch (Exception ex) {

			//display error alert box
			AlertBox.display("Exception", ex.toString());
			ex.printStackTrace();
		}

		return user;

	}

	//method to retrieve userObject
	public static ValueObject getUser(){
		return user;
	}
	
	public static ObservableList<ValueObject> getBusSearchResults(String origin, String destination, String date) {
		
		//create string array to hold answer
		ObservableList<ValueObject> busResults = FXCollections.observableArrayList(); 
		
		//initialize connection to database
		Connection connection = initializeDB();
		
		try {
			
			//create string of search query so that only cities with the selected origin are displayed
			String queryString = "SELECT * FROM busbookingapp.bus WHERE origin = ? AND destination = ? and date = ? ORDER BY time";
			
			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, origin);
			preparedStatement.setString(2, destination);
			preparedStatement.setString(3, date);
				
			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();
			
			while (rset.next()) {
				
				//limit search results to buses with capacity greater than 0
				if (rset.getInt("capacity") >= 1) {
					//create a new object to add to the arraylist
					ValueObject bus = new ValueObject();

					//set the values for each new object
					bus.setBusID(rset.getInt("busID"));
					bus.setCapacity(rset.getInt("capacity"));
					bus.setOrigin(rset.getString("origin"));
					bus.setDestination(rset.getString("destination"));
					bus.setDepartTime(rset.getTime("time").toString());
					bus.setBusDate((rset.getDate("date", est)).toString());
					
					//add object to the arraylist
					busResults.add(bus);
					
				}
				
			}
			
			
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return busResults;
	}
	
	public static ObservableList<ValueObject> getBusSearchResults() {
		
		//create string array to hold answer
		ObservableList<ValueObject> busResults = FXCollections.observableArrayList(); 
		
		//initialize connection to database
		Connection connection = initializeDB();
		
		try {
			
			//create string of search query so that only cities with the selected origin are displayed
			String queryString = "SELECT * FROM bus ORDER BY busID";
			
			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				
			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();
			
			while (rset.next()) {
				
				//limit search results to buses with capacity greater than 0
				if (rset.getInt("capacity") >= 1) {
					//create a new object to add to the arraylist
					ValueObject bus = new ValueObject();

					//set the values for each new object
					bus.setBusID(rset.getInt("busID"));
					bus.setCapacity(rset.getInt("capacity"));
					bus.setOrigin(rset.getString("origin"));
					bus.setDestination(rset.getString("destination"));
					bus.setDepartTime(rset.getTime("time").toString());
					bus.setBusDate((rset.getDate("date", est)).toString());
					
					//add object to the arraylist
					busResults.add(bus);
					
				}
				
			}
			
			
		} catch (Exception ex) {
			System.out.println(ex);
			ex.printStackTrace();
		}
		return busResults;
	}
	
	//Reserving a bus method that will update the database. Initial check on whether or not 
	//the user has already booked the same bus should happen in the businessLogic

	public static void reserveBus(String username, int busID, int capacity) {
		//Generate PNR
		String userPNR = bus.generatePNR();
		int helper = 0;

		try {
			
			Connection connection = initializeDB();
			
			String queryString = "SELECT pnr, username, reservation.busID, " + 
					"origin, destination, date, time " + 
					"FROM (reservation INNER JOIN bus " + 
					"ON reservation.busID = bus.busID) " +
					"WHERE username = ?";
			
			//create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);
			
			//save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();
			
			while (rset.next()) {
				if (busID == rset.getInt("busID")) {
					AlertBox.display("Error: Bus is Already Booked",
							"This bus is already booked! Reservation " + rset.getString("pnr") +
							" already contains bus " + rset.getString("busID") + " from " + rset.getString("origin") +
							" to " + rset.getString("destination") + " on " + rset.getString("date"));
					helper = 1;
					System.out.println("helper inside the while loop is " + helper);
					break;
					
				} 
			}
			System.out.println("Helper before the if statement is" + helper);
			
			if (helper != 1) {
				System.out.println("Helper made it in the if statement");
				//mysql statement. Just testing with first name and last name to get it working first
				queryString = "INSERT INTO reservation (pnr, username, busID) " +
										"VALUES (?, ?, ?)";
				
				//create the mysql insert preparedStatement for the reservation table
				preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setString(1, userPNR);
				preparedStatement.setString(2, username);
				preparedStatement.setInt(3, busID);
				
				//execute preparedStatement
				preparedStatement.executeUpdate();			
				
				queryString = "UPDATE bus SET capacity = ? WHERE (busID = ?)";
				
				preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setInt(1, capacity);
				preparedStatement.setInt(2, busID);
				
				preparedStatement.executeUpdate();
				
				preparedStatement = connection.prepareStatement(queryString);
				queryString = "INSERT INTO busRiders (busID, pnr) VALUES (?,?)";
				preparedStatement.setInt(1, busID);
				preparedStatement.setString(2, userPNR);
				
				preparedStatement.executeUpdate();

				
				//create the mysql insert preparedStatement for the busRiders table
				PreparedStatement preparedStatement2 = connection.prepareStatement(queryString);
				preparedStatement2.setInt(1, busID);
				preparedStatement2.setString(2, userPNR);
				//execute prepared statement
				preparedStatement2.executeUpdate();
				
				AlertBox.display("Booking Confirmed", "Your reservation number is " + userPNR);
				
			}
			
		} catch (Exception ex) {
			//display error alert box
			AlertBox.display("Exception", ex.toString());
			ex.printStackTrace();
		}
	}
	
	//deleteing a bus method that will update the database
	public static void deleteReservation(String pnr) {
			//CREATE BUS PNR VARIABLE
			int busID = 1;
			int busCap = 1;
			try {
				Connection connection = initializeDB();
				
				//mysql statement. Just testing with first name and last name to get it working first
				String queryString = "SELECT * FROM busRiders WHERE pnr = ? ";
				
				//INSERT STATEMENT TO PULL BUSID VALUE WITH CORRESPONDING PNR
				PreparedStatement preparedStatement = connection.prepareStatement(queryString);
				preparedStatement.setString(1, pnr);
				//executed preparedStatement and save results to rset.
				ResultSet rset = preparedStatement.executeQuery();
				//***save the busID info of the reservation into busID
				if (rset.next()) {
					busID = rset.getInt("busID");
					System.out.println("Got the busID");
				}
				
				//DELETE RESERVATION INFO FROM BUSRIDERS USING PNR (must be deleted from first)
				//String deleteString = "DELETE b, r FROM busRiders b INNER JOIN reservation r ON b.pnr = r.pnr WHERE b.pnr = ?";
				String deleteString = "DELETE FROM busRiders WHERE pnr = ?";
				PreparedStatement preparedStatement2 = connection.prepareStatement(deleteString);
				preparedStatement2.setString(1,  pnr);
				preparedStatement2.executeUpdate();
				System.out.println("busRider deletion executed");
				
				//DELETE RESERVATION INFO FROM RESERVATION TABLE (must be deleted 2nd)
				String deleteString2 = "DELETE FROM reservation WHERE pnr = ?";
				PreparedStatement preparedStatement3 = connection.prepareStatement(deleteString2);
				preparedStatement3.setString(1,  pnr);
				preparedStatement3.executeUpdate();
				System.out.println("reservation deletion executed");
				
				//PULL CURRENT BUS CAPACITY FROM BUS TABLE
				String capacityString = "SELECT * FROM bus WHERE busID = ?";
				PreparedStatement preparedStatement4 = connection.prepareStatement(capacityString);
				preparedStatement4.setInt(1, busID);
				ResultSet rset2 = preparedStatement4.executeQuery();
				if (rset2.next()) {
					busCap = rset2.getInt("capacity");
					//Increase bus capacity by 1 since we are deleting the reservation
					busCap = busCap + 1;
				}
				
				
				//UPDATE BUS TABLE WITH NO CAPACITY
				String capacityString2 = "UPDATE bus SET capacity = ? WHERE busID = ?";
				PreparedStatement preparedStatement5 = connection.prepareStatement(capacityString2);
				preparedStatement5.setInt(1, busCap);
				preparedStatement5.setInt(2, busID);
				preparedStatement5.executeUpdate();
				
				AlertBox.display("Booking Canceled", "Confirmation number " + pnr + " has been canceled");
				System.out.println("reservation information updated");
			
				}
				

			 catch (Exception ex) {
				
				//display error alert box
				AlertBox.display("Exception", ex.toString());
				ex.printStackTrace();
			}
		}	

	public static ArrayList<ValueObject> getUserPNR(String username) {

		ArrayList<ValueObject> pnrList = new ArrayList<>();

		try {
			Connection connection = initializeDB();

			String queryString = "SELECT pnr, username, reservation.busID, capacity, origin, destination, date, time "
					+ "FROM (reservation INNER JOIN bus " + "ON reservation.busID = bus.busID) " + "WHERE username = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);

			// save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();

			while (rset.next()) {
				ValueObject reservation = new ValueObject();
				reservation.setPNR(rset.getString("pnr"));
				reservation.setUsername(rset.getString("username"));
				reservation.setBusID(rset.getInt("busID"));
				reservation.setCapacity(rset.getInt("capacity"));
				reservation.setOrigin(rset.getString("origin"));
				reservation.setDestination(rset.getString("destination"));
				reservation.setBusDate(rset.getString("date"));
				reservation.setDepartTime(rset.getString("time"));

				pnrList.add(reservation);
			}

			return pnrList;

			// while (rset.next()) {
		} catch (Exception ex) {

			ex.printStackTrace();

		}

		return pnrList;

	}

	public static void deleteBus(int busID) {
		
		try {
			Connection connection = initializeDB();
			
			//mysql statement. Just testing with first name and last name to get it working first
			String queryString = "DELETE FROM bus WHERE busID = ? ";
			
			//INSERT STATEMENT TO PULL BUSID VALUE WITH CORRESPONDING PNR
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setInt(1, busID);
			//executed preparedStatement and save results to rset.
			preparedStatement.executeUpdate();
			
			AlertBox.display("Bus Deleted", "Bus " + busID + " has been deleted");
			System.out.println("busDeleted");
		
			}
		
		 catch (Exception ex) {
			
			//display error alert box
			AlertBox.display("Exception", ex.toString());
			ex.printStackTrace();
		}
	}	
	
	public static void addBus(int bNum, String origin, String destination, String departureDate, String time,int mCap) {
		try {
			Connection connection = initializeDB();
			
			//mysql statement. Just testing with first name and last name to get it working first
			String queryString = "INSERT INTO bus (busID, capacity, origin, destination, date, time) " +
			"VALUES (?,?,?,?,?,?)";
			
			//INSERT STATEMENT TO PULL BUSID VALUE WITH CORRESPONDING PNR
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setInt(1, bNum);
			preparedStatement.setInt(2, mCap);
			preparedStatement.setString(3, origin);
			preparedStatement.setString(4, destination);
			preparedStatement.setString(5, departureDate);
			preparedStatement.setString(6, time);
			
			//executed preparedStatement and save results to rset.
			preparedStatement.executeUpdate();
			
			AlertBox.display("Bus Added", "Bus " + bNum + " has been added");
		}
		catch (Exception ex) {
			
			//display error alert box
			AlertBox.display("Exception", ex.toString());
			ex.printStackTrace();
		}
	}
	
}
