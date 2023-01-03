package Users.Professors;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
import DataBase.DBConnection;
import Exceptions.ExceptionFrame;
import Login.*;
import MyTimer.DateHolder;
import MyTimer.MyTimer;
import Rooms.Booking;
import Rooms.BookingSuccessful;
import Users.GeneralUser.Users;
import Users.GeneralUser.UsersGUI;

public class Teachers extends Users{

	public Teachers(String name, String lastName, String ID, String email, String password) {
		super(name, lastName, ID, email, password);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void GUI(LoginGUI frame) {
		TeachersGUI gui=new TeachersGUI(this.name, this.lastName, this.email, this);
		frame.dispose();

	}

	@Override
	public JButton checkNotifications() {
		boolean profRequests=false;
		boolean swapRequests=false;
		boolean reminders=false;;
		deleteOldNotifications();
		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications where Sender!=?";
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			ResultSet result=statement.executeQuery();

			profRequests=result.next();

			query="select * from swap_notifications where Receiver=? and Accepted='false'";
			statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			result=statement.executeQuery();

			swapRequests=result.next();

			query="select * from swap_notifications where Receiver=? or Sender=? and Accepted='true'";
			statement = conn.prepareStatement(query);
			statement.setString(1, this.ID);
			statement.setString(2, this.ID);
			result=statement.executeQuery();

			reminders=result.next();


			if (profRequests==true || swapRequests==true) {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-active.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else if(profRequests==false && swapRequests==false && reminders==false){
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-inactive.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);
			}
			else {
				conn.close();
				ImageIcon notificationIcon=new ImageIcon("Files/bell-icon-reminder.png");
				Image image = notificationIcon.getImage();
				Image newimg = image.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);  
				notificationIcon = new ImageIcon(newimg);
				return new JButton(notificationIcon);

			}


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;	

	}

	@Override
	public JPanel getMainPanel(UsersGUI gui) {
		// TODO Auto-generated method stub
		return new TeachersMainPanel(name, lastName, email, gui, this);
	}

	@Override
	public JPanel book(Object[] objects, UsersGUI frame) {
		int year=DateHolder.getYear();
		int month=DateHolder.getMonth();
		int day=DateHolder.getDay();

		JPanel bookPanel=new JPanel();

		bookPanel.setLayout (new GridBagLayout());
		GridBagConstraints c=new GridBagConstraints();

		Booking booking=(Booking)objects[0];


		JLabel bookingSummary=new JLabel("Booking Summary: "+booking.getRoom()+" from: "+booking.getStartTime()+" to "+booking.getEndTime());
		c.gridx=0;
		c.gridy=0;
		bookPanel.add(bookingSummary, c);

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now=LocalDate.now();
		dtf.format(now);

		Date date=Date.valueOf(year+"-"+month+"-"+day);
		LocalDate bookingDate=date.toLocalDate();

		JButton claimButton=new JButton("Calim Room");
		claimButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean firstCheck=false;
					boolean secondCheck=false;
					
					Connection conn=DBConnection.connect();
					String query="select * from solo_booking where Date=? and Room=? and Start_Time=? and End_Time=?";
					PreparedStatement preparedStmt=conn.prepareStatement(query);;
					preparedStmt.setDate(1, date);
					preparedStmt.setString(2, booking.getRoom().getCode());
					preparedStmt.setString(3, booking.getStartTime());
					preparedStmt.setString(4, booking.getEndTime());
					ResultSet result=preparedStmt.executeQuery();
					
					firstCheck=result.next();
					
					
					query="select * from solo_booking where Date=? and Room=? and Start_Time=? and End_Time=?";
					preparedStmt=conn.prepareStatement(query);;
					preparedStmt.setDate(1, date);
					preparedStmt.setString(2, booking.getRoom().getCode());
					preparedStmt.setString(3, booking.getStartTime());
					preparedStmt.setString(4, booking.getEndTime());
					result=preparedStmt.executeQuery();
					secondCheck=result.next();
					
					String bookingID=ID+"-"+booking.getRoom().getCode()+"-"+booking.getStartTime().split(":")[0]+"-"+booking.getEndTime().split(":")[0]+"-"+date;
					
					if(firstCheck==true && bookingDate.compareTo(now.plusDays(3))>0 || secondCheck==true && bookingDate.compareTo(now.plusDays(3))>0) {

						query="delete solo_booking, rooms_booking \r\n"
								+ "from solo_booking inner join rooms_booking where solo_booking.Date=rooms_booking.Date and\r\n"
								+ "solo_booking.Room=rooms_booking.Room and solo_booking.Start_Time=rooms_booking.Start_Time and\r\n"
								+ "solo_booking.End_Time=rooms_booking.End_Time and solo_booking.Date=? and solo_booking.Room=? and solo_booking.Start_Time=? and solo_booking.End_Time=? ";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setDate(1, date);
						preparedStmt.setString(2, booking.getRoom().getCode());
						preparedStmt.setString(3, booking.getStartTime());
						preparedStmt.setString(4, booking.getEndTime());
						preparedStmt.execute();
						
						
						query="insert into solo_booking (Booking_ID, Date, Room, User_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, bookingID);
						preparedStmt.setDate(2, date);
						preparedStmt.setString(3, booking.getRoom().getCode());
						preparedStmt.setString(4, ID);
						preparedStmt.setString(5, booking.getStartTime());
						preparedStmt.setString(6, booking.getEndTime());
						preparedStmt.setString(7, "true");
						preparedStmt.execute();
						
						conn.close();
						
						new BookingSuccessful();

					}
					else if(firstCheck==false && secondCheck==false) {
						query="insert into solo_booking (Booking_ID, Date, Room, User_ID, Start_Time, End_Time, Locked)"+"values (?, ?, ?, ?, ?, ?, ?)";
						preparedStmt = conn.prepareStatement(query);
						preparedStmt.setString(1, bookingID);
						preparedStmt.setDate(2, date);
						preparedStmt.setString(3, booking.getRoom().getCode());
						preparedStmt.setString(4, ID);
						preparedStmt.setString(5, booking.getStartTime());
						preparedStmt.setString(6, booking.getEndTime());
						preparedStmt.setString(7, "true");
						preparedStmt.execute();
						conn.close();
						new BookingSuccessful();
					}
					else {
						throw new Exception();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					new ExceptionFrame("Not Allowed to Overbook!");
					return;
				}

			}

		});
		c.gridx=1;
		c.gridy=0;
		bookPanel.add(claimButton, c);

		return bookPanel;
	}

	@Override
	public JPanel notificationPanel(Users user, UsersGUI frame) {
		return new TeachersNotificationPanel(user, frame);
	}

	public void deleteOldNotifications() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now=LocalDate.now();
		dtf.format(now);

		try {

			Connection conn=DBConnection.connect();
			String query="select * from prof_notifications";
			Statement statement = conn.createStatement();
			ResultSet result=statement.executeQuery(query);

			while(result.next()) {
				if(result.getDate(4)!=null && now.compareTo((result.getDate(4)).toLocalDate())>0) {
					query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date=? and New_From=? and New_To=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, result.getString(1));
					preparedStmt.setDate(2, result.getDate(2));
					preparedStmt.setString(3, result.getString(3));
					preparedStmt.setDate(4, result.getDate(4));
					preparedStmt.setString(5, result.getString(5));
					preparedStmt.setString(6, result.getString(6));
					preparedStmt.executeUpdate();

				}
				else if (result.getDate(4)==null && now.compareTo((result.getDate(2)).toLocalDate())>0){
					query="delete from prof_notifications where Schedule_ID=? and Date=? and Sender=? and New_Date is NULL";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, result.getString(1));
					preparedStmt.setDate(2, result.getDate(2));
					preparedStmt.setString(3, result.getString(3));
					preparedStmt.executeUpdate();
					conn.close();
				}
			}

			query="select * from swap_notifications";
			statement = conn.createStatement();
			result=statement.executeQuery(query);


			while(result.next()) {
				if(now.compareTo((result.getDate(4)).toLocalDate())>0) {
					query="delete from swap_notifications where Sender=? and Receiver=? and First_Date=? and New_Date=? and First_Schedule=? and New_Schedule=? and Accepted=?";
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					preparedStmt.setString(1, result.getString(1));
					preparedStmt.setString(2, result.getString(2));
					preparedStmt.setDate(3, result.getDate(3));
					preparedStmt.setDate(4, result.getDate(4));
					preparedStmt.setString(5, result.getString(5));
					preparedStmt.setString(6, result.getString(6));
					preparedStmt.setString(7, result.getString(7));
					preparedStmt.executeUpdate();
				}
			}

			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
