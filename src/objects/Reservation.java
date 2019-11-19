package objects;

public class Reservation {
	
	private int pnr = 0;
	private int busID;
	private String firstName;
	private String lastName;
	private String username;
	
	public Reservation() {
		pnr++;
	}

	public int getPnr() {
		return pnr;
	}
	
	

}
