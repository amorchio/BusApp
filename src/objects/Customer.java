package objects;

public class Customer {
	
	private String first_name;
	private String last_name;
	private int ssn;
	private String address;
	private String city;
	private String state;
	private int zip;
	private String username;
	private String password;
	private String email;
	private String secQ;
	private String secQAnswer;
	
	public Customer(String first_name, String last_name, int ssn,
			String address, String city, String state, int zip,
			String username, String password, String email,
			String secQ, String secQAnswer) {
		this.first_name = first_name;
		this.last_name = last_name;
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

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSecQ() {
		return secQ;
	}

	public String getSecQAnswer() {
		return secQAnswer;
	}
	
	
	

}
