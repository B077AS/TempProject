package Finder;

import java.io.FileInputStream;
import java.sql.*;
import java.sql.Date;
import java.time.*;
import java.util.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import MyLoader.RoomLoader;
import MyTimer.DateHolder;
import MyTimer.Months;
import MyTimer.MyTimer;
import Rooms.Booking;
import Rooms.Rooms;

public class FinderDB {
	private HashMap<String, Rooms> allRooms;
	private List<Booking> free;


	public FinderDB(int year, String month, int day, String start, String end) throws Exception {
		this.free= new ArrayList<Booking>();

		int monthNumber=Months.getMonths().get(month);
		DateHolder.DateHolder(day, monthNumber, year);
		LocalDate myDate = LocalDate.of(year, monthNumber, day);
		DayOfWeek dayOfWeek=myDate.getDayOfWeek();


		semesterChceck(myDate, dayOfWeek.toString());

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

					String query="(select Room, Start_Time, End_Time from schedule where Start_Time=? and End_Time=? and Day_Of_Week=?)\r\n"
							+ "union\r\n"
							+ "(select Room, Start_Time, End_Time from rooms_booking where Start_Time=? and End_Time=? and Locked='true' and (select dayname(rooms_booking.Date))=?)\r\n"
							+ "union\r\n"
							+ "(select Room, Start_Time, End_Time from lab_booking where Start_Time=? and End_Time=? and Locked='true' and (select dayname(lab_booking.Date))=?)";
					PreparedStatement preparedStmt = conn.prepareStatement(query);

					preparedStmt.setString(1, start);
					preparedStmt.setString(2, end);
					preparedStmt.setString(3, dayOfWeek.toString());
					preparedStmt.setString(4, start);
					preparedStmt.setString(5, end);
					preparedStmt.setString(6, dayOfWeek.toString());
					preparedStmt.setString(7, start);
					preparedStmt.setString(8, end);
					preparedStmt.setString(9, dayOfWeek.toString());
					ResultSet result=preparedStmt.executeQuery();
					while(result.next()) {
						if(this.allRooms.containsKey(result.getString(1))==true) {
							setAvailability(this.allRooms.get(result.getString(1)), result.getString(2), result.getString(3));
							//break;
						}

					}
					conn.close();
				}
			}
			checkAvailability(this.allRooms, endNumber, startNumber, loopCount, start, end);
		} catch (Exception e1) {
			return;
		}

	}

	public void setAvailability(Rooms r, String start, String end) {
		r.setAvailability(start+"-"+end, new Booking(start, end, null, r, null, null, null));

	}

	public void clearAvailability(HashMap<String, Rooms> rooms) {
		for(HashMap.Entry<String, Rooms> room: rooms.entrySet()) {
			room.getValue().getAvailability().clear();
		}
	}

	public void checkAvailability(HashMap<String, Rooms> rooms, int endNumber, int startNumber, int loopCount, String start, String end) {
		this.free.clear();
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
						//this.freeRooms.add(new FreeForBooking(room.getValue().getCode(), start, end, room.getValue().getSeats()));
						this.free.add(new Booking(start, end, null, room.getValue(), null, null, null));
						//System.out.println(room.getValue().getCode()+" LIBERO from: "+start+" to "+end+" posti: "+room.getValue().getSeats());
					}
				}
			}
		}
		Collections.sort(this.free);
	}

	public List<Booking> getFreeRooms(){
		return this.free;
	}

	public void semesterChceck(LocalDate myDate, String dayOfWeek) throws Exception {
		Connection conn=DBConnection.connect();
		String query="select * from semester";
		Statement preparedStmt=conn.createStatement();
		ResultSet result=preparedStmt.executeQuery(query);
		result.next();

		Date start=result.getDate(1);
		Date end=result.getDate(2);
		
		
		MyTimer currentTime=new MyTimer();
		
		
		if(myDate.compareTo(currentTime.getDate().toLocalDate())<0) {
			throw new Exception();
		}

		if(dayOfWeek.equals("SUNDAY")) {
			throw new Exception();
		}
		

		if(myDate.compareTo(start.toLocalDate())>0 && myDate.compareTo(end.toLocalDate())>0) {
			throw new Exception();
		}
		conn.close();

	}

}