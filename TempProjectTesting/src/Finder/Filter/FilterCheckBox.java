package Finder.Filter;

import java.util.LinkedList;
import javax.swing.JCheckBox;
import Finder.FreeForBooking;

public class FilterCheckBox extends JCheckBox implements Filterable{

	protected String identifier;
	protected String start;
	protected String end;
	protected int seats;
	protected LinkedList<FreeForBooking> filteredRooms;


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
	public LinkedList<FreeForBooking> filter(LinkedList<FreeForBooking> freeRooms, FilterCheckBox CheckBox) {
		// TODO Auto-generated method stub
		return null;
	}

}
