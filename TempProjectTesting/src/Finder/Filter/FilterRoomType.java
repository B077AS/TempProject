package Finder.Filter;

import java.util.HashMap;
import java.util.LinkedList;
import Finder.FreeForBooking;
import MyLoader.RoomLoader;
import Rooms.Rooms;

public class FilterRoomType extends FilterCheckBox{
	
	public FilterRoomType() {
		super();
	}
	
	
	@Override
	public LinkedList<FreeForBooking>  filter(LinkedList<FreeForBooking> freeRooms, FilterCheckBox filterLab){
		
		this.filteredRooms=new LinkedList<FreeForBooking>();
		String[] temp;
		HashMap<String, Rooms> uniRooms;
		try {
			uniRooms = new RoomLoader().getRooms();
		for(FreeForBooking book: freeRooms) {
			String code=book.getRoomCode();
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
