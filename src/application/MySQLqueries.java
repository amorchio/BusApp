package application;

import java.sql.*;

import javafx.stage.Stage;

public class MySQLqueries {
	
	static Statement stmt;
	
	private static Connection initializeDB() {

		try {

			// 1. get a connection to the database
			Connection connection = DriverManager.getConnection("jdbc:mysql://35.196.76.98:3306/busbookingapp",
					"java-app",	"cis3270!");

			return connection;

		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;

		
	}
	
	public static boolean checkLogin(String username, String password) {
		
		//add connection close and rewrite mysql query to use PreparedStatement
		
		try {
			
			Connection connection = initializeDB();
			
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
			connection.close();
			
			return true;
			
			
			

		} catch (Exception ex) {
			
			//display error alert box
			AlertBox.display("Exception", ex.toString());
		}
		return false;

	}

	
	public static String getSecQ(String username) {
		
		//add connection close and rewrite mysql query to use PreparedStatement
		
		try {

			Connection connection = initializeDB();

			// create search string to look up username
			String queryString = "SELECT username, secQ " + 
								 "FROM busbookingapp.user WHERE username = ?";

			// create the mysql insert preparedstatement
			PreparedStatement preparedStatement = connection.prepareStatement(queryString);
			preparedStatement.setString(1, username);

			// save query result in variable rset
			ResultSet rset = preparedStatement.executeQuery();

			// process the if statement if the mysql query returns a result
			if (rset.next()) {
				// check if password matches
				return rset.getString(0);

			} else {				
				
				return "Username not found. Please try again"; 
			}

		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		return "Username not found. Please try again"; 
		
	}
	
}
