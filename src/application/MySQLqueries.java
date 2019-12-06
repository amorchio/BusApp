package application;

import java.sql.*;
import java.util.ArrayList;

import javafx.stage.Stage;

public class MySQLqueries {
	
	//static Statement stmt;
	
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
					
					//create new instance of MainMenu
					MainMenu login = new MainMenu();
					login.start(new Stage());
					
					//close connection
			//		connection.close();
					
					return true;
					
				} else {
					//display error if password does not match username
					AlertBox.display("Login Error", "Username and/or password is incorrect. Please try again");
					
					//close connection
			//		connection.close();
					
					return false;
				}
				
			} else {
				//display error if username is not found
				AlertBox.display("Login Error", "Username not found. Please register as a new user.");
				
				//close connection
			//	connection.close();
				
				return false;
			}
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return false;
		
	}
	
	public static boolean newUser(String lastName, String firstName, int ssn, String address,
			String city, String state, int zip, String email, String username, String password,
			String secQ, String secQAnswer) {
		

		try {

			Connection connection = initializeDB();
			
			//mysql insert statement
			String queryString = "INSERT INTO " +
					"busbookingapp.user (username, ssn, first_name, last_name, " +
					"address, city, state, zip, password, email, secQ, secQAnswer) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			
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
			
			//execute preparedStatement
			preparedStatement.execute();
			
			//close the connection to the database
			//connection.close();
			
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
}
