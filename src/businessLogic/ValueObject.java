package businessLogic;

import java.util.Date;

public class ValueObject {

	private int capacity;
	private String origin;
	private String destination;
	private Date departTime;
	private Date arrivalTime;
	private int busID;
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
	
	//no arg constructor
	public ValueObject() {
		
	}
	
	//constructor to create a user
	public ValueObject(String firstName, String lastName, String username, int ssn,
			String address, String city, String state, int zip, String password, 
			String email, String secQ, String secQAnswer) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.ssn = ssn;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.password = password;
		this.email = email;
		this.secQ = secQ;
		this.secQAnswer = secQAnswer;
		
	}
	
	//constructor to create a reservation
	public ValueObject(int busID, String username) {
		
		pnr = generatePNR();
		this.busID = busID;
		this.username = username;
	}
	
	//constructor to create a new bus
	public ValueObject(int busID, String origin, Date departTime, String destination,
			Date arrivalTime, int capacity) {
		this.busID = busID;
		this.origin = origin;
		this.departTime = departTime;
		this.destination = destination;
		this.arrivalTime = arrivalTime;
		this.capacity = capacity;
	}

	public int getCapacity() {
		return capacity;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDestination() {
		return destination;
	}

	public Date getDepartTime() {
		return departTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public int getBusID() {
		return busID;
	}

	public String getPnr() {
		return pnr;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUsername() {
		return username;
	}

	public int getSsn() {
		return ssn;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZip() {
		return zip;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getSecQ() {
		return secQ;
	}

	public String getSecQAnswer() {
		return secQAnswer;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setDepartTime(Date departTime) {
		this.departTime = departTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setSsn(int ssn) {
		this.ssn = ssn;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSecQ(String secQ) {
		this.secQ = secQ;
	}

	public void setSecQAnswer(String secQAnswer) {
		this.secQAnswer = secQAnswer;
	}
	
	//method to generate a unique reservation number
	private String generatePNR() {

		pnr = "";

		// generates 3 random digits and convert them to char
		for (int i = 0; i < 3; i++) {
			pnr += (char) ((Math.random() * 9) + 49) + ""; //omit 0 since it can be confused with letter o
		}
		
		// generates 3 random uppercase letters and appends them to pnr
		for (int i = 0; i < 3; i++) {
			pnr += (char) ((Math.random() * 26) + 65) + "";
		}

		return pnr;

	}
	
	
}




