package Finder.Filter;

import java.util.List;
import javax.swing.JCheckBox;
import Rooms.Booking;

public class FilterCheckBox extends JCheckBox implements Filterable{

	protected String identifier;
	protected String start;
	protected String end;
	protected int seats;
	protected List<Booking> filteredRooms;


	public void setSeats(int i) {
		this.seats=i;
	}

	public int getSeats() {
		return this.seats;
	}

	public void setIdentifier(String id) {
		this.identifier=id;
	}

	public String getIdentifier() {
		return this.identifier;
	}

	@Override
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox CheckBox) {
		// TODO Auto-generated method stub
		return null;
	}

}
