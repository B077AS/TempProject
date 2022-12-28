package Finder.Filter;

import java.util.List;
import Rooms.Booking;

public interface Filterable {
	
	public List<Booking> filter(List<Booking> freeRooms, FilterCheckBox CheckBox);


}
