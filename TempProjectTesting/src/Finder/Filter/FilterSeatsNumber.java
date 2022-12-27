package Finder.Filter;

import java.util.LinkedList;
import Finder.FreeForBooking;

public class FilterSeatsNumber extends FilterCheckBox{
	
	public FilterSeatsNumber() {
		super();
	}
	
	@Override
	
	public LinkedList<FreeForBooking> filter(LinkedList<FreeForBooking> freeRooms, FilterCheckBox seatsCheckBox) {
		this.filteredRooms=new LinkedList<FreeForBooking>();
		for(FreeForBooking book: freeRooms) {
			if(book.getSeats()==seatsCheckBox.getSeats()) {
				filteredRooms.add(book);
			}
		}
		return this.filteredRooms;
	}

}
