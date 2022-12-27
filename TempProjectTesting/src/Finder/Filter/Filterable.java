package Finder.Filter;

import java.util.LinkedList;

import Finder.FreeForBooking;

public interface Filterable {
	
	public LinkedList<FreeForBooking> filter(LinkedList<FreeForBooking> freeRooms, FilterCheckBox CheckBox);

}
