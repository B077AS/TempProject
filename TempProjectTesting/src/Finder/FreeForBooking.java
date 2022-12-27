package Finder;

public class FreeForBooking implements Comparable<FreeForBooking>{
	
	private String roomCode;
	private String start;
	private String end;
	private int seats;

	public FreeForBooking(String roomCode, String start, String end, int seats) {
		super();
		this.roomCode = roomCode;
		this.start = start;
		this.end = end;
		this.seats = seats;
	}
	
	

	public String getRoomCode() {
		return this.roomCode;
	}
	
	public String getStart() {
		return start;
	}


	public String getEnd() {
		return end;
	}


	public int getSeats() {
		return seats;
	}

	@Override
	public int compareTo(FreeForBooking o) {
		return this.roomCode.compareTo(o.getRoomCode());
	}
	
	@Override
	public String toString() {
		return this.roomCode+" from: "+this.start+" to: "+this.end+" with: "+this.seats+" seats";
		
	}
}
