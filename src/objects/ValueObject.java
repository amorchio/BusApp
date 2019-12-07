package objects;

import java.util.Date;

public class ValueObject {

	private int capacity;
	private String origin;
	private String destination;
	private Date departTime; // includes date
	private Date arrivalTime; // includes date
	private int busID;
	private int numBooked;
	private String pnr;
	private String firstName;
	private String lastName;
	private String username;
	private int ssn;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String password;
	private String email;
	private String secQ;
	private String secQAnswer;
	
	public ValueObject() {
		
	}
	
	public ValueObject(String firstName, String lastName, int ssn,
			String address, String city, String state, int zip,
			String username, String password, String email,
			String secQ, String secQAnswer) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.username = username;
		this.password = password;
		this.email = email;
		this.secQ = secQ;
		this.secQAnswer = secQAnswer;
	}
	
	
	
	
	
	
	
}




