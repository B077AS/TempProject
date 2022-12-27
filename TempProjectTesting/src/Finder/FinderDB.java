package Finder;

import java.io.FileInputStream;
import java.sql.*;
import java.time.*;
import java.util.*;
import DataBase.DBConnection;
import Exceptions.LoginException;
import MyLoader.RoomLoader;
import Rooms.Booking;
import Rooms.Rooms;

public class FinderDB {
	private HashMap<String, Integer> months;
	private LinkedList<FreeForBooking> freeRooms;
	private HashMap<String, Rooms> allRooms;


	public FinderDB(int year, String month, int day, String start, String end) {
		this.allRooms=new HashMap<String, Rooms>();
		this.freeRooms=new LinkedList<FreeForBooking>();
		setMonthsMap();
		int monthNumber=months.get(month);
		LocalDate myDate = LocalDate.of(year, monthNumber, day);
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();

		try {
			RoomLoader load=new RoomLoader();
			this.allRooms=load.getRooms();

		} catch (Exception e) {
			e.printStackTrace();
		}


		clearAvailability(this.allRooms);//resetto lo stato di occupato
		String[] startHour=start.split(":");
		String[] endHour=end.split(":");
		int startNumber=Integer.parseInt(startHour[0]);
		int endNumber=Integer.parseInt(endHour[0]);

		int loopCount=endNumber-startNumber;
		try {
			Properties config= new Properties();
			FileInputStream fis=new FileInputStream("Property/config.properties");
			config.load(fis);

			for(int i=0; i<loopCount; i++) {
				if(startNumber+i!=13) {
					start=Integer.toString(startNumber+i)+":00";
					end=Integer.toString(startNumber+i+1)+":00";
					Connection conn=DBConnection.connect();

					String query="select * from schedule where Start_Time=? and End_Time=? and Day_Of_Week=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);

					preparedStmt.setString(1, start);
					preparedStmt.setString(2, end);
					preparedStmt.setString(3, dayOfWeek.toString());
					ResultSet result=preparedStmt.executeQuery();
					while(result.next()) {
						if(this.allRooms.containsKey(result.getString(5))==true) {
							setAvailability(this.allRooms.get(result.getString(5)), result.getString(2), result.getString(3), result.getString(4), result.getString(6));
							//break;
						}

					}
				}
			}
			checkAvailability(this.allRooms, endNumber, startNumber, loopCount, start, end);
		} catch (Exception e1) {
			new LoginException();
			return;
		}

	}

	public void setMonthsMap() {
		HashMap<String, Integer> months=new HashMap<String, Integer>();
		months.put("January", 1);
		months.put("February", 2);
		months.put("March", 3);
		months.put("April", 4);
		months.put("May", 5);
		months.put("June", 6);
		months.put("July", 7);
		months.put("August", 8);
		months.put("September", 9);
		months.put("October", 10);
		months.put("November", 11);
		months.put("December", 12);
		this.months=months;
	}

	public void setAvailability(Rooms r, String start, String end, String subject, String day) {
		r.setAvailability(start+"-"+end, new Booking(start, end, subject, day));

	}

	public void clearAvailability(HashMap<String, Rooms> rooms) {
		for(HashMap.Entry<String, Rooms> room: rooms.entrySet()) {
			room.getValue().getAvailability().clear();
		}
	}

	public void checkAvailability(HashMap<String, Rooms> rooms, int endNumber, int startNumber, int loopCount, String start, String end) {
		this.freeRooms.clear();
		for(HashMap.Entry<String, Rooms> room: rooms.entrySet()) {
			HashMap<String, Booking> availability=room.getValue().getAvailability();
			for(int i=0; i<loopCount; i++) {
				if(startNumber+i!=13) {
					start=Integer.toString(startNumber+i)+":00";
					end=Integer.toString(startNumber+i+1)+":00";
					if(availability.containsKey(start+"-"+end)) {
						//System.out.println(room.getValue().getCode()+" OCCUPATO from: "+start+" to "+end+" for "+availability.get(start+"-"+end).getSubject());
					}
					else {
						this.freeRooms.add(new FreeForBooking(room.getValue().getCode(), start, end, room.getValue().getSeats()));
						//System.out.println(room.getValue().getCode()+" LIBERO from: "+start+" to "+end+" posti: "+room.getValue().getSeats());
					}
				}
			}
		}
		Collections.sort(this.freeRooms);
	}

	public LinkedList<FreeForBooking> getFreeRooms(){
		return this.freeRooms;
	}

}