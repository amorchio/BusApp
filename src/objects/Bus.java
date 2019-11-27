package objects;

import java.sql.Time;
import java.util.Date;

public class Bus {
	
	private int capacity;
	private String origin;
	private String destination;
	private Date date;
	private Time time;
	private int busID;
	
	public Bus(int capacity, String origin, String destination,
			Date date, Time time, int busID) {
		this.capacity = capacity;
		this.origin = origin;
		this.destination = destination;
		this.date = date;
		this.time = time;
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

	public Date getDate() {
		return date;
	}

	public Time getTime() {
		return time;
	}

	public int getBusID() {
		return busID;
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

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public void setBusID(int busID) {
		this.busID = busID;
	}
	
	public void delBus() {
		
		// override: delete the bus itself (need clarification)
		
	}
	

}
