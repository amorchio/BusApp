package application;

import java.sql.*;

import javafx.stage.Stage;

public class MySQLqueries {
	
	static Statement stmt;
	
	private static void initializeDB() {

		try {

			// 1. get a connection to the database
			Connection connection = DriverManager.getConnection("jdbc:mysql://35.196.76.98:3306/busbookingapp",
					"java-app",	"cis3270!");

			// 2. create a statement
			Statement stmt = connection.createStatement();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public static boolean checkLogin(String username, String password) {
		
		try {
			
			initializeDB();
			
			//create search string to look up username
			String queryString = "SELECT username, password " +
								 "FROM busbookingapp.user WHERE username = '" + username + "'";
					
			//save query result in variable rset
			ResultSet rset = stmt.executeQuery(queryString);
			
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
	
	public static void newUser(String lastName, String firstName, String ssn, String address,
			String city, String state, String zip, String email, String username, String password,
			String secQ, String secQAnswer) {

		try {

			initializeDB();
			
	/*		String query = "INSERT INTO " +
					"busbookingapp.user (username, first_name, last_name, " +
					"address, city, state, zip, password, email, secQ, secQAnswer) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	*/
			// create search string to look up username
			String queryString = "INSERT INTO busbookingapp.user (username, ssn, first_name, last_name, " +
					"address, city, state, zip, password, email, secQ, secQAnswer) VALUES ('" + 
					username + "', '" + Integer.parseInt(ssn)+ "', '" + firstName + "', '" + lastName +
					"', '" + address + "', '" + city + "', '" + state + "', '" + Integer.parseInt(zip) +
					"', '" + password + "', '" + email + "', '" + secQ + "', '" + secQAnswer + "'";

			// run query
			stmt.executeQuery(queryString);
			

		} catch (Exception ex) {
			System.out.println(ex);
		}

	}

}
