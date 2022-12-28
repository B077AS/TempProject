package Finder.Filter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import MyLoader.RoomLoader;
import Rooms.Booking;
import Rooms.Rooms;

public class FilterRoomType extends FilterCheckBox{
	
	public FilterRoomType() {
		super();
	}
	
	
	@Override
	public List<Booking>  filter(List<Booking> freeRooms, FilterCheckBox filterLab){
		
		this.filteredRooms=new ArrayList<Booking>();
		String[] temp;
		HashMap<String, Rooms> uniRooms;
		try {
			uniRooms = new RoomLoader().getRooms();
		for(Booking book: freeRooms) {
			String code=book.getRoom().getCode();
			Rooms room=uniRooms.get(code);
			if (room.getType().equals(filterLab.getIdentifier())) {
				this.filteredRooms.add(book);
			}
		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.filteredRooms;
	}

}
