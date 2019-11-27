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
	
	private String generatePNR() {

		pnr = "";

		// generates 3 random digits
		for (int i = 0; i < 3; i++) {
			pnr += (char) ((Math.random() * 9) + 49) + "";
		}
		
		// generates 3 random uppercase letters and appends them to pnr
		for (int i = 0; i < 3; i++) {
			pnr += (char) ((Math.random() * 26) + 65) + "";
		}

		return pnr;

	}

}
