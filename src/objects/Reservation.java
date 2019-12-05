package objects;

public class Reservation {
	
	private String pnr;
	private int busID;
	private String first_name;
	private String last_name;
	private String username;
	
	public Reservation(int busID, String username) {
		
		pnr = generatePNR();
		this.busID = busID;
		this.username = username;
	}

	public String getPnr() {
		return pnr;
	}
	
	public int getBusID() {
		return busID;
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

	public void setBusID(int busID) {
		this.busID = busID;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	private String generatePNR() {

		pnr = "";

		// generates 3 random digits
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
