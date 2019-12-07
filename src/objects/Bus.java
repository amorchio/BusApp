package objects;

import java.sql.Time;
import java.util.Date;

public class Bus {
	
	private int capacity;
	private String origin;
	private String destination;
	private Date departTime; // includes date
	private Date arrivalTime; // includes date
	private int busID;
	private int numBooked;
	
	public Bus(int capacity, String origin, String destination,
			Date date, Date departTime, Date arrivalTime, int busID) {
		this.capacity = capacity;
		this.origin = origin;
		this.destination = destination;
		this.departTime = departTime;
		this.arrivalTime = arrivalTime;
		this.busID = busID;
		
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

	public int getNumBooked() {
		return numBooked;
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
	
	
	
	
	
	

}
